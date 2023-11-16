package proquartiano.it.proquartianobe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import proquartiano.it.proquartianobe.article.IArticlesDAO;
import proquartiano.it.proquartianobe.category.ICategoriesDAO;
import proquartiano.it.proquartianobe.tag.ITagsDAO;

@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private ICategoriesDAO categoriesDAO;
    @Autowired
    private IArticlesDAO articlesDAO;
    @Autowired
    private ITagsDAO tagsDAO;

    @Override
    public void run(String... args) throws Exception {
//        Category categoryTest1 = Category.builder().build();
//        Category categoryTest2 = Category.builder().build();
//        Category categoryTest3 = Category.builder().build();
//        Category categoryTest4 = Category.builder().build();
//
//        categoriesDAO.save(categoryTest1);
//        categoriesDAO.save(categoryTest2);
//        categoriesDAO.save(categoryTest3);
//        categoriesDAO.save(categoryTest4);
//        Tag tagTest1 = Tag.builder().build();
//        Tag tagTest2 = Tag.builder().build();
//        Category tagTest3 = Category.builder().build();
//        Category tagTest4 = Category.builder().build();
//
//        tagsDAO.save(tagTest1);
//        tagsDAO.save(tagTest2);
//        categoriesDAO.save(categoryTest3);
//        categoriesDAO.save(categoryTest4);
//
//        Article articleTest1 = Article.builder().build();
//        Article articleTest2 = Article.builder().build();
//        Article articleTest3 = Article.builder().build();
//        Article articleTest4 = Article.builder().build();
//        Article articleTest5 = Article.builder().build();
//        Article articleTest6 = Article.builder().build();
//        Article articleTest7 = Article.builder().build();
//        Article articleTest8 = Article.builder().build();
//        Article articleTest9 = Article.builder().build();
//        Article articleTest10 = Article.builder().build();
//        Article articleTest11 = Article.builder().build();
//        Article articleTest12 = Article.builder().build();
//        Article articleTest13 = Article.builder().build();
//        Article articleTest14 = Article.builder().build();
//
//        articleTest1.getCategories().add(categoryTest1);
//        articleTest2.getCategories().add(categoryTest2);
//        articleTest3.getCategories().add(categoryTest1);
//
//        articleTest1.getTags().add(tagTest1);
//        articleTest2.getTags().add(tagTest2);
//        articleTest3.getTags().add(tagTest1);
//        articleTest4.getCategories().add(categoryTest4);
//        articleTest5.getCategories().add(categoryTest2);
//        articleTest6.getCategories().add(categoryTest1);
//        articleTest7.getCategories().add(categoryTest1);
//        articleTest8.getCategories().add(categoryTest4);
//        articleTest9.getCategories().add(categoryTest4);
//        articleTest10.getCategories().add(categoryTest3);
//        articleTest11.getCategories().add(categoryTest2);
//        articleTest12.getCategories().add(categoryTest3);
//        articleTest13.getCategories().add(categoryTest1);
//        articleTest14.getCategories().add(categoryTest2);
//        articleTest1.getCategories().add(categoryTest4);
//        articleTest2.getCategories().add(categoryTest4);
//        articleTest3.getCategories().add(categoryTest2);
//        articleTest4.getCategories().add(categoryTest1);
//        articleTest5.getCategories().add(categoryTest1);
//        articleTest6.getCategories().add(categoryTest4);
//        articleTest7.getCategories().add(categoryTest3);
//        articleTest8.getCategories().add(categoryTest1);
//        articleTest9.getCategories().add(categoryTest2);
//        articleTest10.getCategories().add(categoryTest1);
//
//        articlesDAO.save(articleTest1);
//        articlesDAO.save(articleTest2);
//        articlesDAO.save(articleTest3);
//        articlesDAO.save(articleTest4);
//        articlesDAO.save(articleTest5);
//        articlesDAO.save(articleTest6);
//        articlesDAO.save(articleTest7);
//        articlesDAO.save(articleTest8);
//        articlesDAO.save(articleTest9);
//        articlesDAO.save(articleTest10);
//        articlesDAO.save(articleTest11);
//        articlesDAO.save(articleTest12);
//        articlesDAO.save(articleTest13);
//        articlesDAO.save(articleTest14);
//        articlesDAO.getArticles().forEach(System.out::println);
    }
}
