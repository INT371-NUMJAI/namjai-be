package int371.namjai.domain.auth.auth.signup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class APIUserSignup {
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String password;



}
