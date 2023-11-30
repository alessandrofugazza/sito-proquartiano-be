package proquartiano.it.proquartianobe.entities.tags;


import proquartiano.it.proquartianobe.entities.tags.payload.NewTagDTO;

import java.util.List;

public interface ITagsDAO {
    public Tag save(NewTagDTO tag);

    List<String> getMostUsed();

//    public void delete(Tag tag);
//
//    public void edit(Tag tag);
}
