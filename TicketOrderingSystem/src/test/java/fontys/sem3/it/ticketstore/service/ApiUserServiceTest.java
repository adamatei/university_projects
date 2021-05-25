package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.ApiUser;
import fontys.sem3.it.ticketstore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApiUserServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    UserService service;

    @Test
    public void getUsers(){

        List<ApiUser> apiUsers = new ArrayList<>();

        String role1 = "ADMIN";
        String role2 = "USER";

        ApiUser apiUser1 = new ApiUser(1, "acdc", "12345", "acdc32@yahoo.com", role2);
        ApiUser apiUser2 = new ApiUser(2, "metallica", "54321", "metal17@gmail.com", role1);

        apiUsers.add(apiUser1);
        apiUsers.add(apiUser2);

        when(repository.findAll()).thenReturn(apiUsers);
        CompletableFuture<List<ApiUser>> completableFuture = service.getUsers();
        List<ApiUser> actualApiUsers = null;
        try {
            actualApiUsers = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertArrayEquals(apiUsers.toArray(),actualApiUsers.toArray());
    }

    @Test
    public void getUserById(){

        String role1 = "ADMIN";

        ApiUser apiUser1 = new ApiUser(1, "metallica", "54321", "metal17@gmail.com", role1);

        when(repository.findById(1)).thenReturn(java.util.Optional.of(apiUser1));
        CompletableFuture<ApiUser> completableFuture = service.getUserById(1);
        ApiUser actualApiUser = null;
        try {
            actualApiUser = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(apiUser1, actualApiUser);
    }

    @Test
    public void saveUser(){

        String role1 = "ADMIN";

        ApiUser apiUser1 = new ApiUser(1, "metallica", "54321", "metal17@gmail.com", role1);

        when(repository.save(apiUser1)).thenReturn(apiUser1);
        CompletableFuture<ApiUser> completableFuture = service.saveUser(apiUser1);
        ApiUser actualApiUser = null;
        try {
            actualApiUser = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(apiUser1, actualApiUser);
    }
    @Test
    public void updateUser(){

        String role1 = "ADMIN";

        ApiUser apiUser1 = new ApiUser(1, "metallica", "54321", "metal17@gmail.com", role1);

        when(repository.findById(1)).thenReturn(java.util.Optional.of(apiUser1));
        when(repository.save(apiUser1)).thenReturn(apiUser1);
        CompletableFuture<ApiUser> completableFuture = service.updateUser(apiUser1);
        ApiUser actualApiUser = null;
        try {
            actualApiUser = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(apiUser1, actualApiUser);
    }

    @Test
    public void getUsersByRole(){

        List<ApiUser> apiUsers = new ArrayList<>();

        String role1 = "ADMIN";
        String role2 = "USER";

        ApiUser apiUser1 = new ApiUser(1, "acdc", "12345", "acdc32@yahoo.com", role2);
        ApiUser apiUser2 = new ApiUser(2, "metallica", "54321", "metal17@gmail.com", role1);

        apiUsers.add(apiUser1);
        apiUsers.add(apiUser2);

        List<ApiUser> expectedApiUsers = new ArrayList<>();
        expectedApiUsers.add(apiUser2);

        when(repository.findAll()).thenReturn(apiUsers);
        CompletableFuture<List<ApiUser>> completableFuture = service.getUsersByRole(role1);
        List<ApiUser> actualApiUsers = new ArrayList<>();
        try {
            actualApiUsers = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertArrayEquals(expectedApiUsers.toArray(), actualApiUsers.toArray());
    }
}

