package proquartiano.it.proquartianobe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proquartiano.it.proquartianobe.dao.ICategoriesDAO;
import proquartiano.it.proquartianobe.entities.Category;
import proquartiano.it.proquartianobe.payload.categories.NewCategoryDTO;
import proquartiano.it.proquartianobe.repositories.CategoriesRepository;

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
