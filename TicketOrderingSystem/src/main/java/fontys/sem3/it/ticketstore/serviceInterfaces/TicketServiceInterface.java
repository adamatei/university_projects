package fontys.sem3.it.ticketstore.serviceInterfaces;

import fontys.sem3.it.ticketstore.model.Category;
import fontys.sem3.it.ticketstore.model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TicketServiceInterface {

    //getting all the available tickets for the up-coming concerts
    CompletableFuture<List<Ticket>> getTickets();

    //getting a particular ticket based on id
    CompletableFuture<Ticket> getTicket(int id);

    //adding a new ticket to the system
    CompletableFuture<Ticket> saveTicket(Ticket ticket);

    //delete a specific ticket
    CompletableFuture<Boolean> deleteTicket(int id);

    //updating an existing ticket
    CompletableFuture<Ticket> updateTicket(Ticket ticket);

    //getting all the categories of one's concert tickets
    CompletableFuture<List<Category>> getTicketCategoryByConcert(int concertId);

    //getting the price of a specific ticket
    CompletableFuture<Double> getTicketPriceByConcertAndCategory(int concertId, int categoryId);

    //getting a ticket based on the category and concert
    CompletableFuture<Ticket> getTicketByConcertAndCategory(int concertId, int categoryId);
}
