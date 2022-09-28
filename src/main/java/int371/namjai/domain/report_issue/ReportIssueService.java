package int371.namjai.domain.report_issue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReportIssueService {
    @Autowired
    private ReportIssueRepository reportIssueRepo;

    public void addNewReportIssue(APIReportIssueForm apiReportIssueForm) {
        ReportIssue reportIssue = new ReportIssue();
        reportIssue.setIssueUUID(UUID.randomUUID().toString());
        reportIssue.setIssueStatus("UNSOLVED");
        reportIssue.setIssueType(apiReportIssueForm.getIssueType());
        reportIssue.setDetail(apiReportIssueForm.getIssueDetail());
        reportIssue.setReporter(apiReportIssueForm.getReporter());
        reportIssueRepo.save(reportIssue);
    }

    public List<ReportIssue> getAllReportIssueList() {
        return reportIssueRepo.findAll();
    }

    public ReportIssue getReportIssueById(String issueUUID) {
        return reportIssueRepo.findById(issueUUID)
                .orElseThrow(ReportIssueNotFoundException::new);
    }
}
