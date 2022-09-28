package int371.namjai.domain.report_issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
@CrossOrigin("*")
public class ReportIssueController {

    @Autowired
    private ReportIssueService reportIssueService;

    @PostMapping(value = "/add")
    public ResponseEntity<Void> createNewReport(@RequestBody APIReportIssueForm apiReportIssueForm) {
        reportIssueService.addNewReportIssue(apiReportIssueForm);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/view")
    private ResponseEntity<List<ReportIssue>> getListOfReportIssue() {
        return ResponseEntity.ok().body(reportIssueService.getAllReportIssueList());
    }

    @GetMapping(value = "/view/{id}")
    public ResponseEntity<ReportIssue> getReportIssueByID(@PathVariable("id") String issueUUID) {
        return ResponseEntity.ok(reportIssueService.getReportIssueById(issueUUID));
    }

}
