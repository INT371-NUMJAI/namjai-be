package int371.namjai.domain.transaction;

import int371.namjai.domain.foundation_project.FoundationProject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @Column(name = "transaction_uuid")
    private String transactionUUID;

    @Column(name = "payment_id")
    private String paymentID;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "create_date")
    private Timestamp createDate;

    @ManyToOne
    @JoinColumn(name = "fdn_project_uuid")
    private FoundationProject foundationProject = new FoundationProject();

}
