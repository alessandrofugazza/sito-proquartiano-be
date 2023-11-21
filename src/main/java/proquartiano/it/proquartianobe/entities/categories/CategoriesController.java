package proquartiano.it.proquartianobe.entities.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import proquartiano.it.proquartianobe.entities.categories.payload.NewCategoryDTO;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("")
    public List<Category> getCategories() {
        return categoriesService.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Category saveCategory(@RequestBody NewCategoryDTO body) {
        return categoriesService.save(body);
    }


}
