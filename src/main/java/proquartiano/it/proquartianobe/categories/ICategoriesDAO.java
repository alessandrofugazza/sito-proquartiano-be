package proquartiano.it.proquartianobe.categories;


import proquartiano.it.proquartianobe.categories.payload.NewCategoryDTO;

import java.util.List;

public interface ICategoriesDAO {
    public Category save(NewCategoryDTO category);

    public List<Category> findAll();

//    public void delete(Category category);
//
//    public void edit(Category category);
}
