package proquartiano.it.proquartianobe.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagsService implements ITagsDAO {
    @Autowired
    private TagsRepository tagsRepo;

//    @Override
//    public Tag save(Tag tag) {
//        return tagsRepo.save(tag);
//    }
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
