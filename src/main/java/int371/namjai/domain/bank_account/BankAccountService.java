package int371.namjai.domain.bank_account;

import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private FoundationService foundationService;


    public void createBankAccount(BankAccountForm bankAccountForm) {
        BankAccount bankAccount = new BankAccount();
        Foundation foundation = foundationService.getFoundationById(bankAccountForm.getFdnUUID());
        bankAccount.setBankAccountUUID(bankAccountForm.getBankAccountUUID());
        bankAccount.setBankBrand(bankAccountForm.getBankBrand());
        bankAccount.setBankNumber(bankAccountForm.getBankNumber());
        bankAccount.setAccountName(bankAccountForm.getAccountName());
        bankAccount.setFoundation(foundation);

        bankAccountRepository.save(bankAccount);
    }

    public List<BankAccountDTO> getAllBankAccountByFoundationUUID(String fdnUUId) {
        List<BankAccount> bankAccounts = bankAccountRepository.findBankAccountsByFoundation_FdnUUid(fdnUUId);
        return BankAccountMapper.INSTANCE.toBankAccountDTOList(bankAccounts);
    }
}
