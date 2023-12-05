package proquartiano.it.proquartianobe.entities.tags;


import proquartiano.it.proquartianobe.entities.tags.payload.NewTagDTO;
import proquartiano.it.proquartianobe.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ITagsDAO {
    public Tag save(NewTagDTO tag);

    List<String> getMostUsed();

    void findByIdAndDelete(UUID id) throws NotFoundException;

//    public void delete(Tag tag);
//
//    public void edit(Tag tag);
}
