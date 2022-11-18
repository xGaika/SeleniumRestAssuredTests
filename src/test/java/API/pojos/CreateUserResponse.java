package API.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CreateUserResponse{
    private int id;
    private int country_id;
    private int timezone_id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String gender;
    private int phone_number;
    private String birthdate;
    private boolean bonuses_allowed;
    @JsonProperty("is_verified")
    private boolean isVerified;
}
