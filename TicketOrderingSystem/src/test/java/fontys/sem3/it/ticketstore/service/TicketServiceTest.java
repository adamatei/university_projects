package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.Category;
import fontys.sem3.it.ticketstore.model.Concert;
import fontys.sem3.it.ticketstore.model.Ticket;
import fontys.sem3.it.ticketstore.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    TicketRepository repository;

    @InjectMocks
    TicketService service;

    @Test
    public void getTickets(){

        List<Ticket> tickets= new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String str="31-06-2021";
        java.util.Date date1 = null;
        try {
            date1 = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c1 = new Concert(1, "Passeio Martimo De Alges", "Portugal","Lisbon", date1);


        String str2="24-07-2021";
        java.util.Date date2 = null;
        try {
            date2 = formatter.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c2 = new Concert(2, "Estadio Benito Villamarin", "Spain","Seville", date2);

        Category cat1 = new Category(1,"VIP");

        Ticket t1 = new Ticket(1,c1,210.5, cat1, 300);

        Ticket t2= new Ticket(2, c2, 200, cat1, 200);

        tickets.add(t1);
        tickets.add(t2);

        when(repository.findAll()).thenReturn(tickets);
        CompletableFuture<List<Ticket>> completableFuture = service.getTickets();
        List<Ticket> actualTickets = null;
        try {
            actualTickets = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertArrayEquals(tickets.toArray(), actualTickets.toArray());
    }

    @Test
    public void getTicket(){

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

        when(repository.findById(1)).thenReturn(java.util.Optional.of(t1));
        CompletableFuture<Ticket> completableFuture = service.getTicket(1);
        Ticket actualTicket = null;
        try {
            actualTicket = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(t1, actualTicket);
    }

    @Test
    public void saveTicket(){

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

        when(repository.save(t1)).thenReturn(t1);
        CompletableFuture<Ticket> completableFuture = service.saveTicket(t1);
        Ticket actualTicket = null;
        try {
            actualTicket = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(t1, actualTicket);
    }

    @Test
    public void updateTicket(){

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

        when(repository.findById(1)).thenReturn(java.util.Optional.of(t1));
        when(repository.save(t1)).thenReturn(t1);
        CompletableFuture<Ticket> completableFuture = service.updateTicket(t1);
        Ticket actualTicket = null;
        try {
            actualTicket = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(t1, actualTicket);
    }

    @Test
    public void getTicketCategoryByConcert(){

        List<Ticket> tickets= new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String str="31-06-2021";
        java.util.Date date1 = null;
        try {
            date1 = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c1 = new Concert(1, "Passeio Martimo De Alges", "Portugal","Lisbon", date1);


        String str2="24-07-2021";
        java.util.Date date2 = null;
        try {
            date2 = formatter.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c2 = new Concert(2, "Estadio Benito Villamarin", "Spain","Seville", date2);

        Category cat1 = new Category(1,"VIP");

        Ticket t1 = new Ticket(1,c1,210.5, cat1, 300);

        Ticket t2= new Ticket(2, c2, 200, cat1, 200);

        tickets.add(t1);
        tickets.add(t2);

        when(repository.findAll()).thenReturn(tickets);
        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(cat1);
        CompletableFuture<List<Category>> completableFuture = service.getTicketCategoryByConcert(1);
        List<Category> actualCategories = null;
        try {
            actualCategories = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertArrayEquals(expectedCategories.toArray(), actualCategories.toArray());
    }

    @Test
    public void getTicketPriceByConcertAndCategory(){

        List<Ticket> tickets= new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String str="31-06-2021";
        java.util.Date date1 = null;
        try {
            date1 = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c1 = new Concert(1, "Passeio Martimo De Alges", "Portugal","Lisbon", date1);


        String str2="24-07-2021";
        java.util.Date date2 = null;
        try {
            date2 = formatter.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c2 = new Concert(2, "Estadio Benito Villamarin", "Spain","Seville", date2);

        Category cat1 = new Category(1,"VIP");

        Ticket t1 = new Ticket(1,c1,210.5, cat1, 300);

        Ticket t2= new Ticket(2, c2, 200, cat1, 200);

        tickets.add(t1);
        tickets.add(t2);

        when(repository.findAll()).thenReturn(tickets);
        CompletableFuture<Double> completableFuture = service.getTicketPriceByConcertAndCategory(1,1);
        Double actualPrice = null;
        try {
            actualPrice = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(210.5,actualPrice);
    }

    @Test
    public void getTicketByConcertAndCategory(){

        List<Ticket> tickets= new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String str="31-06-2021";
        java.util.Date date1 = null;
        try {
            date1 = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c1 = new Concert(1, "Passeio Martimo De Alges", "Portugal","Lisbon", date1);


        String str2="24-07-2021";
        java.util.Date date2 = null;
        try {
            date2 = formatter.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c2 = new Concert(2, "Estadio Benito Villamarin", "Spain","Seville", date2);

        Category cat1 = new Category(1,"VIP");

        Ticket t1 = new Ticket(1,c1,210.5, cat1, 300);

        Ticket t2= new Ticket(2, c2, 200, cat1, 200);

        tickets.add(t1);
        tickets.add(t2);

        when(repository.findAll()).thenReturn(tickets);
        CompletableFuture<Ticket> completableFuture = service.getTicketByConcertAndCategory(1,1);
        Ticket actualTicket = null;
        try {
            actualTicket = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(t1, actualTicket);
    }
}
