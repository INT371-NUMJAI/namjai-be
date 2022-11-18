package int371.namjai.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT u FROM User u WHERE UPPER(u.email) LIKE UPPER(?1) " +
            "AND u.status LIKE 'ACTIVE' ")
    User findByEmailIgnoreCaseAndStatusActive(String email);

    @Query(value = "SELECT u FROM User u WHERE UPPER(u.email) LIKE UPPER(?1) ")
    User findByEmailIgnoreCaseEveryStatus(String email);

    @Query(value = "SELECT u FROM User u WHERE UPPER(u.email) LIKE UPPER(?1) " +
            "AND u.status LIKE 'DISABLE' ")
    User findByEmailIgnoreCaseAndStatusDisable(String email);

    @Query(value = "SELECT u.role.roleName FROM User u WHERE UPPER(u.email) LIKE UPPER(?1) ")
    String selectRoleNameByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Query(value = "DELETE FROM User u WHERE UPPER(u.email) LIKE UPPER(?1) ")
    void deleteByEmail(String email);

    @Query(value = "SELECT u FROM User u WHERE UPPER(u.email) LIKE UPPER(?1) ")
    User findByEmailIgnoreCase(String email);

    @Query(value = "SELECT u.userName FROM User u WHERE UPPER(u.email) LIKE UPPER(?1) ")
    String findUserNameByEmailIgnoreCase(String email);

    User findByUserNameIgnoreCase(String userName);

    List<User> findUsersByRole_RoleUUidAndStatus(String roleId, String status);

    @Query(value = "SELECT COUNT(u.userUUid) FROM User u inner join  u.targetCategoriesSuggestion t WHERE  u.userUUid =?1 ")
    Integer getNumberOfUserSuggestion(String userUUID);


}
