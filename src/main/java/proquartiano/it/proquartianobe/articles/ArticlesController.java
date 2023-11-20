package proquartiano.it.proquartianobe.articles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import proquartiano.it.proquartianobe.exceptions.BadRequestException;
import proquartiano.it.proquartianobe.articles.payload.NewArticleDTO;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/articles")
@CrossOrigin
public class ArticlesController {
    @Autowired
    private ArticlesService articlesService;

    @GetMapping("")
    public Page<Article> getArticles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "date") String orderBy) {
        return articlesService.getArticles(page, size, orderBy);
    }

    @GetMapping("/search")
    public Page<Article> findByTitleContainingIgnoreCase(@RequestParam String q, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "date") String orderBy) {
        return articlesService.findByTitleContainingIgnoreCase(q, page, size, orderBy);
    }


    @PostMapping(value = "", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public Article saveArticle(@RequestPart("article") @Validated NewArticleDTO body, @RequestParam("img") MultipartFile img, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return articlesService.save(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/{id}")
    public Article findById(@PathVariable UUID id) {
        return articlesService.findById(id);
    }

//    @PutMapping("/{id}")
//    public Article findByIdAndUpdate(@PathVariable UUID id, @RequestBody NewArticleDTO body) {
//        return articlesService.findByIdAndUpdate(id, body);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) {
        articlesService.findByIdAndDelete(id);
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile body) throws IOException {
        return articlesService.uploadImage(body);
    }


}
