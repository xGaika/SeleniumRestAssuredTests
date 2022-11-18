package API.pojos;

import lombok.*;

import java.util.Base64;

@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CreateUserRequest{
    @Setter
    private String username;

    private String password_change;

    public void setPassword_change(String password_change) {
        this.password_change = Base64.getEncoder().encodeToString(password_change.getBytes());
    }

    private String password_repeat;

    public void setPassword_repeat(String password_repeat) {
        this.password_repeat = Base64.getEncoder().encodeToString(password_repeat.getBytes());
    }

    @Setter private String email;
    @Setter private String name;
    @Setter private String surname;
    @Setter private String currency_code;
}
