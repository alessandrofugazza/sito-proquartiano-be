package proquartiano.it.proquartianobe.entities.articles;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import proquartiano.it.proquartianobe.entities.admins.Admin;
import proquartiano.it.proquartianobe.entities.articles.payload.NewArticleDTO;
import proquartiano.it.proquartianobe.exceptions.NotFoundException;
import proquartiano.it.proquartianobe.entities.admins.AdminsRepository;
import proquartiano.it.proquartianobe.entities.categories.CategoriesRepository;
import proquartiano.it.proquartianobe.entities.tags.TagsRepository;
import proquartiano.it.proquartianobe.security.JWTTools;

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

    @Autowired
    private JWTTools jwtTools;

    public Admin getAuthorFromToken(String token) {
        UUID adminId = UUID.fromString(jwtTools.extractIdFromToken(token));
        return adminsRepo.findById(adminId)
                .orElseThrow(() -> new NotFoundException("Admin not found with ID: " + adminId));
    }

    @Override
    public Article save(NewArticleDTO body, MultipartFile img, Admin currentAdmin) throws IOException {
        Article newArticle = new Article();

        newArticle.setAuthor(currentAdmin);
        newArticle.setContent(body.content());
        newArticle.setTitle(body.title());
        newArticle.setCategories(body.categories().stream().map(categoryName -> categoriesRepo.findByName(categoryName).orElseThrow(() -> new NotFoundException(categoryName))).toList());
        newArticle.setTags(body.tags().stream().map(tagName -> tagsRepo.findByName(tagName).orElseThrow(() -> new NotFoundException(tagName))).toList());
        newArticle.setImg((String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url"));
//        newArticle.setPdf(body.pdf());
        return articlesRepo.save(newArticle);
    }

    @Override
    @Transactional
    public Article findByIdAndUpdate(UUID id, NewArticleDTO body, MultipartFile img) throws NotFoundException, IOException {
        // TODO: only change actually modified fields
        Article found = this.findById(id);
        found.setContent(body.content());
        found.setTitle(body.title());
        found.getCategories().clear();
        found.getCategories().addAll(
                body.categories().stream()
                        .map(categoryName -> categoriesRepo.findByName(categoryName)
                                .orElseThrow(() -> new NotFoundException(categoryName)))
                        .toList()
        );

        found.getTags().clear();
        found.getTags().addAll(
                body.tags().stream()
                        .map(tagName -> tagsRepo.findByName(tagName)
                                .orElseThrow(() -> new NotFoundException(tagName)))
                        .toList()
        );
        String imgUrl = found.getImg();
        String[] parts = imgUrl.split("/");
        String imgId = parts[parts.length - 1].split("\\.")[0];
        cloudinary.uploader().destroy(imgId, ObjectUtils.emptyMap());

        found.setImg((String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url"));
        return articlesRepo.save(found);
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
    public Page<Article> getArticlesByCategory(String category, int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        return articlesRepo.findByCategories_Name(category, pageable);
    }

    @Override
    public Page<Article> getArticlesByTag(String tag, int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        return articlesRepo.findByTags_Name(tag, pageable);
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
