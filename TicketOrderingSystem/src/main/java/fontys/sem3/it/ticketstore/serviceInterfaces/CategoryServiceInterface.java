package fontys.sem3.it.ticketstore.serviceInterfaces;

import fontys.sem3.it.ticketstore.model.Category;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CategoryServiceInterface {


    CompletableFuture<List<Category>> getCategories();

    CompletableFuture<Category> getCategoryById(int id);

    CompletableFuture<Category> saveCategory(Category category);

    CompletableFuture<Boolean> deleteCategory(int id);

    CompletableFuture<Category>  updateCategory(Category category);
}
