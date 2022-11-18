package API.utils;

import API.pojos.CreateUserRequest;
import API.pojos.CreateUserResponse;

public class ExpectedResponse {
    public static CreateUserResponse getResponse(CreateUserRequest rq){
        return CreateUserResponse.builder()
                .username(rq.getUsername())
                .email(rq.getEmail())
                .name(rq.getName())
                .surname(rq.getSurname())
                .bonuses_allowed(true)
                .isVerified(false)
                .build();
    }
}
