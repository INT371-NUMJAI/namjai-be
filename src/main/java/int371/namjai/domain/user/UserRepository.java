package int371.namjai.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByEmailIgnoreCase(String  email);
    User findByUserName(String username);
    boolean existsByUserName(String username);
}
