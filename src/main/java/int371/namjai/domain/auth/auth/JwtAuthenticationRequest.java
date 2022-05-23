package int371.namjai.domain.auth.auth;

import lombok.Getter;

@Getter
public class JwtAuthenticationRequest {
    private String email;
    private String password;

}
