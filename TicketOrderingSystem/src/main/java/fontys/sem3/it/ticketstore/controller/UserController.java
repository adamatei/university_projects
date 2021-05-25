package fontys.sem3.it.ticketstore.controller;

import fontys.sem3.it.ticketstore.model.ApiUser;
import fontys.sem3.it.ticketstore.model.request.UserCreateRequest;
import fontys.sem3.it.ticketstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    //getting all the users
    @GetMapping()
    public CompletableFuture<List<ApiUser>> getAllUsers(){
       return service.getUsers();
    }

    //getting a particular user based on id
    @GetMapping("{id}")
    //GET at http://localhost:XXXX/users/3
    public CompletableFuture<ApiUser> getUser(@PathVariable(value = "id") int id) {
       return service.getUserById(id);
    }

    @GetMapping("/un/{username}")
    //GET at http://localhost:XXXX/users/3
    public CompletableFuture<ApiUser> getUser(@PathVariable(value = "username") String username) {
        return service.getUserByUsername(username);
    }

    //getting all users based on role
    @GetMapping("/role")
    public CompletableFuture<List<ApiUser>> getUsersByRole(@RequestBody String role){
       return service.getUsersByRole(role);
    }

    //adding a new user to the list
    @PostMapping()
  /*  public User addUser(@RequestBody User user){
       return service.saveUser(user);
    }*/
    public CompletableFuture<ApiUser> createUser (@RequestBody UserCreateRequest userCreateRequest) {
        return service.createUser(userCreateRequest);
        //return ResponseEntity.ok().build();
    }

    //removing a user from the list
    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/concerts/3
    public CompletableFuture<Boolean> removeUser(@PathVariable(value = "id") int id){
      return service.deleteUser(id);
    }

    //updating a specific user
    @PutMapping()
    public CompletableFuture<ApiUser> updateUser(@RequestBody ApiUser apiUser){
        return service.updateUser(apiUser);
    }
}
