package fontys.sem3.it.ticketstore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.OneToOne;
//import java.util.List;

@Data
@Entity
public class ApiUser {

    //unique id to identity a specific user
    @Id
    @GeneratedValue
    private int id;

    //account details
    private String username;
    private String password;
    private String role;

    //constructors
    public ApiUser(int id, String username, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public ApiUser() {
    }

    public ApiUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ApiUser(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

}
