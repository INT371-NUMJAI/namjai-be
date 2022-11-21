package int371.namjai.domain.volunteer_registerred.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserEnrolledVolunteerProjectDTO {
    private String userFavoriteUUID;
    private String userEmail;
    private String typeOfFavorite;
    private String favoriteReferenceUUID;
    private String favoriteReferenceTitle;
    private Timestamp createDate;
}
