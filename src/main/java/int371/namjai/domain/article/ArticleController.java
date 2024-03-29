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

    @GetMapping(value = "/articles/top-3")
    public ResponseEntity<List<ArticleShortDTO>> getTop3ArticleShortList() {
        return ResponseEntity.ok().body(articleService.getTop3Articles());
    }

    @GetMapping(value = "/article/{id}")
    public ResponseEntity<ArticleShortDTO> getArticleShortByUUID(@PathVariable("id") String articleUUIID) {
        return ResponseEntity.ok().body(articleService.getArticleShortDTOByUUID(articleUUIID));
    }

    @GetMapping(value = "/article-email/{email}")
    public ResponseEntity<List<ArticleShortDTO>> getArticleShortByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok().body(articleService.getArticlesByEmail(email));
    }

}
