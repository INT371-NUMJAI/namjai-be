package int371.namjai.domain.article.dto;

import int371.namjai.domain.article.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mappings({
            @Mapping(target = "articleUUID", source = " article.articleUUID"),
            @Mapping(target = "articleHeader", source = "article.articleHeader"),
            @Mapping(target = "articleBody", source = "article.articleBody"),
            @Mapping(target = "status", source = "article.status"),
            @Mapping(target = "createDate", source = "article.createDate", dateFormat = "dd MMM yyyy"),
            @Mapping(target = "author", source = "article.user.userName"),
            @Mapping(target = "email", source = "article.user.email"),
            @Mapping(target = "articlePicture", source = "article.picturePath")
    })
    ArticleShortDTO toArticleShortDto(Article article);

    List<ArticleShortDTO> toArticleShortDTOList(List<Article> articleList);
}
