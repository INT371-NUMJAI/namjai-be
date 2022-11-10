package int371.namjai.domain.user_favorite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavoriteRepository extends JpaRepository<UserFavorite, String> {

    List<UserFavorite> findUserFavoritesByUserEmailIgnoreCaseOrderByCreateDateDesc(String userEmail);

    UserFavorite findUserFavoriteByTypeOfFavoriteAndFavoriteReferenceUUIDAndUserEmail(String type, String favoriteUUID, String userEmail);

    void deleteByTypeOfFavoriteAndFavoriteReferenceUUIDAndUserEmail(String type, String favoriteUUID, String userEmail);

    boolean existsByTypeOfFavoriteAndFavoriteReferenceUUIDAndUserEmail(String type, String favoriteUUID, String userEmail);

}
