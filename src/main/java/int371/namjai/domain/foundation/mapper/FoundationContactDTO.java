package int371.namjai.domain.foundation.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoundationContactDTO {
    private String fdnUUID;
    private String fdnName;
    private String addressDetail;
    private String subDistrict;
    private String district;
    private String province;
    private String postalCode;
    private String email;
    private String contactNumber;
    private String qrCodePath;
}
