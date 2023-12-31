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
import proquartiano.it.proquartianobe.entities.categories.Category;
import proquartiano.it.proquartianobe.entities.sections.SectionsRepository;
import proquartiano.it.proquartianobe.entities.tags.Tag;
import proquartiano.it.proquartianobe.enums.ESection;
import proquartiano.it.proquartianobe.exceptions.NotFoundException;
import proquartiano.it.proquartianobe.entities.admins.AdminsRepository;
import proquartiano.it.proquartianobe.entities.categories.CategoriesRepository;
import proquartiano.it.proquartianobe.entities.tags.TagsRepository;
import proquartiano.it.proquartianobe.security.JWTTools;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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
    private SectionsRepository sectionsRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JWTTools jwtTools;

    @Override
    public Article save(NewArticleDTO body, MultipartFile[] img, MultipartFile[] pdf, Admin currentAdmin) throws IOException {
        Article newArticle = new Article();

        newArticle.setAuthor(currentAdmin);
        newArticle.setContent(body.content());
        newArticle.setTitle(body.title());
        // todo not proud of this either
        if (body.eventDate() != null) {
            newArticle.setEventDate(body.eventDate());
        } else {
            newArticle.setEventDate(null);
        }
        if (body.section() != null) {
            String sectionString = body.section();
            // todo this is really, really bad. find a better way to handle this.
            ESection section = switch (sectionString) {
                case "Mercatino dei libri" -> ESection.MERCATINO_LIBRI;
                case "Sagra di Quartiano" -> ESection.SAGRA;
                case "Concorso corale" -> ESection.CONCORSO_CORI;
                default -> null;
            };
            // todo also really bad
            if (section != null) {
                newArticle.setSection(sectionsRepo.findByName(section).orElseThrow(() -> new NotFoundException(section.name())));
            } else {
                newArticle.setSection(null);
            }
        }
        newArticle.setCategories(body.categories().stream().map(categoryName -> categoriesRepo.findByName(categoryName).orElseThrow(() -> new NotFoundException(categoryName))).toList());
        List<Tag> tagsToAdd = new ArrayList<>();
        body.tags().forEach(tagName -> {
            Optional<Tag> foundTag = tagsRepo.findByName(tagName);
            if (foundTag.isPresent()) {
                tagsToAdd.add(foundTag.get());
            } else {
                Tag newTag = new Tag();
                newTag.setName(tagName);
                Tag savedTag = tagsRepo.save(newTag);
                tagsToAdd.add(savedTag);

            }
        });
        newArticle.setTags(tagsToAdd);
        if (img != null && img.length > 0) {
            List<String> imgUrls = new ArrayList<>();

            for (MultipartFile file : img) {
                if (!file.isEmpty()) {
                    imgUrls.add(((String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url")));
                }
            }
            newArticle.setImg(imgUrls.toArray(new String[0]));
        }
        if (pdf != null && pdf.length > 0) {
            List<String> pdfUrls = new ArrayList<>();

            for (MultipartFile file : pdf) {
                if (!file.isEmpty()) {
                    pdfUrls.add(((String) cloudinary.uploader().upload(file.getBytes(),
                            ObjectUtils.asMap("resource_type", "auto", "format", "pdf")).get("url")));
                }
            }
            newArticle.setPdf(pdfUrls.toArray(new String[0]));
        }
//            newArticle.setPdf(cloudinary.uploader().upload(pdf.getInputStream(), ObjectUtils.asMap("resource_type", "raw")).get("url").toString());
        return articlesRepo.save(newArticle);
    }

    @Override
    @Transactional
    public Article findByIdAndUpdate(UUID id, NewArticleDTO body, MultipartFile[] img, MultipartFile[] pdf) throws NotFoundException, IOException {
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
        // todo this
        String[] existingImgUrls = found.getImg();
        if (existingImgUrls != null) {
            for (String imgUrl : existingImgUrls) {
                String[] parts = imgUrl.split("/");
                String imgId = parts[parts.length - 1].split("\\.")[0];
                cloudinary.uploader().destroy(imgId, ObjectUtils.emptyMap());
            }
        }
        // q what is even the point of this check?
        if (img != null && img.length > 0) {
            List<String> imgUrls = new ArrayList<>();

            for (MultipartFile file : img) {
                if (!file.isEmpty()) {
                    imgUrls.add(((String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url")));
                }
            }
            found.setImg(imgUrls.toArray(new String[0]));
        } else {
            found.setImg(null);
        }
        String[] existingPdfUrls = found.getPdf();
        if (existingPdfUrls != null) {
            for (String pdfUrl : existingPdfUrls) {
                String[] parts = pdfUrl.split("/");
                String pdfId = parts[parts.length - 1].split("\\.")[0];
                cloudinary.uploader().destroy(pdfId, ObjectUtils.emptyMap());
            }
        }
        if (pdf != null && pdf.length > 0) {
            List<String> pdfUrls = new ArrayList<>();

            for (MultipartFile file : pdf) {
                if (!file.isEmpty()) {
                    pdfUrls.add(((String) cloudinary.uploader().upload(file.getBytes(),
                            ObjectUtils.asMap("resource_type", "auto", "format", "pdf")).get("url")));
                }
            }
            found.setPdf(pdfUrls.toArray(new String[0]));
        } else {
            found.setPdf(null);
        }
        return articlesRepo.save(found);
    }

//    public String uploadImage(MultipartFile file) throws IOException {
//        return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
//    }

    @Override
    public Page<Article> getArticles(String category, String tag, String author, String sectionName, int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        if (category != null) {
            return articlesRepo.findByCategories_Name(category, pageable);
        } else if (tag != null) {
            return articlesRepo.findByTags_Name(tag, pageable);
        } else if (author != null) {
            return articlesRepo.findByAuthor_Username(author, pageable);
        } else if (sectionName != null) {
            ESection section = switch (sectionName) {
                // q did i break something?
                case "mercatino-dei-libri" -> ESection.MERCATINO_LIBRI;
                case "sagra" -> ESection.SAGRA;
                case "concorso-corale" -> ESection.CONCORSO_CORI;
                default -> null;
            };
            return articlesRepo.findBySection(section, pageable);
        } else {
            return articlesRepo.findAll(pageable);
        }
    }

    @Override
    public Page<Article> getUpcomingEvents(String sectionName, int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, orderBy));
        if (sectionName != null) {
            ESection section = switch (sectionName) {
                case "mercatino-dei-libri" -> ESection.MERCATINO_LIBRI;
                case "sagra" -> ESection.SAGRA;
                case "concorso-corale" -> ESection.CONCORSO_CORI;
                default -> null;
            };
            return articlesRepo.findWithEventDateBySection(section, pageable);
        } else {
            return articlesRepo.findWithEventDate(pageable);
        }
    }

    @Override
    public List<Article> getUpcomingEventsWithinOneYear() {
        LocalDate oneYearFromNow = LocalDate.now().plusYears(1);
        return articlesRepo.findUpcomingEventsWithinOneYear(oneYearFromNow);
    }

//    @Override
//    public Page<Article> getArticlesBySection(Section section, int page, int size, String orderBy) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
//        return articlesRepo.findBySection(section, pageable);
//    }

    @Override
    public Page<Article> findByTitleCategoriesTagsContainingIgnoreCase(String query, int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        return articlesRepo.findByTitleCategoriesTagsContainingIgnoreCase(query, pageable);
    }

    @Override
    public Article findById(UUID id) throws NotFoundException {
        return articlesRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public void findByIdAndDelete(UUID id) throws NotFoundException, IOException {
        Article article = articlesRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

        for (Category category : article.getCategories()) {
            category.getArticles().remove(article);
            categoriesRepo.save(category);
        }
        for (Tag tag : article.getTags()) {
            tag.getArticles().remove(article);
            tagsRepo.save(tag);
        }

        // q should be empty array instead of null?
        // q is being a web developer worse for your health than smoking?
        String[] existingImgUrls = article.getImg();
        if (existingImgUrls != null) {
            for (String imgUrl : existingImgUrls) {
                String[] parts = imgUrl.split("/");
                String imgId = parts[parts.length - 1].split("\\.")[0];
                cloudinary.uploader().destroy(imgId, ObjectUtils.emptyMap());
            }
        }

        String[] existingPdfUrls = article.getPdf();
        if (existingPdfUrls != null) {
            for (String pdfUrl : existingPdfUrls) {
                String[] parts = pdfUrl.split("/");
                String pdfId = parts[parts.length - 1].split("\\.")[0];
                cloudinary.uploader().destroy(pdfId, ObjectUtils.emptyMap());
            }
        }

        articlesRepo.delete(article);
    }

//    @Override
//    public void findByIdAndDelete(UUID id) throws NotFoundException {
//        Article found = this.findById(id);
//        articlesRepo.delete(found);
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
