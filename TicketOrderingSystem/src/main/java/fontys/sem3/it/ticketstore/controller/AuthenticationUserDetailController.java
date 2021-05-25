package fontys.sem3.it.ticketstore.controller;

import fontys.sem3.it.ticketstore.model.request.AuthenticationRequest;
import fontys.sem3.it.ticketstore.model.request.UserCreateRequest;
import fontys.sem3.it.ticketstore.service.AuthenticationUserDetailService;
import fontys.sem3.it.ticketstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/login")
public class AuthenticationUserDetailController {

    @Autowired
    private AuthenticationUserDetailService service;

    @PostMapping()
    public UserDetails authentication(@RequestBody AuthenticationRequest request){
        return service.loadUserByUsername(request.getUsername());
    }

}
