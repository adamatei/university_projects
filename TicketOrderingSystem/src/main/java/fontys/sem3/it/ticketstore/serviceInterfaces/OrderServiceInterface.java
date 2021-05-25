package fontys.sem3.it.ticketstore.serviceInterfaces;

import fontys.sem3.it.ticketstore.model.TicketOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface OrderServiceInterface {

    //getting all the ticketOrders
    CompletableFuture<List<TicketOrder>> getAllOrders();

    //getting a particular order based on id
    CompletableFuture<TicketOrder> getOrder(int id);

    //getting a particular order based on customer id
    CompletableFuture<List<TicketOrder>> getOrderByCustomerId(int customerId);

    //adding a new order to the list
    CompletableFuture<TicketOrder> addOrder(TicketOrder ticketOrder);

    //removing a order from the list
    CompletableFuture<Boolean> removeOrder(int id);

    //updating a specific order
    CompletableFuture<TicketOrder> updateOrder(TicketOrder ticketOrder);
}
