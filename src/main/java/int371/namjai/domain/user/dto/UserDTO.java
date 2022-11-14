package int371.namjai.domain.user.dto;

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
