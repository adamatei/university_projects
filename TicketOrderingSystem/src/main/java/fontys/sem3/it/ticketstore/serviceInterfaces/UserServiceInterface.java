package fontys.sem3.it.ticketstore.serviceInterfaces;

import fontys.sem3.it.ticketstore.model.ApiUser;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserServiceInterface {

    //getting all the users
    CompletableFuture<List<ApiUser>> getUsers();

    //getting one user based on id
    CompletableFuture<ApiUser> getUserById(int id);

    //adding a new user
    CompletableFuture<ApiUser> saveUser(ApiUser apiUser);

    //delete an existing user
    CompletableFuture<Boolean> deleteUser(int id);

    //update an existing user
    CompletableFuture<ApiUser> updateUser(ApiUser apiUser);

    //getting all users based on role
    CompletableFuture<List<ApiUser>> getUsersByRole(String role);

    //getting a user by username
    CompletableFuture<ApiUser> getUserByUsername(String username);
}
