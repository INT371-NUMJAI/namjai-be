package int371.namjai.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDTO {
    private String profileDetail;
    private String profileAddress;
    private String contactNumber;
    private String email;
}
