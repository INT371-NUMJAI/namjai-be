package int371.namjai.domain.withdrawal_request.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawalRequestApproveShortDTO {
    private String withdrawalUUID;
    //    private String foundationProjectUUID;
    private String foundationProjectName;
    private String status;
    private double totalAmount;
    private String createDate;
    private String approveDate;
}
