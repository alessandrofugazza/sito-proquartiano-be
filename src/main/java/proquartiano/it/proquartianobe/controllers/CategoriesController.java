package proquartiano.it.proquartianobe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import proquartiano.it.proquartianobe.entities.Category;
import proquartiano.it.proquartianobe.payload.categories.NewCategoryDTO;
import proquartiano.it.proquartianobe.services.CategoriesService;

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
