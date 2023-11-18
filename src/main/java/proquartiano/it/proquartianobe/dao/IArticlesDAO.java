package proquartiano.it.proquartianobe.dao;

import org.springframework.data.domain.Page;
import proquartiano.it.proquartianobe.payload.articles.NewArticleDTO;
import proquartiano.it.proquartianobe.entities.Article;

import java.util.UUID;

public interface IArticlesDAO {
    public Article save(NewArticleDTO article);

    public Page<Article> getArticles(int page, int size, String orderBy);
//// public List<Article> findByCategory(String categoryName);

    public void findByIdAndDelete(UUID id);

//    public Article findByIdAndUpdate(UUID id, NewArticleDTO article);

    public Article findById(UUID id);
}
