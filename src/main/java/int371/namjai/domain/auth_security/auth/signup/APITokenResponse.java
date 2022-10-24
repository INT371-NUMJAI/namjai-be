package int371.namjai.domain.auth_security.auth.signup;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class APITokenResponse {
    private String userName;
    private String email;
    private String role;
    private String status;
    private final String accessToken;

}
