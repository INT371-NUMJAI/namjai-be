package int371.namjai.domain.foundation;

import int371.namjai.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoundationRepository extends JpaRepository<Foundation,String>{
}
