package proquartiano.it.proquartianobe.entities.tags;


import proquartiano.it.proquartianobe.entities.tags.payload.NewTagDTO;

public interface ITagsDAO {
    public Tag save(NewTagDTO tag);

//    public void delete(Tag tag);
//
//    public void edit(Tag tag);
}
