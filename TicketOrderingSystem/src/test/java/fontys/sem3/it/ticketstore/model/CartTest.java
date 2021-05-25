package fontys.sem3.it.ticketstore.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    //constructor
    @Test
    public void constructorCart(){

        //ARRANGE section
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

        //ACT section
        List<Item> expectedItems = new ArrayList<>(Arrays.asList(item1,item2));

        //ASSERT section
        assertEquals(1, cart1.getId());
        assertEquals(apiUser1, cart1.getApiUser());
        assertArrayEquals(expectedItems.toArray(),cart1.getItems().toArray());
        assertEquals(631.5, cart1.getTotalPrice());
    }

    //user setter and getter
    @Test
    public void setGetUserCart(){

        //ARRANGE section
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

        //ACT section
        cart1.setApiUser(new ApiUser(2, "metallica", "unforgiven", "metallica07@yahoo.com", role1));
        ApiUser expectedApiUser = new ApiUser(2, "metallica", "unforgiven", "metallica07@yahoo.com", role1);

        //ASSERT section
        assertEquals(expectedApiUser, cart1.getApiUser());
    }

    //price setter and getter
    @Test
    public void setGetPriceCart(){

        //ARRANGE section
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

        //ACT section
        cart1.setTotalPrice(600);

        //ASSERT section
        assertEquals(600, cart1.getTotalPrice());
    }

    //items setter and getter
    @Test
    public void setGetItemsCart(){

        //ARRANGE section
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

        //ACT section
        List<Item> newItems = new ArrayList<>();
        newItems.add(item1);
        cart1.setItems(newItems);

        //ASSERT section
        assertArrayEquals(newItems.toArray(),cart1.getItems().toArray());
    }

    //add item
    @Test
    public void addItemCart(){

        //ARRANGE section
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

        //ACT section
        Item item3 = new Item(3,t1, 3, 631.5);
        List<Item> newItems = new ArrayList<>(Arrays.asList(item1,item2,item3));
        cart1.addItem(item3);

        //ASSERT section
        assertArrayEquals(newItems.toArray(),cart1.getItems().toArray());
    }

    //calculate total price
    @Test
    public void calculateTotalPriceCart(){

        //ARRANGE section
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

        //ACT section
       double actualPrice = cart1.calculatateTotalPrice();

        //ASSERT section
       assertEquals(631.5,actualPrice);
    }

    //remove item
    @Test
    public void removeItemCart(){

        //ARRANGE section
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

        //ACT section
        cart1.deleteItem(2);
        List<Item> expectedItems = new ArrayList<>();
        expectedItems.add(item1);

        //ASSERT section
        assertArrayEquals(expectedItems.toArray(),cart1.getItems().toArray());
    }
}
