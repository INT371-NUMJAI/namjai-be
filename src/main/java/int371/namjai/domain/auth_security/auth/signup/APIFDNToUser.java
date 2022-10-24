package int371.namjai.domain.auth_security.auth.signup;

import int371.namjai.domain.resource.Resource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIFDNToUser {

    private String fdnName;

    private String addressNo;

    private String streetNo;

    private String streetName;

    private String subDistrict;

    private String district;

    private String province;

    private String postalCode;

    private String founderName;

    private String fdnDetail;

    private String fdnSize;

    private String establishDate;

    private String email;

    private String contactNumber;

    private String status;

    private Resource resource;

}
