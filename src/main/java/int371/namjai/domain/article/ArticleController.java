package int371.namjai.domain.article;

import int371.namjai.domain.article.dto.ArticleForm;
import int371.namjai.domain.article.dto.ArticleShortDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/view")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/article/create")
    public ResponseEntity<Void> createArticle(@RequestBody ArticleForm articleForm) {
        articleService.saveArticle(articleForm);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/articles")
    public ResponseEntity<List<ArticleShortDTO>> getArticleShortList() {
        return ResponseEntity.ok().body(articleService.getArticleShortDTO());
    }

}
