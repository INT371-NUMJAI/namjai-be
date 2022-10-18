package int371.namjai.domain.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String userUUID;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String email;
}
