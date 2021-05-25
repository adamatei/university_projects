package fontys.sem3.it.ticketstore.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiUserTest {

    // constructor test
    @Test
    public void constructorUser(){

        //ARRANGE section
        ApiUser u = new ApiUser(1,"axl","12345","roseaxl17@yahoo.com", "ADMIN");

        //ACT section
        int id = 1;
        String username = "axl";
        String password = "12345";
        String role = "ADMIN";

        //ASSERT section
        assertEquals(id,u.getId());
        assertEquals(username,u.getUsername());
        assertEquals(password,u.getPassword());
        assertEquals(role,u.getRole());
    }

    // username setter and getter test
    @Test
    public void setGetUsernameUser(){

        //ARRANGE section
        ApiUser u = new ApiUser(1,"axl","12345","roseaxl17@yahoo.com", "ADMIN");

        //ACT section
        u.setUsername("roseaxl");
        String username = "roseaxl";

        //ASSERT section
        assertEquals(username,u.getUsername());
    }


    // password setter and getter test
    @Test
    public void setGetPasswordUser(){

        //ARRANGE section
        ApiUser u = new ApiUser(1,"axl","12345","roseaxl17@yahoo.com", "ADMIN");

        //ACT section
        u.setPassword("newPassword");
        String password = "newPassword";

        //ASSERT section
        assertEquals(password,u.getPassword());
    }


    // role setter and getter test
    @Test
    public void setGetRoleUser(){

        //ARRANGE section
        ApiUser u = new ApiUser(1,"axl","12345","roseaxl17@yahoo.com", "ADMIN");

        //ACT section
        String newRole = "USER";
        u.setRole(newRole);

        //ASSERT section
        assertEquals(newRole,u.getRole());
    }

}
