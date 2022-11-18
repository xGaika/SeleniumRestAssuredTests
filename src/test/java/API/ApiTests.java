package API;

import API.pojos.CreateUserRequest;
import API.pojos.CreateUserResponse;
import API.utils.ExpectedResponse;
import API.utils.UserGenerator;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTests {
    private static String basicUsername = "front_2d6b0a8391742f5d789d7d915755e09e:";
    private static String basic_token = Base64.getUrlEncoder().encodeToString(basicUsername.getBytes());
    private String guest_access_token;
    private String user_access_token;
    private CreateUserRequest user = UserGenerator.getUser();
    private CreateUserResponse userData;
    private int user_id;

    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("http://test-api.d6.dev.devcaz.com")
                    .setContentType("application/json")
                    .build();

    @Test
    @Order(1)
    public void getGuestToken(){
         guest_access_token = given()
                 .spec(REQ_SPEC)
                 .header("Authorization", "Basic " + basic_token)
                 .body("{\"grant_type\":\"client_credentials\", \"scope\":\"guest:default\"}")
                 .when().post("/v2/oauth2/token")
                 .then()
                 .assertThat().statusCode(200)
                 .extract().path("access_token");

         assertThat(guest_access_token).isNotNull();
         System.out.println("Получен токен гостя:\n" + guest_access_token);
    }

    @Test
    @Order(2)
    public void registerUser(){
         CreateUserResponse rs = given()
                 .spec(REQ_SPEC)
                 .header("Authorization", "Bearer " + guest_access_token)
                 .body(user)
                 .when().post("/v2/players")
                 .then()
                 .assertThat().statusCode(201)
                 .extract().as(CreateUserResponse.class);

         CreateUserResponse exrs = ExpectedResponse.getResponse(user);
         exrs.setId(rs.getId());
         assertThat(rs).isEqualTo(exrs);
         this.user_id = rs.getId();

        System.out.println("Пользователь с id " + user_id + " успешно зарегестрирован");
        userData = rs;
    }

    @Test
    @Order(3)
    public void authorizationUser(){
        user_access_token = given()
                .spec(REQ_SPEC)
                .header("Authorization", "Basic " + basic_token)
                .body("{\"grant_type\":\"password\", \"username\":\"" + user.getUsername() + "\", \"password\":\"" + user.getPassword_change() + "\"}").log().all()
                .when().post("/v2/oauth2/token")
                .then()
                .assertThat().statusCode(200)
                .extract().path("access_token");

        assertThat(user_access_token).isNotNull();
        System.out.println("Получен токен пользователя:\n" + user_access_token);
    }

    @Test
    @Order(4)
    public void getUserProfile(){
        CreateUserResponse rs = given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + user_access_token)
                .when().get("/v2/players/" + user_id)
                .then()
                .assertThat().statusCode(200)
                .extract().as(CreateUserResponse.class);

        assertThat(rs).isEqualTo(userData);
        System.out.println("Данные пользователя совпадают с ожидаемыми");
    }

    @Test
    @Order(5)
    public void getAnotherUserProfile(){
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + user_access_token)
                .when().get("/v2/players/20" + user_id)
                .then()
                .assertThat().statusCode(404);

        System.out.println("Данные другого пользователя не найдены");
    }

}
