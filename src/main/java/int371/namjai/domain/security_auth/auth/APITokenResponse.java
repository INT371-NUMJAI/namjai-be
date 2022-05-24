package int371.namjai.domain.security_auth.auth;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class APITokenResponse {
    //    private Users user;
    private final String accessToken;

}
