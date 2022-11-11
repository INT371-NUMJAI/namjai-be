package int371.namjai.domain.foundation_project_progress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoundationProjectProgressRepository extends JpaRepository<FoundationProjectProgress, String> {

    List<FoundationProjectProgress> findFoundationProjectProgressesByFoundationProject_FdnProjectUUid(String fdnProjectUUID);

    FoundationProjectProgress findFoundationProjectProgressByFoundationProject_FdnProjectUUidAndFdnProjectProgressUUID(String fdnProjectUUID, String fdnProjectProgressUUID);
}
