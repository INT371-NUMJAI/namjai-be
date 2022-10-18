package int371.namjai.domain.volunteer_projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerProjectsRepository extends JpaRepository<VolunteerProjects, String> {
}
