package fontys.sem3.it.ticketstore.controller;

import fontys.sem3.it.ticketstore.service.CategoryService;
import fontys.sem3.it.ticketstore.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/tickets_categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

   //getting all the available categories
    @GetMapping()
    public CompletableFuture<List<Category>> findAllCategories(){
       return service.getCategories();
    }

    //getting a particular category based on id
    @GetMapping("{id}")
    public CompletableFuture<Category> getCategory(@PathVariable(value = "id") int id) {
      return service.getCategoryById(id);
    }

    //adding a new category to the list
    @PostMapping()
    public CompletableFuture<Category> addCategory(@RequestBody Category category){
      return service.saveCategory(category);
    }

    //removing a category from the list
    @DeleteMapping("{id}")
    public CompletableFuture<Boolean> removeCategory(@PathVariable(value = "id") int id){
        return service.deleteCategory(id);
    }


    //updating a specific category
    @PutMapping()
    public CompletableFuture<Category>  updateCategory(@RequestBody Category category){
        return service.updateCategory(category);
    }
}
