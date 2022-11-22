package int371.namjai.domain.article.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleShortDTO {
    private String articleUUID;
    private String articleHeader;
    private String articleBody;
    private String author;
    private String createDate;
    private String status;
    private String email;
    private String articlePicture;
}
