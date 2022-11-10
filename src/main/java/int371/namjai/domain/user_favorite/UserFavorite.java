package int371.namjai.domain.user_favorite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "user_fav")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserFavorite {
    @Id
    @Column(name = "user_fav_uuid")
    private String userFavoriteUUID;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "type_of_fav")
    private String typeOfFavorite;

    @Column(name = "fav_ref_uuid")
    private String favoriteReferenceUUID;

    @Column(name = "fav_ref_title")
    private String favoriteReferenceTitle;

    @Column(name = "create_date")
    private Timestamp createDate;


}
