package int371.namjai.domain.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    List<Article> findAllByOrderByCreateDateDesc();

    List<Article> findArticlesByCreateByEmailIgnoreCaseOrderByCreateDateDesc(String email);
}
