package int371.namjai.domain.foundation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoundationRepository extends JpaRepository<Foundation, String> {
    @Query(value = "SELECT fdn FROM Foundation fdn WHERE UPPER(fdn.email) LIKE UPPER(?1)")
    Foundation findByEmailIgnoreCase(String email);
}


