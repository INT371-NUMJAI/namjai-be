package int371.namjai.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT u FROM User u WHERE UPPER(u.email) LIKE UPPER(?1) " +
            "AND u.status LIKE 'ACTIVE' ")
    User findByEmailIgnoreCaseAndStatusActive(String email);

//    @Query(value = "SELECT u FROM User u WHERE UPPER(u.email) LIKE UPPER(?1) " +
//            "AND u.status LIKE 'ACTIVE' ")
//    User findByEmailIgnoreCaseAndStatusActive(String email);
@Query(value = "SELECT u FROM User u WHERE UPPER(u.email) LIKE UPPER(?1) " +
        "AND u.status LIKE 'DISABLE' ")
User findByEmailIgnoreCaseAndStatusDisable(String email);

    @Query(value = "SELECT u.role.roleName FROM User u WHERE UPPER(u.email) LIKE UPPER(?1) ")
    String selectRoleNameByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    @Query(value = "SELECT u FROM User u WHERE UPPER(u.email) LIKE UPPER(?1) ")
    User findByEmailIgnoreCase(String email);
}
