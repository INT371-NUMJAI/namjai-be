package int371.namjai.domain.article;

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

    @Column(name = "create_by_email")
    private String createByEmail;

    @Column(name = "picture_path")
    private String picturePath;
}
