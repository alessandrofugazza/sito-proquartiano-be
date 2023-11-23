package proquartiano.it.proquartianobe.entities.articles;

import org.springframework.data.domain.Page;
import proquartiano.it.proquartianobe.entities.admins.Admin;
import proquartiano.it.proquartianobe.entities.articles.payload.NewArticleDTO;
import org.springframework.web.multipart.MultipartFile;
import proquartiano.it.proquartianobe.exceptions.NotFoundException;

import java.io.IOException;
import java.util.UUID;

public interface IArticlesDAO {
    public Article save(NewArticleDTO article, MultipartFile img, Admin currentAdmin) throws IOException;

    Article findByIdAndUpdate(UUID id, NewArticleDTO body, MultipartFile img) throws NotFoundException, IOException;

    public Page<Article> getArticles(int page, int size, String orderBy);

    //// public List<Article> findByCategory(String categoryName);
    public Page<Article> findByTitleContainingIgnoreCase(String query, int page, int size, String orderBy);

    public void findByIdAndDelete(UUID id);

//    public Article findByIdAndUpdate(UUID id, NewArticleDTO article);

    public Article findById(UUID id);
}
