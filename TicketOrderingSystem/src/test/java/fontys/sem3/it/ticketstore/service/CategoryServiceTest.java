package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.Category;
import fontys.sem3.it.ticketstore.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    CategoryRepository repository;

    @InjectMocks
    CategoryService service;

    @Test
    public void getAllCategories(){

        List<Category> categories = new ArrayList<>();

        Category c1 = new Category(1, "VIP");
        Category c2 = new Category(2, "General Access");
        Category c3 = new Category(3, "Limited Access");
        Category c4 = new Category(4, "First Seat");

        categories.add(c1);
        categories.add(c2);
        categories.add(c3);
        categories.add(c4);

        when(repository.findAll()).thenReturn(categories);
        CompletableFuture<List<Category>> completableFuture = service.getCategories();
        List<Category> actualCategories = null;
        try {
            actualCategories = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertArrayEquals(categories.toArray(),actualCategories.toArray());
    }

    @Test
    public void getCategoryById(){

        List<Category> categories = new ArrayList<>();

        Category c1 = new Category(1, "VIP");
        Category c2 = new Category(2, "General Access");
        Category c3 = new Category(3, "Limited Access");
        Category c4 = new Category(4, "First Seat");

        categories.add(c1);
        categories.add(c2);
        categories.add(c3);
        categories.add(c4);

        when(repository.findById(1)).thenReturn(java.util.Optional.of(new Category(1, "VIP")));
        CompletableFuture<Category> completableFuture = service.getCategoryById(1);
        Category actualCategory = null;
        try {
            actualCategory = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(c1,actualCategory);
    }

    @Test
    public void saveCategory(){

        List<Category> categories = new ArrayList<>();

        Category c1 = new Category(1, "VIP");
        Category c2 = new Category(2, "General Access");
        Category c3 = new Category(3, "Limited Access");
        Category c4 = new Category(4, "First Seat");

        categories.add(c1);
        categories.add(c2);
        categories.add(c3);
        categories.add(c4);

        when(repository.save(c4)).thenReturn(c4);
        CompletableFuture<Category> completableFuture = service.saveCategory(new Category(4, "First Seat"));
        Category actualCategory = null;
        try {
            actualCategory = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(c4,actualCategory);
    }

    @Test
    public void updateCategory(){

        List<Category> categories = new ArrayList<>();

        Category c1 = new Category(1, "VIP");
        Category c2 = new Category(2, "General Access");
        Category c3 = new Category(3, "Limited Access");
        Category c4 = new Category(4, "First Seat");

        categories.add(c1);
        categories.add(c2);
        categories.add(c3);
        when(repository.findById(4)).thenReturn(java.util.Optional.of(c4));
        when(repository.save(c4)).thenReturn(c4);
        CompletableFuture<Category> completableFuture = service.updateCategory(new Category(4, "First Seat"));
        Category actualCategory = null;
        try {
            actualCategory = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(c4,actualCategory);
    }
}
