package int371.namjai.domain.foundation_project_progress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoundationProjectProgressRepository extends JpaRepository<FoundationProjectProgress, String> {

    FoundationProjectProgress findFoundationProjectProgressByFoundationProject_FdnProjectUUid(String fdnProjectUUID);
}
