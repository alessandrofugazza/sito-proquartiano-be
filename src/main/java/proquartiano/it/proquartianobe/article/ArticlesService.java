package proquartiano.it.proquartianobe.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import proquartiano.it.proquartianobe.exceptions.NotFoundException;

import java.util.UUID;

@Service
public class ArticlesService implements IArticlesDAO {
    @Autowired
    private ArticlesRepository articlesRepo;

    @Override
    public Article save(Article body) {
        return articlesRepo.save(body);
    }

    @Override
    public Page<Article> getArticles(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        return articlesRepo.findAll(pageable);
    }
    @Override
    public Article findById(UUID id) throws NotFoundException {
        return articlesRepo.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    @Override
    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Article found = this.findById(id);
        articlesRepo.delete(found);
    }

    @Override
    public Article findByIdAndUpdate(UUID id, Article body) throws NotFoundException {
        Article found = this.findById(id);
        found.setAuthor(body.getAuthor());
        found.setDate(body.getDate());
        found.setContent(body.getContent());
        found.setTitle(body.getTitle());
        return articlesRepo.save(found);
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
