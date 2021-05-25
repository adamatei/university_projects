package fontys.sem3.it.ticketstore.controller;

import fontys.sem3.it.ticketstore.model.Concert;
import fontys.sem3.it.ticketstore.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/concerts")
public class ConcertController {

    @Autowired
    private ConcertService service;

    //getting all the available concerts for the up-coming concerts
    @GetMapping()
    public CompletableFuture<List<Concert>> getAllConcerts(){ return service.getConcerts();}

    //getting a particular concert based on id
    @GetMapping("{id}")
    //GET at http://localhost:XXXX/concerts/3
    public CompletableFuture<Concert> getConcert(@PathVariable(value = "id") int id) {
       return service.getConcertById(id);
    }

    //adding a new concert to the list
    @PostMapping()
    public CompletableFuture<Concert> addConcert(@RequestBody Concert concert){
      return service.saveConcert(concert);
    }

    //removing a concert from the list
    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/concerts/3
    public CompletableFuture<Boolean> removeConcert(@PathVariable(value = "id") int id){
       return service.deleteConcert(id);
    }


    //updating a specific concert
    @PutMapping()
    public CompletableFuture<Concert> updateConcert(@RequestBody Concert concert){
       return service.updateConcert(concert);
    }
}
