package int371.namjai.domain.article.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleForm {
    private String articleUUID;
    private String articleHeader;
    private String articleBody;
    private String authorRole;
    private String authorEmail;
}
