package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.*;
import fontys.sem3.it.ticketstore.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    CartRepository repository;

    @InjectMocks
    CartService service;

    @Test
    public void getAllCarts(){

        List<Cart> carts = new ArrayList<>();

        String role1 = "ADMIN";
        String role2 = "USER";

        ApiUser apiUser1 = new ApiUser(1, "acdc", "12345", "acdc32@yahoo.com", role2);
        ApiUser apiUser2 = new ApiUser(2, "metallica", "54321", "metal17@gmail.com", role1);

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

        Item item1 = new Item(1,t1,2,421);
        Item item2 = new Item(2,t1,1,210.5);

        Cart cart1 = new Cart(1, apiUser1,421,new ArrayList<>(Arrays.asList(item1)));
        Cart cart2 = new Cart(2, apiUser2,210.5,new ArrayList<>(Arrays.asList(item2)));

        carts.add(cart1);
        carts.add(cart2);

        when(repository.findAll()).thenReturn(carts);
        CompletableFuture<List<Cart>> completableFuture = service.getAllCarts();
        List<Cart> actualCarts = null;
        try {
            actualCarts = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(carts, actualCarts);
    }

    @Test
    public void getCart(){

        String role1 = "ADMIN";
        String role2 = "USER";

        ApiUser apiUser1 = new ApiUser(1, "acdc", "12345", "acdc32@yahoo.com", role1);

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

        Item item1 = new Item(1,t1,2,421);
        Item item2 = new Item(2,t1,1,210.5);

        Cart cart1 = new Cart(1, apiUser1,631.5,new ArrayList<>(Arrays.asList(item1,item2)));

        when(repository.findById(1)).thenReturn(java.util.Optional.of(cart1));
        CompletableFuture<Cart> completableFuture = service.getCart(1);
        Cart actualCart = null;
        try {
            actualCart = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(cart1,actualCart);
    }

    @Test
    public void getCartByCustomerId(){

        String role1= "USER";

        ApiUser apiUser1 = new ApiUser(1, "acdc", "12345", "acdc32@yahoo.com", role1);

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

        Item item1 = new Item(1,t1,2,421);
        Item item2 = new Item(2,t1,1,210.5);

        Cart cart1 = new Cart(1, apiUser1,631.5,new ArrayList<>(Arrays.asList(item1,item2)));

        when(repository.findById(1)).thenReturn(java.util.Optional.of(cart1));
        CompletableFuture<Cart> completableFuture = service.getCart(1);
        Cart actualCart = null;
        try {
            actualCart = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(cart1,actualCart);
    }

    @Test
    public void addCart(){

        String role1 = "USER";

        ApiUser apiUser1 = new ApiUser(1, "acdc", "12345", "acdc32@yahoo.com", role1);

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

        Item item1 = new Item(1,t1,2,421);
        Item item2 = new Item(2,t1,1,210.5);

        Cart cart1 = new Cart(1, apiUser1,631.5,new ArrayList<>(Arrays.asList(item1,item2)));

        when(repository.save(cart1)).thenReturn(cart1);
        CompletableFuture<Cart> completableFuture = service.addCart(cart1);
        Cart actualCart = null;
        try {
            actualCart = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(cart1,actualCart);
    }

    @Test
    public void updateCart(){

        String role1 = "USER";

        ApiUser apiUser1 = new ApiUser(1, "acdc", "12345", "acdc32@yahoo.com", role1);

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

        Item item1 = new Item(1,t1,2,421);
        Item item2 = new Item(2,t1,1,210.5);

        Cart cart1 = new Cart(1, apiUser1,631.5,new ArrayList<>(Arrays.asList(item1,item2)));

        when(repository.findById(1)).thenReturn(java.util.Optional.of(cart1));
        when(repository.save(cart1)).thenReturn(cart1);
        CompletableFuture<Cart> completableFuture = service.updateCart(cart1);
        Cart actualCart = null;
        try {
            actualCart = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(cart1,actualCart);
    }

    @Test
    public void addItem(){

        String role1 = "USER";

        ApiUser apiUser1 = new ApiUser(1, "acdc", "12345", "acdc32@yahoo.com", role1);

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

        Item item1 = new Item(1,t1,2,421);
        Item item2 = new Item(2,t1,1,210.5);

        Cart cart1 = new Cart(1, apiUser1,631.5,new ArrayList<>(Arrays.asList(item1)));
        Cart expectedCart = cart1;
        expectedCart.addItem(item2);
        expectedCart.setTotalPrice(expectedCart.calculatateTotalPrice());
        when(repository.findById(1)).thenReturn(java.util.Optional.of(cart1));
        when(repository.save(cart1)).thenReturn(cart1);
        CompletableFuture<Cart> completableFuture = service.addItem(1,item2);
        Cart actualCart = null;
        try {
            actualCart = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(expectedCart,actualCart);
    }

    @Test
    public void getCartItems(){

        List<Cart> carts = new ArrayList<>();

        String role1 = "ADMIN";
        String role2 = "USER";


        ApiUser apiUser1 = new ApiUser(1, "acdc", "12345", "acdc32@yahoo.com", role2);
        ApiUser apiUser2 = new ApiUser(2, "metallica", "54321", "metal17@gmail.com", role1);

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

        Item item1 = new Item(1,t1,2,421);
        Item item2 = new Item(2,t1,1,210.5);

        Cart cart1 = new Cart(1, apiUser1,421,new ArrayList<>(Arrays.asList(item1)));
        Cart cart2 = new Cart(2, apiUser2,210.5,new ArrayList<>(Arrays.asList(item2)));

        carts.add(cart1);
        carts.add(cart2);

        when(repository.findAll()).thenReturn(carts);
        List<Item> expectedItems = new ArrayList<>();
        expectedItems.add(item1);
        CompletableFuture<List<Item>> completableFuture = service.getCartItems(1);
        List<Item> actualItems = null;
        try {
            actualItems = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(expectedItems, actualItems);
    }

    @Test
    public void updatePrice(){

        String role1 = "ADMIN";
        String role2 = "USER";


        ApiUser apiUser1 = new ApiUser(1, "acdc", "12345", "acdc32@yahoo.com", role1);

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

        Item item1 = new Item(1,t1,2,421);
        Item item2 = new Item(2,t1,1,210.5);

        Cart cart1 = new Cart(1, apiUser1,631.5,new ArrayList<>(Arrays.asList(item1,item2)));

        when(repository.findById(1)).thenReturn(java.util.Optional.of(cart1));
        when(repository.save(cart1)).thenReturn(cart1);
        CompletableFuture<Cart> completableFuture = service.updatePrice(1);
        Cart actualCart = null;
        try {
            actualCart = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(cart1,actualCart);
    }
}
