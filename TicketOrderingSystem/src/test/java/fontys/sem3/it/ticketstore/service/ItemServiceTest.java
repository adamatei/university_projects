package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.Category;
import fontys.sem3.it.ticketstore.model.Concert;
import fontys.sem3.it.ticketstore.model.Item;
import fontys.sem3.it.ticketstore.model.Ticket;
import fontys.sem3.it.ticketstore.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    ItemRepository repository;

    @InjectMocks
    ItemService service;

    @Test
    public void getItemById(){

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String str="31-06-2021";
        java.util.Date date1 = null;
        try {
            date1 = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c1 = new Concert(1, "Passeio Martimo De Alges", "Portugal","Lisbon", date1);

        Category cat1 = new Category(1,"VIP");

        Ticket t1 = new Ticket(1,c1,210.5, cat1, 300);

        Item item = new Item(1,t1,2,421);

        when(repository.findById(1)).thenReturn(java.util.Optional.of(item));
        CompletableFuture<Item> completableFuture = service.getItemById(1);
        Item actualItem = null;
        try {
            actualItem = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(item, actualItem);
    }

    @Test
    public void saveItem(){

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String str="31-06-2021";
        java.util.Date date1 = null;
        try {
            date1 = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c1 = new Concert(1, "Passeio Martimo De Alges", "Portugal","Lisbon", date1);

        Category cat1 = new Category(1,"VIP");

        Ticket t1 = new Ticket(1,c1,210.5, cat1, 300);

        Item item = new Item(1,t1,2,421);

        when(repository.save(item)).thenReturn(item);
        CompletableFuture<Item> completableFuture = service.saveItem(item);
        Item actualItem = null;
        try {
            actualItem = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(item, actualItem);
    }

    @Test
    public void updateItem(){

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String str="31-06-2021";
        java.util.Date date1 = null;
        try {
            date1 = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c1 = new Concert(1, "Passeio Martimo De Alges", "Portugal","Lisbon", date1);

        Category cat1 = new Category(1,"VIP");

        Ticket t1 = new Ticket(1,c1,210.5, cat1, 300);

        Item item = new Item(1,t1,2,421);

        when(repository.findById(1)).thenReturn(java.util.Optional.of(item));
        when(repository.save(item)).thenReturn(item);
        CompletableFuture<Item> completableFuture = service.updateItem(item);
        Item actualItem = null;
        try {
            actualItem = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(item, actualItem);
    }
}
