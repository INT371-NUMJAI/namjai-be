package int371.namjai.domain.withdrawal_request;

import int371.namjai.domain.bank_account.BankAccount;
import int371.namjai.domain.foundation_project.FoundationProject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "withdrawal_request")
@Getter
@Setter
@NoArgsConstructor
public class WithdrawalRequest {

    @Id
    @Column(name = "withdrawal_uuid")
    private String withdrawalUUID;

    @Column(name = "status")
    private String status;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "approve_date")
    private Timestamp approveDate;

    @ManyToOne
    @JoinColumn(name = "fdn_project_uuid")
    private FoundationProject foundationProject = new FoundationProject();

    @ManyToOne
    @JoinColumn(name = "bank_account_uuid")
    private BankAccount bankAccount = new BankAccount();


}
