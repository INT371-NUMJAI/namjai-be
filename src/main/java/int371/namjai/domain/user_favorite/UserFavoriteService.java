package int371.namjai.domain.user_favorite;

import int371.namjai.domain.user_favorite.mapper.UserFavoriteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserFavoriteService {
    @Autowired
    private UserFavoriteRepository userFavoriteRepo;


    public List<UserFavorite> getListOfUserFavorite() {
        return userFavoriteRepo.findAll();
    }

    public List<UserFavorite> getUserFavoriteByUserEmail(String userEmail) {
        return userFavoriteRepo.findUserFavoritesByUserEmailIgnoreCaseOrderByCreateDateDesc(userEmail);
    }

    public UserFavorite getUserFavoriteByID(String userFavUUID) {
        return userFavoriteRepo.findById(userFavUUID).orElseThrow(NoSuchElementException::new);
    }

    public void setUserFavorite(UserFavoriteDTO userFavDTO) {
        UserFavorite userFav = new UserFavorite();
        userFav.setUserFavoriteUUID(userFavDTO.getUserFavoriteUUID());
        userFav.setUserEmail(userFavDTO.getUserEmail());
        userFav.setTypeOfFavorite(userFavDTO.getTypeOfFavorite());
        userFav.setFavoriteReferenceUUID(userFavDTO.getFavoriteReferenceUUID());
        userFav.setFavoriteReferenceTitle(userFavDTO.getFavoriteReferenceTitle());
        userFav.setCreateDate(new Timestamp(System.currentTimeMillis()));

        userFavoriteRepo.save(userFav);
    }

    @Transactional
    public void unSetUserFavorite(String type, String favRefUUID, String userEmail) {
//         UserFavorite userFav = getUserFavoriteByTypeAndFavoriteRefAndUserEmail(type,favRefUUID,userEmail);
        userFavoriteRepo.deleteByTypeOfFavoriteAndFavoriteReferenceUUIDAndUserEmail(type, favRefUUID, userEmail);
    }

    public boolean getUserFavoriteByTypeAndFavoriteRefAndUserEmail(String type, String favRefUUID, String userEmail) {
        return userFavoriteRepo.existsByTypeOfFavoriteAndFavoriteReferenceUUIDAndUserEmail(type, favRefUUID, userEmail);
    }

}
