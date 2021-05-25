package fontys.sem3.it.ticketstore.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    //constructor
    @Test
    public void constructorItem(){
        //ARRANGE section
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
        Ticket t1 = new Ticket(1,c1,210.5, cat1,300);
        Item item = new Item(1,t1,1,210.5);

        //ACT section
        int id = 1;
        Ticket expectedTicket = new Ticket(1,c1,210.5, cat1,300);
        int quantity = 1;
        double price = 210.5;

        //ASSERT section
        assertEquals(id, item.getId());
        assertEquals(expectedTicket,item.getTicket());
        assertEquals(quantity, item.getQuantity());
        assertEquals(price, item.getPrice());
    }

    //quantity setter and getter
    @Test
    public void setGetQuantityItem(){
        //ARRANGE section
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
        Ticket t1 = new Ticket(1,c1,210.5, cat1,300);
        Item item = new Item(1,t1,1,210.5);

        //ACT section
        item.setQuantity(2);

        //ASSERT section
        assertEquals(2, item.getQuantity());
    }

    //price setter and getter
    @Test
    public void setGetPriceItem(){
        //ARRANGE section
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
        Ticket t1 = new Ticket(1,c1,210.5, cat1,300);
        Item item = new Item(1,t1,1,210.5);

        //ACT section
        item.setPrice(300);

        //ASSERT section
        assertEquals(300, item.getPrice());
    }

    //ticket setter and getter
    @Test
    public void setGetTicketItem(){
        //ARRANGE section
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

        Ticket t1 = new Ticket(1,c1,210.5, cat1,300);
        Ticket t2 = new Ticket(2,c1,200, cat1,200);
        Item item = new Item(1,t1,1,210.5);

        //ACT section
        item.setTicket(t2);

        //ASSERT section
        assertEquals(t2, item.getTicket());
    }
}
