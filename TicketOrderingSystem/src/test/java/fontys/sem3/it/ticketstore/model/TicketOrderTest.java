package fontys.sem3.it.ticketstore.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketOrderTest {

    //constructor
   @Test
    public void constructorTicketOrder(){

       //ARRANGE section

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
       Item item2 = new Item(3,t2,1,150);

       TicketOrder order1 = new TicketOrder(1, apiUser1, da1, 571,new ArrayList<>(Arrays.asList(item1,item2)),payment1);

       //ACT section
       List<Item> expectedItems = new ArrayList<>(Arrays.asList(item1,item2));

       //ASSERT section
       assertEquals(1, order1.getId());
       assertEquals(apiUser1, order1.getApiUser());
       assertArrayEquals(expectedItems.toArray(),order1.getItems().toArray());
       assertEquals(571, order1.getTotalPrice());
   }

    //price setter and getter
    @Test
    public void setGetPriceTicketOrder(){

        //ARRANGE section
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
        Item item2 = new Item(3,t2,1,150);

        TicketOrder order1 = new TicketOrder(1, apiUser1, da1, 571,new ArrayList<>(Arrays.asList(item1,item2)),payment1);

        //ACT section
        order1.setTotalPrice(600);

        //ASSERT section
        assertEquals(600, order1.getTotalPrice());
    }

    //user setter and getter
    @Test
    public void setGetUserTicketOrder(){

        //ARRANGE section
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
        Item item2 = new Item(3,t2,1,150);

        TicketOrder order1 = new TicketOrder(1, apiUser1, da1, 571,new ArrayList<>(Arrays.asList(item1,item2)),payment1);

        //ACT section
        ApiUser apiUser2 = new ApiUser(2, "metallica", "54321", "metal17@gmail.com", role2);
        order1.setApiUser(apiUser2);

        //ASSERT section
        assertEquals(apiUser2, order1.getApiUser());
    }

    //payment method setter and getter
    @Test
    public void setGetPaymentMethodTicketOrder(){

        //ARRANGE section
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
        Item item2 = new Item(3,t2,1,150);

        TicketOrder order1 = new TicketOrder(1, apiUser1, da1, 571,new ArrayList<>(Arrays.asList(item1,item2)),payment1);

        //ACT section
        PaymentMethod payment2 = new PaymentMethod(2, "cash");
        order1.setPaymentMethod(payment2);

        //ASSERT section
        assertEquals(payment2, order1.getPaymentMethod());
    }

    //delivery address setter and getter
    @Test
    public void setGetDeliveryAddressTicketOrder(){

        //ARRANGE section
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
        Item item2 = new Item(3,t2,1,150);

        TicketOrder order1 = new TicketOrder(1, apiUser1, da1, 571,new ArrayList<>(Arrays.asList(item1,item2)),payment1);

        //ACT section
        DeliveryAddress da2 = new DeliveryAddress("Stratum 23", "Netherlands", "Eindhoven", "3245RT");
        order1.setDeliveryAddress(da2);

        //ASSERT section
        assertEquals(da2, order1.getDeliveryAddress());
    }

    //items setter and getter
    @Test
    public void setGetItemsTicketOrder(){

        //ARRANGE section
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
        Item item2 = new Item(3,t2,1,150);

        TicketOrder order1 = new TicketOrder(1, apiUser1, da1, 571,new ArrayList<>(Arrays.asList(item1,item2)),payment1);

        //ACT section
        List<Item> newItems = new ArrayList<>();
        newItems.add(item1);
        order1.setItems(newItems);

        //ASSERT section
        assertArrayEquals(newItems.toArray(), order1.getItems().toArray());
    }

    //calculate total price
    @Test
    public void calculateTotalPriceTicketOrder(){

        //ARRANGE section
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
        Item item2 = new Item(3,t2,1,150);

        TicketOrder order1 = new TicketOrder(1, apiUser1, da1, 571,new ArrayList<>(Arrays.asList(item1,item2)),payment1);

        //ACT section
        double actualPrice = order1.calculateTotalPrice();

        //ASSERT section
        assertEquals(571,actualPrice);
    }
}
