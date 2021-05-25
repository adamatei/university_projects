package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.Category;
import fontys.sem3.it.ticketstore.model.Ticket;
import fontys.sem3.it.ticketstore.repository.TicketRepository;
import fontys.sem3.it.ticketstore.serviceInterfaces.TicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TicketService implements TicketServiceInterface {

    @Autowired
    private TicketRepository repository;

    //getting all the available tickets for the up-coming concerts
    @Async
    public CompletableFuture<List<Ticket>> getTickets(){ return CompletableFuture.completedFuture(repository.findAll());}

    //getting a particular ticket based on id
    @Async
    public CompletableFuture<Ticket> getTicket(int id){ return CompletableFuture.completedFuture(repository.findById(id).orElse(null));}

    //adding a new ticket to the system
    @Async
    public CompletableFuture<Ticket> saveTicket(Ticket ticket){return CompletableFuture.completedFuture(repository.save(ticket));}

    //delete a specific ticket
    @Async
    public CompletableFuture<Boolean> deleteTicket(int id){ repository.deleteById(id); return CompletableFuture.completedFuture(true);}

    //updating an existing ticket
    @Async
    public CompletableFuture<Ticket> updateTicket(Ticket ticket){
        Ticket existingTicket = repository.findById(ticket.getId()).orElse(null);
        existingTicket.setQuantity(ticket.getQuantity());
        existingTicket.setPrice(ticket.getPrice());
        existingTicket.setConcert(ticket.getConcert());
        existingTicket.setCategory(ticket.getCategory());
        return CompletableFuture.completedFuture(repository.save(existingTicket));
    }

    //getting all the categories of one's concert tickets
    @Async
    public CompletableFuture<List<Category>> getTicketCategoryByConcert(int concertId){
       List<Category> categories = new ArrayList<>();
        for(Ticket ticket : repository.findAll()){
            if((ticket.getConcert().getId() == concertId) && (!categories.contains(ticket.getCategory()))){
                categories.add(ticket.getCategory());
            }
        }
        return CompletableFuture.completedFuture(categories);
    }

    //getting the price of a specific ticket
    @Async
    public CompletableFuture<Double> getTicketPriceByConcertAndCategory(int concertId, int categoryId){
        for(Ticket ticket : repository.findAll()){
            if((ticket.getConcert().getId() == concertId) && (ticket.getCategory().getId() == categoryId)){
                return CompletableFuture.completedFuture(ticket.getPrice());
            }
        }
        return CompletableFuture.completedFuture(0.0);
    }

    //getting a ticket based on the category and concert
    @Async
    public CompletableFuture<Ticket> getTicketByConcertAndCategory(int concertId, int categoryId) {
        for (Ticket ticket : repository.findAll()) {
            if ((ticket.getConcert().getId() == concertId) && (ticket.getCategory().getId() == categoryId)) {
                return CompletableFuture.completedFuture(ticket);
            }
        }
        return null;
    }
}
