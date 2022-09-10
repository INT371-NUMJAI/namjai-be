package int371.namjai.domain.foundation_rejected;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "fdn_rejected")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoundationRejected {

    @Id
    @Column(name = "fdn_rejected_uuid")
    private String fdnRejectedUUid;

    @Column(name = "fdn_uuid")
    private String fdnUUid;

    @Column(name = "detail")
    private String detail;

    @Column(name = "create_date")
    private Timestamp createDat;
}
