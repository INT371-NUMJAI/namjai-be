package int371.namjai.domain.user_favorite.mapper;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserFavoriteDTO {
    private String userFavoriteUUID;
    private String userEmail;
    private String typeOfFavorite;
    private String favoriteReferenceUUID;
    private String favoriteReferenceTitle;
}
