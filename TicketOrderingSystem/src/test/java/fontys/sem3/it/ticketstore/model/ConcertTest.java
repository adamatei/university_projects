package fontys.sem3.it.ticketstore.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcertTest {

    // constructor test
    @Test
    public void constructorConcert(){

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

        //ACT section
        int id = 1;
        String location = "Passeio Martimo De Alges";
        String country = "Portugal";
        String city = "Lisbon";
        String str2="31-06-2021";
        java.util.Date date = null;
        try {
            date = formatter.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //ASSERT section
        assertEquals(id, c1.getId());
        assertEquals(location, c1.getLocation());
        assertEquals(country, c1.getCountry());
        assertEquals(city, c1.getCity());
        assertEquals(date, c1.getDateTime());
    }

    //location setter and getter test
    @Test
    public void setGetLocConcert(){

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

        //ACT section
        c1.setLocation("Grand Arena");
        String location = "Grand Arena";

        //ASSERT section
        assertEquals(location, c1.getLocation());
    }


    //country&city setter and getter test
    @Test
    public void setGetCountryConcert(){

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

        //ACT section
        c1.setCountry("Spain");
        c1.setCity("Madrid");
        String country = "Spain";
        String city = "Madrid";


        //ASSERT section
        assertEquals(country, c1.getCountry());
        assertEquals(city, c1.getCity());
    }


    //date setter and getter test
    @Test
    public void setGetDateConcert(){

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
        //ACT section
        String str1 = "2021-06-02";
        java.util.Date date = null;
        try {
            date = formatter.parse(str1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c1.setDateTime(date);

        //ASSERT section
        assertEquals(date, c1.getDateTime());
    }
}
