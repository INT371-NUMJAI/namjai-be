package int371.namjai.domain.foundation_project_financial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoundationProjectFinancialRepository extends JpaRepository<FoundationProjectFinancial, String> {

    List<FoundationProjectFinancial> findFoundationProjectFinancialsByFoundationProject_FdnProjectUUidOrderByCreateDateDesc(String fdnProjectUUID);
}
