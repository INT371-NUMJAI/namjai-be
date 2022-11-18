package int371.namjai.domain.article;

import int371.namjai.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "articles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @Column(name = "articles_uuid")
    private String articleUUID;

    @Column(name = "articles_header")
    private String articleHeader;

    @Column(name = "articles_body")
    private String articleBody;

    @Column(name = "status")
    private String status;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "author_role")
    private String authorRole;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user = new User();

    @Column(name = "picture_path")
    private String picturePath;
}
