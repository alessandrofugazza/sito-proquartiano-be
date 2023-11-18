package proquartiano.it.proquartianobe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proquartiano.it.proquartianobe.entities.Tag;
import proquartiano.it.proquartianobe.repositories.TagsRepository;
import proquartiano.it.proquartianobe.dao.ITagsDAO;

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
