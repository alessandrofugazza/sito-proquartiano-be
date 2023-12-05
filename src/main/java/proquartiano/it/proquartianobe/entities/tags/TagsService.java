package proquartiano.it.proquartianobe.entities.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proquartiano.it.proquartianobe.entities.articles.Article;
import proquartiano.it.proquartianobe.entities.articles.ArticlesRepository;
import proquartiano.it.proquartianobe.entities.tags.payload.NewTagDTO;
import proquartiano.it.proquartianobe.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class TagsService implements ITagsDAO {
    @Autowired
    private TagsRepository tagsRepo;
    @Autowired
    private ArticlesRepository articlesRepo;

    @Override
    public Tag save(NewTagDTO body) {
        Tag newTag = new Tag();
        newTag.setName(body.name());
        return tagsRepo.save(newTag);
    }

    @Override
    public List<String> getMostUsed() {
        return tagsRepo.findMostUsed();
    }

    @Override
    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Article article = articlesRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

        for (Tag tag : article.getTags()) {
            tag.getArticles().remove(article);
            tagsRepo.save(tag);
        }

        articlesRepo.delete(article);
    }
//
//    @Override
//    public void edit(Tag tag) {
//
//    }
}
