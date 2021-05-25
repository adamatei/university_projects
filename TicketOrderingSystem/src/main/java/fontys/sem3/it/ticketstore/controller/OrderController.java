package fontys.sem3.it.ticketstore.controller;

import fontys.sem3.it.ticketstore.model.TicketOrder;
import fontys.sem3.it.ticketstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/ticketOrders")
public class OrderController {

    @Autowired
    private OrderService service;

    //getting all ticketOrders
    @GetMapping()
    public CompletableFuture<List<TicketOrder>> getAllOrders(){
      return service.getAllOrders();
    }

    //getting a particular order based on id
    @GetMapping("{id}")
    //GET at http://localhost:XXXX/ticketOrders/3
    public CompletableFuture<TicketOrder> getOrder(@PathVariable(value = "id") int id) {
       return service.getOrder(id);
    }


    //getting ticketOrders based on customer's id
    @GetMapping("/customerId/{customerId}")
    public CompletableFuture<List<TicketOrder>> getOrderByCustomerId(@PathVariable(value = "customerId") int customerId) {
       return service.getOrderByCustomerId(customerId);
    }

    //adding a new order to the list
    @PostMapping()
    public CompletableFuture<TicketOrder> addOrder(@RequestBody TicketOrder ticketOrder){
      return service.addOrder(ticketOrder);
    }

    //removing a order from the list
    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/carts/3
    public CompletableFuture<Boolean> removeOrder(@PathVariable(value = "id") int id){
       return service.removeOrder(id);
    }


    //updating a specific order
    @PutMapping()
    public CompletableFuture<TicketOrder> updateOrder(@RequestBody TicketOrder ticketOrder){
       return service.updateOrder(ticketOrder);
    }
}
