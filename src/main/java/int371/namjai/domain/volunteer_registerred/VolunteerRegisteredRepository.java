package int371.namjai.domain.volunteer_registerred;

import int371.namjai.domain.user.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRegisteredRepository extends JpaRepository<VolunteerRegistered, String> {
    @Query("select u,vra from VolunteerRegistered  vr left join User u  left join VolunteerRegisteredAnonymous  vra where  vr.volunteerProjects.volunteerProjectsUUID = ?1 ")
    List<UserDTO> findByVolunteerProjectUUID(String fdnUUID);
}
