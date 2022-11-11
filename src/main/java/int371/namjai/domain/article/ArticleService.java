package int371.namjai.domain.article;

import int371.namjai.domain.article.dto.ArticleForm;
import int371.namjai.domain.article.dto.ArticleMapper;
import int371.namjai.domain.article.dto.ArticleShortDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

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

    public List<ArticleShortDTO> getArticleShortDTO() {
        return ArticleMapper.INSTANCE.toArticleShortDTOList(articleRepo.findAllByOrderByCreateDateDesc());
    }

    public List<ArticleShortDTO> getArticlesByEmail(String email) {
        return ArticleMapper.INSTANCE.toArticleShortDTOList(articleRepo.findArticlesByCreateByEmailIgnoreCaseOrderByCreateDateDesc(email));
    }

    public ArticleShortDTO getArticleShortDTOByUUID(String articleUUID) {
        return ArticleMapper.INSTANCE.toArticleShortDto(getArticleByID(articleUUID));
    }

    public Article getArticleByID(String articleUUID) {
        return articleRepo.findById(articleUUID).orElseThrow(ArticleNotfoundException::new);
    }

    public void saveToTableArticle(Article article) {
        articleRepo.save(article);
    }

}
