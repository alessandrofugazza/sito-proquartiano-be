package proquartiano.it.proquartianobe.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import proquartiano.it.proquartianobe.dao.IArticlesDAO;
import proquartiano.it.proquartianobe.payload.articles.NewArticleDTO;
import proquartiano.it.proquartianobe.entities.Article;
import proquartiano.it.proquartianobe.exceptions.NotFoundException;
import proquartiano.it.proquartianobe.repositories.AdminsRepository;
import proquartiano.it.proquartianobe.repositories.ArticlesRepository;
import proquartiano.it.proquartianobe.repositories.CategoriesRepository;
import proquartiano.it.proquartianobe.repositories.TagsRepository;

import java.io.IOException;
import java.util.UUID;

@Service
public class ArticlesService implements IArticlesDAO {
    @Autowired
    private ArticlesRepository articlesRepo;
    @Autowired
    private AdminsRepository adminsRepo;
    @Autowired
    private CategoriesRepository categoriesRepo;
    @Autowired
    private TagsRepository tagsRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Article save(NewArticleDTO body) {
        Article newArticle = new Article();
        newArticle.setAuthor(adminsRepo.findById(body.authorId()).orElseThrow(() -> new NotFoundException(body.authorId())));
        newArticle.setContent(body.content());
        newArticle.setTitle(body.title());
        newArticle.setCategories(body.categoryIds().stream().map(categoryId -> categoriesRepo.findById(categoryId).orElseThrow(() -> new NotFoundException(categoryId))).toList());
        newArticle.setTags(body.tagIds().stream().map(tagId -> tagsRepo.findById(tagId).orElseThrow(() -> new NotFoundException(tagId))).toList());
//        newArticle.setTags(body.tagIds());
        return articlesRepo.save(newArticle);
    }

    @Override
    public Page<Article> getArticles(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        return articlesRepo.findAll(pageable);
    }

    @Override
    public Article findById(UUID id) throws NotFoundException {
        return articlesRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Article found = this.findById(id);
        articlesRepo.delete(found);
    }

//    @Override
//    public Article findByIdAndUpdate(UUID id, NewArticleDTO body) throws NotFoundException {
//        Article found = this.findById(id);
//        found.setAuthor(body.author());
//        found.setContent(body.content());
//        found.setTitle(body.title());
//        return articlesRepo.save(found);
//    }

    public String uploadImage(MultipartFile file) throws IOException {
        return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }

//    @Override
//    public List<Article> findByCategory(String categoryName) {
//        return articlesRepo.findByCategory(categoryName);
//    }
//
//    @Override
//    public void deleteById(UUID id) {
//
//    }
//
//    @Override
//    public List<Article> findAll() {
//        return (List<Article>) articlesRepo.findAll();
//    }
//
//    @Override
//    public Article findById(UUID id) throws ItemNotFoundException {
//        return articlesRepo.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
//    }


}
