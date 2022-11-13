package int371.namjai.domain.foundation_project_financial;

import int371.namjai.domain.foundation_project.FoundationProject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "fdn_project_financial")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoundationProjectFinancial {
    @Id
    @Column(name = "fdn_project_financial_uuid")
    private String fdnProjectFinancialUUID;

    @OneToOne
    @JoinColumn(name = "fdn_project_uuid", referencedColumnName = "fdn_project_uuid")
    private FoundationProject foundationProject;

    @Column(name = "detail")
    private String detail;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "amount")
    private int amount;

    @Column(name = "create_date")
    private Timestamp createDate;

}
