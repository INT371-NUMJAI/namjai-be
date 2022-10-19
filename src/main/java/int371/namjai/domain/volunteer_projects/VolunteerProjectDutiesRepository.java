package int371.namjai.domain.volunteer_projects;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerProjectDutiesRepository extends JpaRepository<VolunteerProjectDuties, String> {
    List<VolunteerProjectDuties> findByVolunteerProjectsUUID(String volunteerProjectUUID);
}
