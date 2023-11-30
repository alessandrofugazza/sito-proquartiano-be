package proquartiano.it.proquartianobe.entities.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proquartiano.it.proquartianobe.entities.tags.payload.NewTagDTO;

import java.util.List;

@Service
public class TagsService implements ITagsDAO {
    @Autowired
    private TagsRepository tagsRepo;

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
//
//    @Override
//    public void delete(Tag tag) {
//
//    }
//
//    @Override
//    public void edit(Tag tag) {
//
//    }
}
