package int371.namjai.domain.article;

import int371.namjai.domain.article.dto.ArticleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepo;


    public void saveArticle(ArticleForm articleForm) {
        Article article = new Article();
        article.setArticleUUID(articleForm.getArticleUUID());
        article.setArticleHeader(articleForm.getArticleHeader());
        article.setArticleBody(articleForm.getArticleBody());
        article.setStatus("OPEN");
        article.setCreateDate(new Timestamp(System.currentTimeMillis()));
        article.setAuthorRole(articleForm.getAuthorRole());
        article.setCreateByEmail(articleForm.getAuthorEmail());
        article.setPicturePath(null);
        articleRepo.save(article);
    }


}