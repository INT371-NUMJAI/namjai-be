package int371.namjai.domain.volunteer_registerred;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRegisteredAnonymousRepository extends JpaRepository<VolunteerRegisteredAnonymous, String> {
}
