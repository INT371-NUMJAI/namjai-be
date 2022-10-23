package int371.namjai.domain.volunteer_registerred;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerEnrolledRepository extends JpaRepository<VolunteerEnrolled, String> {
    //    @Query("select u  from VolunteerRegistered  vr left join User u  left join VolunteerRegisteredAnonymous  vra where  vr.volunteerProjects.volunteerProjectsUUID = ?1 ")
//    @Query("select u  from VolunteerEnrolled  vr  left join User u ")
//    List<User> findByVolunteerProjectUUID();
    List<VolunteerEnrolled> findByVolunteerProjects_VolunteerProjectsUUID(String volunteerProjectUUID);

    VolunteerEnrolled findByVolunteerProjects_VolunteerProjectsUUIDAndAndVolunteerEnrolledUUID(String volunteerProject, String volunteerEnrolledUUID);

    //    List<User> findByVolunteerProjectUUID(String fdnUUID);
    Boolean existsByVolunteerProjects_VolunteerProjectsUUIDAndEmail(String volunteerProjectUUID, String userEmail);

//    @Query("select vra  from VolunteerRegistered  vr left join VolunteerRegisteredAnonymous  vra where  vr.volunteerProjects.volunteerProjectsUUID = ?1 ")
//    List<VolunteerRegisteredAnonymous> findAnonymousByVolunteerProjectUUID(String fdnUUID);

}
