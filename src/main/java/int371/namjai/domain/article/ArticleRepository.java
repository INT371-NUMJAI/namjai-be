package int371.namjai.domain.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    List<Article> findAllByOrderByCreateDateDesc();

    List<Article> findArticlesByUser_EmailOrderByCreateDateAsc(String email);

    @Query(value = "select * from articles a where a.status ='OPEN' and a.create_date >='2022-11-20 00:00:00' and a.create_date<'2022-11-21 00:00:00' order by a.create_date desc limit 3 ", nativeQuery = true)
    List<Article> findTop3AndStatusOpen();
}
