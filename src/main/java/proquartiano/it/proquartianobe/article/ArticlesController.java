package proquartiano.it.proquartianobe.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
    @Autowired
    private ArticlesService articlesService;
    @GetMapping("")
    public Page<Article> getArticles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "date") String orderBy){
        return articlesService.getArticles(page, size, orderBy);
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Article saveArticle(@RequestBody Article body){
        return articlesService.save(body);
    }

    @GetMapping("/{id}")
    public Article findById(@PathVariable UUID id){
        return articlesService.findById(id);
    }

    @PutMapping("/{id}")
    public Article findByIdAndUpdate(@PathVariable UUID id, @RequestBody Article body){
        return articlesService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){
        articlesService.findByIdAndDelete(id);
    }
}
