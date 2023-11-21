package proquartiano.it.proquartianobe.entities.categories;


import proquartiano.it.proquartianobe.entities.categories.payload.NewCategoryDTO;

import java.util.List;

public interface ICategoriesDAO {
    public Category save(NewCategoryDTO category);

    public List<Category> findAll();

//    public void delete(Category category);
//
//    public void edit(Category category);
}
