package proquartiano.it.proquartianobe.entities.articles;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import proquartiano.it.proquartianobe.entities.articles.payload.NewArticleDTO;
import proquartiano.it.proquartianobe.exceptions.NotFoundException;
import proquartiano.it.proquartianobe.entities.admins.AdminsRepository;
import proquartiano.it.proquartianobe.entities.categories.CategoriesRepository;
import proquartiano.it.proquartianobe.entities.tags.TagsRepository;

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
    public Article save(NewArticleDTO body, MultipartFile img) throws IOException {
        Article newArticle = new Article();
        newArticle.setAuthor(adminsRepo.findById(body.authorId()).orElseThrow(() -> new NotFoundException(body.authorId())));
        newArticle.setContent(body.content());
        newArticle.setTitle(body.title());
        newArticle.setCategories(body.categories().stream().map(categoryName -> categoriesRepo.findByName(categoryName).orElseThrow(() -> new NotFoundException(categoryName))).toList());
        newArticle.setTags(body.tags().stream().map(tagName -> tagsRepo.findByName(tagName).orElseThrow(() -> new NotFoundException(tagName))).toList());
        newArticle.setImg((String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url"));
//        newArticle.setPdf(body.pdf());
        return articlesRepo.save(newArticle);
    }

    public String uploadImage(MultipartFile file) throws IOException {
        return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }

    @Override
    public Page<Article> getArticles(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        return articlesRepo.findAll(pageable);
    }

    @Override
    public Page<Article> findByTitleContainingIgnoreCase(String query, int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        return articlesRepo.findByTitleContainingIgnoreCase(query, pageable);
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
