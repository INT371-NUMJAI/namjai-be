package int371.namjai.domain.auth.auth;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {
//    private Users user;
    private final String accessToken;

}
