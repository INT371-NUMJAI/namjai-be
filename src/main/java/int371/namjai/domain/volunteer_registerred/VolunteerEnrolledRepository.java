package int371.namjai.domain.volunteer_registerred;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerEnrolledRepository extends JpaRepository<VolunteerEnrolled, String> {
    List<VolunteerEnrolled> findByVolunteerProjects_VolunteerProjectsUUID(String volunteerProjectUUID);

    VolunteerEnrolled findByVolunteerProjects_VolunteerProjectsUUIDAndAndVolunteerEnrolledUUID(String volunteerProject, String volunteerEnrolledUUID);

    Boolean existsByVolunteerProjects_VolunteerProjectsUUIDAndEmail(String volunteerProjectUUID, String userEmail);

    VolunteerEnrolled findVolunteerEnrolledByEmailAndVolunteerProjects_VolunteerProjectsUUID(String email, String volunteerProject);

    Boolean existsByVolunteerProjects_VolunteerProjectsUUIDAndEmailAndContactNumber(String volunteerProjectUUID, String email, String contactNumber);

    @Query(value = "SELECT ve.* FROM volunteer_enrolled ve LEFT JOIN volunteers_projects vp ON ve.volunteer_projects_uuid=vp.volunteer_projects_uuid WHERE ve.email=:email ORDER BY ve.enrolled_date desc", nativeQuery = true)
    List<VolunteerEnrolled> findVolunteerEnrolledsByEmailOrderByVolunteerProjectCreateDateDesc(@Param("email") String email);


}
