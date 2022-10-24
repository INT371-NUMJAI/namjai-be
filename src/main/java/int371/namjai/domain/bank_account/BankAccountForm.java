package int371.namjai.domain.bank_account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountForm {
    private String bankAccountUUID;
    private String bankBrand;
    private String bankNumber;
    private String accountName;
    private String fdnUUID;
}
