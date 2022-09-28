package int371.namjai.domain.report_issue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIReportIssueForm {
    private String issueType;
    private String issueDetail;
    private String reporter;
}
