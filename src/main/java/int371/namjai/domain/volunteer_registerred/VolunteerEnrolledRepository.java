package int371.namjai.domain.volunteer_registerred;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerEnrolledRepository extends JpaRepository<VolunteerEnrolled, String> {
    List<VolunteerEnrolled> findByVolunteerProjects_VolunteerProjectsUUID(String volunteerProjectUUID);

    VolunteerEnrolled findByVolunteerProjects_VolunteerProjectsUUIDAndAndVolunteerEnrolledUUID(String volunteerProject, String volunteerEnrolledUUID);

    Boolean existsByVolunteerProjects_VolunteerProjectsUUIDAndEmail(String volunteerProjectUUID, String userEmail);

    VolunteerEnrolled findVolunteerEnrolledByEmailAndVolunteerProjects_VolunteerProjectsUUID(String email, String volunteerProject);

    Boolean existsByVolunteerProjects_VolunteerProjectsUUIDAndEmailAndContactNumber(String volunteerProjectUUID, String email, String contactNumber);

}
