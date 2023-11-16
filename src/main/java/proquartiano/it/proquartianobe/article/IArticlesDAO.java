package proquartiano.it.proquartianobe.article;

import org.springframework.data.domain.Page;
import proquartiano.it.proquartianobe.article.Article;

import java.util.UUID;

public interface IArticlesDAO {
 public Article save(Article article);

 public Page<Article> getArticles(int page, int size, String orderBy);
//// public List<Article> findByCategory(String categoryName);

 public void findByIdAndDelete(UUID id);

public Article findByIdAndUpdate(UUID id, Article article);
 public Article findById(UUID id);
}
