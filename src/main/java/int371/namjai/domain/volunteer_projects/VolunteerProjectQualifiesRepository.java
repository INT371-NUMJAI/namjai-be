package int371.namjai.domain.volunteer_projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerProjectQualifiesRepository extends JpaRepository<VolunteerProjectQualifies, String> {
    List<VolunteerProjectQualifies> findByVolunteerProjectsUUID(String volunteerProjectUUID);
}
