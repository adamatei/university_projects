package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.ApiUser;
import fontys.sem3.it.ticketstore.model.request.UserCreateRequest;
import fontys.sem3.it.ticketstore.repository.UserRepository;
import fontys.sem3.it.ticketstore.serviceInterfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //getting all the users
    @Async
    public CompletableFuture<List<ApiUser>> getUsers(){
        return CompletableFuture.completedFuture(repository.findAll());
    }

    //getting one user based on id
    @Async
    public CompletableFuture<ApiUser> getUserById(int id){
        return CompletableFuture.completedFuture(repository.findById(id).orElse(null));
    }

    //adding a new user
    @Async
    public CompletableFuture<ApiUser> saveUser(ApiUser apiUser){
        return CompletableFuture.completedFuture(repository.save(apiUser));
    }

    //delete an existing user
    @Async
    public CompletableFuture<Boolean> deleteUser(int id){
        repository.deleteById(id);
        return CompletableFuture.completedFuture(true);
    }

    //update an existing user
    @Async
    public CompletableFuture<ApiUser> updateUser(ApiUser apiUser){
        ApiUser existingApiUser = repository.findById(apiUser.getId()).orElse(null);
        existingApiUser.setUsername(apiUser.getUsername());
        //.setEmail(apiUser.getEmail());
        existingApiUser.setPassword(apiUser.getPassword());
        existingApiUser.setRole(apiUser.getRole());
        return CompletableFuture.completedFuture(repository.save(existingApiUser));
    }

    //getting all users based on role
    @Async
    public CompletableFuture<List<ApiUser>> getUsersByRole(String role){
        List<ApiUser> apiUsers = new ArrayList<>();
        for(ApiUser apiUser : repository.findAll()){
            if(apiUser.getRole().equals(role)){
                apiUsers.add(apiUser);
            }
        }
        return CompletableFuture.completedFuture(apiUsers);
    }

    @Override
    public CompletableFuture<ApiUser> getUserByUsername(String username) {
        return CompletableFuture.completedFuture(repository.getByUsername(username));
    }

    public ApiUser readUserByUsername (String username) {
        return repository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Async
    public CompletableFuture<ApiUser> createUser(UserCreateRequest userCreateRequest) {
        ApiUser apiUser = new ApiUser();
        Optional<ApiUser> byUsername = repository.findByUsername(userCreateRequest.getUsername());
        if (byUsername.isPresent()) {
            throw new RuntimeException("User already registered. Please use different username.");
        }
        apiUser.setUsername(userCreateRequest.getUsername());
        apiUser.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        apiUser.setRole(userCreateRequest.getRole());
        return CompletableFuture.completedFuture(repository.save(apiUser));
    }

}
