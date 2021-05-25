package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.TicketOrder;
import fontys.sem3.it.ticketstore.repository.OrderRepository;
import fontys.sem3.it.ticketstore.serviceInterfaces.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService implements OrderServiceInterface {

    @Autowired
    private OrderRepository repository;

    //getting all the ticketOrders
    @Async
    public CompletableFuture<List<TicketOrder>> getAllOrders(){
        return  CompletableFuture.completedFuture(repository.findAll());
    }

    //getting a particular order based on id
    @Async
    public CompletableFuture<TicketOrder> getOrder(int id) {
        return CompletableFuture.completedFuture(repository.findById(id).orElse(null));
    }

    //getting a particular order based on customer id
    @Async
    public CompletableFuture<List<TicketOrder>> getOrderByCustomerId(int customerId){
       List<TicketOrder> ticketOrders = new ArrayList<>();
        for(TicketOrder ticketOrder : repository.findAll()){
            if(ticketOrder.getApiUser().getId()==customerId){
                ticketOrders.add(ticketOrder);
            }
        }
        return CompletableFuture.completedFuture(ticketOrders);
    }

    //adding a new order to the list
    @Async
    public CompletableFuture<TicketOrder> addOrder(TicketOrder ticketOrder){ return CompletableFuture.completedFuture(repository.save(ticketOrder)); }

    //removing a order from the list
    @Async
    public CompletableFuture<Boolean> removeOrder(int id){ repository.deleteById(id); return CompletableFuture.completedFuture(true);}

    //updating a specific order
    @Async
    public CompletableFuture<TicketOrder> updateOrder(TicketOrder ticketOrder){
        TicketOrder existingTicketOrder = repository.findById(ticketOrder.getId()).orElse(null);
        existingTicketOrder.setApiUser(ticketOrder.getApiUser());
        existingTicketOrder.setTotalPrice(ticketOrder.getTotalPrice());
        existingTicketOrder.setDeliveryAddress(ticketOrder.getDeliveryAddress());
        existingTicketOrder.setPaymentMethod(ticketOrder.getPaymentMethod());
        existingTicketOrder.setItems(ticketOrder.getItems());
        return CompletableFuture.completedFuture(repository.save(existingTicketOrder));
    }
}
