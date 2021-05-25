package fontys.sem3.it.ticketstore.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    //constructor test
    @Test
    public void constructorCategory(){

        //ARRANGE section
        Category category = new Category(1,"VIP");

        //ACT section
        int id = 1;
        String type = "VIP";

        //ASSERT section
        assertEquals(id,category.getId());
        assertEquals(type,category.getType());
    }


    //type getter and setter test
    @Test
    public void setGetTypeCategory(){

        //ARRANGE section
        Category category = new Category(1,"VIP");

        //ACT section
        int id = 1;
        String type = "General Access";
        category.setType(type);

        //ASSERT section
        assertEquals(id,category.getId());
        assertEquals(type,category.getType());
    }
}
