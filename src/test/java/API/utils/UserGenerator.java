package API.utils;

import API.pojos.CreateUserRequest;
import static API.utils.RandomStringGenerator.getString;

public class UserGenerator {
    public static CreateUserRequest getUser(){
        CreateUserRequest user = CreateUserRequest.builder()
                .username(getString(6))
                .email(getString(8) + '@' + getString(6) + ".com")
                .build();
        String password = getString(6);
        user.setPassword_change(password);
        user.setPassword_repeat(password);

        return user;
    }
}
