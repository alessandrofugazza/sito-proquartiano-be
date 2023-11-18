package proquartiano.it.proquartianobe.dao;


import proquartiano.it.proquartianobe.entities.Category;
import proquartiano.it.proquartianobe.payload.categories.NewCategoryDTO;

import java.util.List;

public interface ICategoriesDAO {
    public Category save(NewCategoryDTO category);

    public List<Category> findAll();

//    public void delete(Category category);
//
//    public void edit(Category category);
}
