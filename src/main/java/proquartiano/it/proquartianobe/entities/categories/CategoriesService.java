package proquartiano.it.proquartianobe.entities.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proquartiano.it.proquartianobe.entities.categories.payload.NewCategoryDTO;

import java.util.List;

@Service
public class CategoriesService implements ICategoriesDAO {
    @Autowired
    private CategoriesRepository categoriesRepo;

    @Override
    public Category save(NewCategoryDTO body) {
        Category newCategory = new Category();
        newCategory.setName(body.name());
        return categoriesRepo.save(newCategory);
    }

    @Override
    public List<Category> findAll() {
        return categoriesRepo.findAll();
    }
//
//    @Override
//    public void delete(Category category) {
//        categoriesRepo.delete(category);
//    }
//
//    @Override
//    public void edit(Category category) {
//
//    }
}
