package tn.malek.Ecommerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.malek.Ecommerce.models.Category;
import tn.malek.Ecommerce.repositories.CategoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("categories")
public class CategoryController {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @PostMapping(path = "add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){

        System.out.println(category.name);

        Category savedCategory = this.categoryRepository.save(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping(path="all")
    public  ResponseEntity<List<Category>>getAllCategories()
    {
        List<Category>categories=this.categoryRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
    @GetMapping(path = "one/{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Integer id) {
        // try {
        Optional<Category> category = this.categoryRepository.findById(id);//.get();

        if (category.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(category);
        else
            return ResponseEntity.status(HttpStatus.OK).body(category);


    }




    @PatchMapping(path = "update")
    public ResponseEntity<Category>updateCategory(@RequestBody Category category)
    {
        try {
            categoryRepository.findById(category.id);

            Category updateCategory =this.categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(updateCategory);
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Category());
        }
    }








    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable Integer id) {
        this.categoryRepository.deleteById(id);

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "category deleted");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
