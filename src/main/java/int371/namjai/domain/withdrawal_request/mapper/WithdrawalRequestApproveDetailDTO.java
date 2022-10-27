package int371.namjai.domain.withdrawal_request.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawalRequestApproveDetailDTO {
    private String withdrawalUUID;
    private String foundationProjectUUID;
    private String foundationProjectName;
    private double totalAmount;
    private String fdnUUID;
    private String fdnName;
    private String fdnEmail;
    private String bankAccountUUID;
    private String bankAccountName;
    private String bankAccountBrand;
    private String bankNumber;
}
