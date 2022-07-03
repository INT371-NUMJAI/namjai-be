package int371.namjai.domain.security_auth.auth.signup;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class APITokenResponse {
    private String userName;
    private String role;
    private final String accessToken;

}
