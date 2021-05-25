package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.Category;
import fontys.sem3.it.ticketstore.repository.CategoryRepository;
import fontys.sem3.it.ticketstore.serviceInterfaces.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CategoryService implements CategoryServiceInterface {


    @Autowired
    private CategoryRepository repository;

   //getting all the categories
    @Async
    public CompletableFuture<List<Category>> getCategories(){
            return  CompletableFuture.completedFuture(repository.findAll());
    }

    //getting one category based on id
    @Async
    public CompletableFuture<Category> getCategoryById(int id){
        return CompletableFuture.completedFuture((repository.findById(id).orElse(null)));
    }

    //adding a new category
    @Async
    public CompletableFuture<Category> saveCategory(Category category){
        return CompletableFuture.completedFuture(repository.save(category));
    }

    //delete an existing category
    @Async
    public CompletableFuture<Boolean> deleteCategory(int id){
        repository.deleteById(id);
        return CompletableFuture.completedFuture(true);
    }

    //update an existing category
    @Async
    public CompletableFuture<Category> updateCategory(Category category){
        Category existingCategory = repository.findById(category.getId()).orElse(null);
        existingCategory.setType(category.getType());
        return CompletableFuture.completedFuture(repository.save(existingCategory));
    }

}
