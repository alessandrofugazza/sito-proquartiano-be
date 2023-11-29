package proquartiano.it.proquartianobe.entities.articles;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import proquartiano.it.proquartianobe.entities.admins.Admin;
import proquartiano.it.proquartianobe.entities.articles.payload.NewArticleDTO;
import proquartiano.it.proquartianobe.exceptions.BadRequestException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/articoli")
public class ArticlesController {
    @Autowired
    private ArticlesService articlesService;

    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping("")
    public Page<Article> getArticles(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "date") String orderBy,
                                     @RequestParam(required = false) String categoria,
                                     @RequestParam(required = false) String tag,
                                     @RequestParam(required = false) String autore
    ) {
        // todo better syntax
        // todo multiple search params if needed
        if (categoria != null) {
            return articlesService.getArticlesByCategory(categoria, page, size, orderBy);
        } else if (tag != null) {
            return articlesService.getArticlesByTag(tag, page, size, orderBy);
        } else if (autore != null) {
            return articlesService.getArticlesByAuthor(autore, page, size, orderBy);
        } else {
            return articlesService.getArticles(page, size, orderBy);
        }
    }

    @GetMapping("/search")
    public Page<Article> findByTitleContainingIgnoreCase(@RequestParam String q, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "date") String orderBy) {
        return articlesService.findByTitleContainingIgnoreCase(q, page, size, orderBy);
    }


    @PostMapping(value = "", consumes = "multipart/form-data")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Article saveArticle(
            @RequestPart("article") @Validated String articleJson,
            @RequestParam(value = "img", required = false) MultipartFile img,
            @RequestParam(value = "pdf", required = false) MultipartFile pdf,
            @AuthenticationPrincipal Admin currentAdmin,
            BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                NewArticleDTO article = objectMapper.readValue(articleJson, NewArticleDTO.class);
                return articlesService.save(article, img, pdf, currentAdmin);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public Article findByIdAndUpdate(@PathVariable UUID id, @RequestPart("article") @Validated String articleJson, @RequestParam(value = "img", required = false) MultipartFile img, MultipartFile pdf, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                NewArticleDTO article = objectMapper.readValue(articleJson, NewArticleDTO.class);
                return articlesService.findByIdAndUpdate(id, article, img, pdf);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/{id}")
    public Article findById(@PathVariable UUID id) {
        return articlesService.findById(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) {
        articlesService.findByIdAndDelete(id);
    }

//    @PostMapping("/upload")
//    public String uploadImage(@RequestParam("image") MultipartFile body) throws IOException {
//        return articlesService.uploadImage(body);
//    }


}
