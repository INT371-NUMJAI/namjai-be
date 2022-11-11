package int371.namjai.domain.volunteer_projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerProjectsRepository extends JpaRepository<VolunteerProjects, String> {

    @Query("select vp.volunteerProjectName from VolunteerProjects  vp where vp.volunteerProjectsUUID = ?1 ")
    String getVolunteerName(String volunteerProjectUUID);


    List<VolunteerProjects> findVolunteerProjectsByFoundation_Email(String fdnEmail);

    @Query("select vp from VolunteerProjects  vp  where upper(vp.foundation.email) like  UPPER(?1) and vp.status='OPEN' order by vp.createDate desc ")
    List<VolunteerProjects> findVolunteerProjectsByFoundation_EmailAndStatusOpen(String email);


    @Query("select vp from VolunteerProjects  vp  where upper(vp.volunteerProjectName) like  concat('%',UPPER(?1),'%') and vp.status='OPEN'")
    List<VolunteerProjects> findVolunteerProjectsByVolunteerProjectNameContainingIgnoreCase(String volunteerName);

    List<VolunteerProjects> findAllByStatusIsOrderByEndDateDesc(String status);
}
