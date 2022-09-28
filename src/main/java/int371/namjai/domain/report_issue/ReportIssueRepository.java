package int371.namjai.domain.report_issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportIssueRepository extends JpaRepository<ReportIssue, String> {
}
