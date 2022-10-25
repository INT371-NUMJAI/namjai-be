package int371.namjai.domain.bank_account;

import int371.namjai.domain.foundation.Foundation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bank_accounts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Id
    @Column(name = "bank_account_uuid")
    private String bankAccountUUID;

    @Column(name = "bank_brand")
    private String bankBrand;

    @Column(name = "bank_number")
    private String bankNumber;

    @Column(name = "account_name")
    private String accountName;

    @ManyToOne
    @JoinColumn(name = "fdn_uuid")
    private Foundation foundation = new Foundation();


}
