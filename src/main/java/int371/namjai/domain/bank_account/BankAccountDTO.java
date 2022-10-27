package int371.namjai.domain.bank_account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountDTO {
    private String value;
    private String bankBrand;
    private String bankNumber;
    private String accountName;
    private String label;

    public String getLabel() {
        return this.getBankNumber() + " " + this.getBankBrand() + " " + this.getAccountName();
    }
}
