package int371.namjai.domain.withdrawal_request;


import int371.namjai.domain.backoffice.BackOfficeService;
import int371.namjai.domain.bank_account.BankAccount;
import int371.namjai.domain.bank_account.BankAccountService;
import int371.namjai.domain.foundation_project.FoundationProject;
import int371.namjai.domain.foundation_project.FoundationProjectRepository;
import int371.namjai.domain.foundation_project.FoundationProjectService;
import int371.namjai.domain.withdrawal_request.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class WithdrawalRequestService {

    @Autowired
    private WithdrawalRequestRepository withdrawalRequestRepo;

    @Autowired
    private FoundationProjectRepository foundationProjectRepo;

    @Autowired
    private FoundationProjectService foundationProjectService;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BackOfficeService backOfficeService;

    public void createWithdrawalRequest(WithdrawalRequestFormDTO withdrawalRequestFormDTO) {
        FoundationProject foundationProject = foundationProjectService.getFoundationById(withdrawalRequestFormDTO.getFdnProjectUUID());

        BankAccount bankAccount = bankAccountService.getBankAccountById(withdrawalRequestFormDTO.getBankAccountUUID());
        WithdrawalRequest withdrawalRequest = new WithdrawalRequest();
        withdrawalRequest.setWithdrawalUUID(withdrawalRequestFormDTO.getWithdrawalRequestUUID());
        withdrawalRequest.setStatus("PENDING");
        withdrawalRequest.setCreateDate(new Timestamp(System.currentTimeMillis()));
        withdrawalRequest.setApproveDate(null);
        withdrawalRequest.setBankAccount(bankAccount);
        withdrawalRequest.setFoundationProject(foundationProject);
        withdrawalRequestRepo.save(withdrawalRequest);
    }

    public List<WithdrawalRequestApproveShortDTO> getWithdrawalRequest() {
        return WithdrawalRequestMapper.INSTANCE.toWithdrawalRequestApproveShortDtoList(withdrawalRequestRepo.findAll());
    }

    public WithdrawalRequestApproveDetailDTO getWithdrawalRequestDetailByID(String withdrawalUUID) {
        return WithdrawalRequestMapper.INSTANCE.toWithdrawalRequestApproveDetailDto(getWithdrawalRequestById(withdrawalUUID));
    }


    public void approveWithdrawal(WithdrawalRequestApproveDTO withdrawalRequestApproveDTO) throws MessagingException {
        WithdrawalRequest withdrawalRequest = getWithdrawalRequestById(withdrawalRequestApproveDTO.getWithdrawalUUID());
        withdrawalRequest.setStatus(withdrawalRequestApproveDTO.getStatus());
        withdrawalRequest.setApproveDate(new Timestamp(System.currentTimeMillis()));
        backOfficeService.sendmailForApprovalWithdrawal(withdrawalRequestApproveDTO.getWithdrawalUUID(), withdrawalRequestApproveDTO.getFdnEmail(), withdrawalRequest.getStatus(), withdrawalRequestApproveDTO.getMessage());
    }

    private WithdrawalRequest getWithdrawalRequestById(String requestID) {
        return withdrawalRequestRepo.findById(requestID).orElseThrow(WithdrawalRequestNotFoundException::new);
    }
}
