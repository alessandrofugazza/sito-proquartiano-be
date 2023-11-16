package proquartiano.it.proquartianobe.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService implements ICategoriesDAO {
    @Autowired
    private CategoriesRepository categoriesRepo;

    @Override
    public Category save(Category category) {
        return categoriesRepo.save(category);
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
