package int371.namjai.domain.withdrawal_request.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawalRequestApproveDTO {
    private String withdrawalUUID;
    private String fdnEmail;
    private String status;
    private String message;
}
