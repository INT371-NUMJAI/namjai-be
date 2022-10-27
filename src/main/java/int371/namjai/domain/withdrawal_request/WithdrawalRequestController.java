package int371.namjai.domain.withdrawal_request;

import int371.namjai.domain.withdrawal_request.mapper.WithdrawalRequestApproveDTO;
import int371.namjai.domain.withdrawal_request.mapper.WithdrawalRequestApproveDetailDTO;
import int371.namjai.domain.withdrawal_request.mapper.WithdrawalRequestApproveShortDTO;
import int371.namjai.domain.withdrawal_request.mapper.WithdrawalRequestFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/view")
@CrossOrigin("*")
public class WithdrawalRequestController {
    @Autowired
    private WithdrawalRequestService withdrawalRequestService;

    @PostMapping(value = "/request-withdrawal/create")
    public ResponseEntity<Void> createWithdrawalRequest(@RequestBody WithdrawalRequestFormDTO withdrawalRequestFormDTO) {
        withdrawalRequestService.createWithdrawalRequest(withdrawalRequestFormDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/request-withdrawal")
    public ResponseEntity<List<WithdrawalRequestApproveShortDTO>> getWithdrawalRequestListShort() {
        return ResponseEntity.ok().body(withdrawalRequestService.getWithdrawalRequest());
    }

    @GetMapping(value = "/request-withdrawal/{id}")
    public ResponseEntity<WithdrawalRequestApproveDetailDTO> getWithdrawalRequestDetailById(@PathVariable("id") String requestUUID) {
        return ResponseEntity.ok().body(withdrawalRequestService.getWithdrawalRequestDetailByID(requestUUID));
    }

    @GetMapping(value = "/request-withdrawal/")
    public ResponseEntity<List<WithdrawalRequestApproveShortDTO>> getWithdrawalRequestDetailByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok().body(withdrawalRequestService.getWithdrawralRequestByEmail(email));
    }


    @PostMapping(value = "/request-withdrawal/approve")
    public ResponseEntity<WithdrawalRequestApproveDetailDTO> approveWithdrawalRequest(@RequestBody WithdrawalRequestApproveDTO withdrawalRequestApproveDTO) throws MessagingException {
        withdrawalRequestService.approveWithdrawal(withdrawalRequestApproveDTO);
        return ResponseEntity.ok().build();

    }

}
