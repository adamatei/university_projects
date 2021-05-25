package fontys.sem3.it.ticketstore.controller;

import fontys.sem3.it.ticketstore.model.Category;
import fontys.sem3.it.ticketstore.model.Ticket;
import fontys.sem3.it.ticketstore.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    //getting all the available tickets for the up-coming concerts
    @GetMapping()
    public CompletableFuture<List<Ticket>> getAllTickets(){
       return service.getTickets();
    }

    //getting a particular ticket based on id
    @GetMapping("{id}")
    //GET at http://localhost:XXXX/tickets/3
    public CompletableFuture<Ticket> getTicket(@PathVariable(value = "id") int id) {
      return service.getTicket(id);
    }

    //getting a particular ticket based on icategory and concert
    @GetMapping("/ticket/{concertId}/{categoryId}")
    public CompletableFuture<Ticket> getTicketByConcertAndCategory(@PathVariable(value = "concertId") int concertId, @PathVariable(value = "categoryId") int categoryId) {
        return service.getTicketByConcertAndCategory(concertId, categoryId);
    }

    //adding a new ticket to the list
    @PostMapping()
    public CompletableFuture<Ticket> addTicket(@RequestBody Ticket ticket){
      return service.saveTicket(ticket);
    }

    //removing a ticket from the list
    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/tickets/3
    public CompletableFuture<Boolean> removeTicket(@PathVariable(value = "id") int id){
       return service.deleteTicket(id);
    }


    //updating a specific ticket
    @PutMapping()
    public CompletableFuture<Ticket> updateTicket(@RequestBody Ticket ticket){
       return  service.updateTicket(ticket);
    }


    //getting the price for a specific ticket based on its concert and category
    @GetMapping("{concertId}/{categoryId}")
    public CompletableFuture<Double> getPriceByConcertAndCategory(@PathVariable(value = "concertId") int concertId, @PathVariable(value = "categoryId") int categoryId) {
        return service.getTicketPriceByConcertAndCategory(concertId,categoryId);
    }


    //getting all the ticket categories available for a specific concert89ik
    @GetMapping("/category/{concertId}")
    public  CompletableFuture<List<Category>>  getTicketCategoryByConcert(@PathVariable(value = "concertId") int concertId){
        return service.getTicketCategoryByConcert(concertId);
    }
}
