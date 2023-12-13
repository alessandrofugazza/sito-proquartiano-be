package proquartiano.it.proquartianobe.entities.articles;

import org.springframework.data.domain.Page;
import proquartiano.it.proquartianobe.entities.admins.Admin;
import proquartiano.it.proquartianobe.entities.articles.payload.NewArticleDTO;
import org.springframework.web.multipart.MultipartFile;
import proquartiano.it.proquartianobe.entities.sections.Section;
import proquartiano.it.proquartianobe.enums.ESection;
import proquartiano.it.proquartianobe.exceptions.NotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IArticlesDAO {
    public Article save(NewArticleDTO article, MultipartFile[] img, MultipartFile[] pdf, Admin currentAdmin) throws IOException;

    Article findByIdAndUpdate(UUID id, NewArticleDTO body, MultipartFile[] img, MultipartFile[] pdf) throws NotFoundException, IOException;

//    Page<Article> getArticles(String category, String tag, String author, int page, int size, String orderBy);

//    Page<Article> getArticlesBySection(Section section, int page, int size, String orderBy);

    Page<Article> getArticles(String category, String tag, String author, String sectionName, int page, int size, String orderBy);


    Page<Article> getUpcomingEvents(String sectionName, int page, int size, String orderBy);


    List<Article> getUpcomingEventsWithinOneYear();

    //// public List<Article> findByCategory(String categoryName);
    public Page<Article> findByTitleContainingIgnoreCase(String query, int page, int size, String orderBy);

    public void findByIdAndDelete(UUID id);

//    public Article findByIdAndUpdate(UUID id, NewArticleDTO article);

    public Article findById(UUID id);
}
