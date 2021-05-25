package fontys.sem3.it.ticketstore.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketTest {

    // constructor test
    @Test
    public void constructorTicket(){

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
        Ticket t1 = new Ticket(1,c1,210.5, cat1,1);

        //ACT section
        int id = 1;
        double price = 210.5;
        String category = "VIP";
        String country = "Portugal";
        String city = "Lisbon";
        java.util.Date date = null;
        try {
            date = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //ASSERT section
        assertEquals(id, t1.getId());
        assertEquals(price, t1.getPrice());
        assertEquals(1,t1.getQuantity());

        //checking the assigned concert
        assertEquals(id, t1.getConcert().getId());
        assertEquals(country, t1.getConcert().getCountry());
        assertEquals(city, t1.getConcert().getCity());
        assertEquals(date, t1.getConcert().getDateTime());

        //checking the assigned category
        assertEquals(id, t1.getCategory().getId());
        assertEquals(category, t1.getCategory().getType());
    }


    // price getter and setter test
    @Test
    public void setGetPriceTicket(){

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
        Ticket t1 = new Ticket(1,c1,210.5, cat1,1);

        //ACT section
        t1.setPrice(200.15);
        double price = 200.15;

        //ASSERT section
        assertEquals(price, t1.getPrice());
    }

    // Concert getter and setter test
    @Test
    public void getSetConcertTicket(){

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
        Ticket t1 = new Ticket(1,c1,210.5, cat1,1);

        //ACT section
        String str2="24-07-2021";
        java.util.Date date2 = null;
        try {
            date2 = formatter.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c2 = new Concert(2, "Estadio Benito Villamarin", "Spain","Seville", date2);
        int id = 2;
        t1.setConcert(c2);
        String country = "Spain";
        String city = "Seville";
        java.util.Date date = null;
        try {
            date = formatter.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //ASSERT section
        assertEquals(id, t1.getConcert().getId());
        assertEquals(country, t1.getConcert().getCountry());
        assertEquals(city, t1.getConcert().getCity());
        assertEquals(date, t1.getConcert().getDateTime());
    }


    // category getter and setter test
    @Test
    public void getSetCategoryTicket(){

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
        Ticket t1 = new Ticket(1,c1,210.5, cat1,1);

        //ACT section
        Category cat2 = new Category(2,"General Access");
        t1.setCategory(cat2);

        //ASSERT section
        assertEquals(cat2, t1.getCategory());
    }
}
