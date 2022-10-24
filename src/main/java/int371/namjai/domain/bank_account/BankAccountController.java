package int371.namjai.domain.bank_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/view")
@CrossOrigin("*")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping(value = "/bank-account/create")
    public ResponseEntity<Void> createBankAccount(@RequestBody BankAccountForm bankAccountForm) {
        bankAccountService.createBankAccount(bankAccountForm);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/bank-account/fdn")
    public ResponseEntity<List<BankAccountDTO>> getAllBankAccountByFoundationUUID(@RequestParam("id") String fdnUUId) {
        return ResponseEntity.ok().body(bankAccountService.getAllBankAccountByFoundationUUID(fdnUUId));
    }
}
