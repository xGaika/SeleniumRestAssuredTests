package API;

import API.pojos.CreateUserRequest;
import API.pojos.CreateUserResponse;
import API.utils.ExpectedResponse;
import API.utils.UserGenerator;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApiTests {
    private static String basicUsername = "front_2d6b0a8391742f5d789d7d915755e09e:";
    private static String token = Base64.getUrlEncoder().encodeToString(basicUsername.getBytes());
    private String access_token;
    private CreateUserRequest user = UserGenerator.getUser();
    private int user_id;

    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("http://test-api.d6.dev.devcaz.com")
                    .setContentType("application/json")
                    .build();

    @Test
    public void getGuestToken(){
         this.access_token = given()
                 .spec(REQ_SPEC)
                 .header("Authorization", "Basic " + token)
                 .body("{\"grant_type\":\"client_credentials\", \"scope\":\"guest:default\"}")
                 .when().post("/v2/oauth2/token")
                 .then()
                 .assertThat().statusCode(200)
                 .extract().path("access_token");

         assertThat(access_token).isNotNull();
    }

    @Test()
    public void registerUser(){
         CreateUserResponse rs = given()
                 .spec(REQ_SPEC)
                 .header("Authorization", "Bearer " + access_token)
                 .body(user).log().all()
                 .when().post("/v2/players")
                 .then().log().all()
                 .assertThat().statusCode(201)
                 .extract().as(CreateUserResponse.class);

         CreateUserResponse exrs = ExpectedResponse.getResponse(user);
         exrs.setId(rs.getId());
         assertThat(rs).isEqualTo(exrs);
         this.user_id = rs.getId();
    }



}
