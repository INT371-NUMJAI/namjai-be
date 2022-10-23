package int371.namjai.domain.volunteer_registerred;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerEnrolledCRUDRepository extends CrudRepository<VolunteerEnrolled, String> {
    //    void deleteByVolunteerProjects_VolunteerProjectsUUIDAndEmail(String volunteerProject,String email);
//    void removeByVolunteerProjects_VolunteerProjectsUUIDAndEmail(String volunteerProject,String email);
    void removeVolunteerEnrolledByVolunteerProjects_VolunteerProjectsUUIDAndEmail(String volunteerProject, String email);
}
