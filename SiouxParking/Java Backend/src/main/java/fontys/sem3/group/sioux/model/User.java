package fontys.sem3.group.sioux.model;

import javax.persistence.*;

@Entity
@Table(name ="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //unique identifier id
    private Long userId;

    //name
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;

    //email
    @Column(name = "email")
    private String email;

    //username
    @Column(name = "username")
    private String username;

    //password
    @Column(name = "password")
    private String password;

    //isDeleted
    @Column(name = "isDeleted")
    private Boolean isDeleted;

}
