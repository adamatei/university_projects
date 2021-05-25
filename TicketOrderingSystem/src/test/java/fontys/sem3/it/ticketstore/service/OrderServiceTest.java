package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.*;
import fontys.sem3.it.ticketstore.repository.OrderRepository;
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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepository repository;

    @InjectMocks
    OrderService service;

    @Test
    public void getAllOrders(){

        List<TicketOrder> orders = new ArrayList<>();
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
        Category cat2 = new Category(2,"General Access");

        PaymentMethod payment1 = new PaymentMethod(1, "card");
        PaymentMethod payment2 = new PaymentMethod(2, "cash");

        DeliveryAddress da1 = new DeliveryAddress("Tongelresestraat 216", "Netherlands", "Eindhoven", "5631DT");
        DeliveryAddress da2 = new DeliveryAddress("Stratum 23", "Netherlands", "Eindhoven", "3245RT");

        Ticket t1 = new Ticket(1,c1,210.5, cat1, 300);
        Ticket t2 = new Ticket(2,c1,150, cat2, 300);

        Item item1 = new Item(1,t1,2,421);
        Item item2 = new Item(2,t1,1,210.5);
        Item item3 = new Item(3,t2,1,150);
        Item item4 = new Item(4,t2,2,300);

        TicketOrder order1 = new TicketOrder(1, apiUser1, da1, 571,new ArrayList<>(Arrays.asList(item1,item3)),payment1);
        TicketOrder order2 = new TicketOrder(2, apiUser2, da2, 510.5,new ArrayList<>(Arrays.asList(item2,item4)),payment2);

        orders.add(order1);
        orders.add(order2);

        when(repository.findAll()).thenReturn(orders);
        CompletableFuture<List<TicketOrder>> completableFuture = service.getAllOrders();
        List<TicketOrder> actualOrders = null;
        try {
            actualOrders = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertArrayEquals(orders.toArray(), actualOrders.toArray());
    }

    @Test
    public void getOrder(){

        String role2 = "USER";

        ApiUser apiUser1 = new ApiUser(1, "acdc", "12345", "acdc32@yahoo.com", role2);

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
        Category cat2 = new Category(2,"General Access");

        PaymentMethod payment1 = new PaymentMethod(1, "card");

        DeliveryAddress da1 = new DeliveryAddress("Tongelresestraat 216", "Netherlands", "Eindhoven", "5631DT");

        Ticket t1 = new Ticket(1,c1,210.5, cat1, 300);
        Ticket t2 = new Ticket(2,c1,150, cat2, 300);

        Item item1 = new Item(1,t1,2,421);
        Item item3 = new Item(3,t2,1,150);

        TicketOrder order1 = new TicketOrder(1, apiUser1, da1, 571,new ArrayList<>(Arrays.asList(item1,item3)),payment1);

        when(repository.findById(1)).thenReturn(java.util.Optional.of(order1));

        CompletableFuture<TicketOrder> completableFuture = service.getOrder(1);
        TicketOrder actualOrder = null;
        try {
            actualOrder = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(order1,actualOrder);
    }

    @Test
    public void addOrder(){

        String role2 = "USER";

        ApiUser apiUser1 = new ApiUser(1, "acdc", "12345", "acdc32@yahoo.com", role2);

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
        Category cat2 = new Category(2,"General Access");

        PaymentMethod payment1 = new PaymentMethod(1, "card");

        DeliveryAddress da1 = new DeliveryAddress("Tongelresestraat 216", "Netherlands", "Eindhoven", "5631DT");

        Ticket t1 = new Ticket(1,c1,210.5, cat1, 300);
        Ticket t2 = new Ticket(2,c1,150, cat2, 300);

        Item item1 = new Item(1,t1,2,421);
        Item item3 = new Item(3,t2,1,150);

        TicketOrder order1 = new TicketOrder(1, apiUser1, da1, 571,new ArrayList<>(Arrays.asList(item1,item3)),payment1);

        when(repository.save(order1)).thenReturn(order1);
        CompletableFuture<TicketOrder> completableFuture = service.addOrder(order1);
        TicketOrder actualOrder = null;
        try {
            actualOrder = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(order1,actualOrder);
    }

    @Test
    public void updateOrder(){

        String role2 = "USER";

        ApiUser apiUser1 = new ApiUser(1, "acdc", "12345", "acdc32@yahoo.com", role2);

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
        Category cat2 = new Category(2,"General Access");

        PaymentMethod payment1 = new PaymentMethod(1, "card");

        DeliveryAddress da1 = new DeliveryAddress("Tongelresestraat 216", "Netherlands", "Eindhoven", "5631DT");

        Ticket t1 = new Ticket(1,c1,210.5, cat1, 300);
        Ticket t2 = new Ticket(2,c1,150, cat2, 300);

        Item item1 = new Item(1,t1,2,421);
        Item item3 = new Item(3,t2,1,150);

        TicketOrder order1 = new TicketOrder(1, apiUser1, da1, 571,new ArrayList<>(Arrays.asList(item1,item3)),payment1);

        when(repository.findById(1)).thenReturn(java.util.Optional.of(order1));
        when(repository.save(order1)).thenReturn(order1);
        CompletableFuture<TicketOrder> completableFuture = service.updateOrder(order1);
        TicketOrder actualOrder = null;
        try {
            actualOrder = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(order1,actualOrder);
    }
}
