package int371.namjai.domain.withdrawal_request.mapper;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawalRequestFormDTO {
    private String withdrawalRequestUUID;
    private String fdnProjectUUID;
    private String bankAccountUUID;
}
