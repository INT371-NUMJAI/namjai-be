package int371.namjai.domain.report_issue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "report_issue")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportIssue {
    @Id
    @Column(name = "issue_uuid")
    private String issueUUID;

    @Column(name = "issue_type")
    private String issueType;

    @Column(name = "detail")
    private String detail;

    @Column(name = "reporter")
    private String reporter;

    @Column(name = "issue_status")
    private String issueStatus;
}
