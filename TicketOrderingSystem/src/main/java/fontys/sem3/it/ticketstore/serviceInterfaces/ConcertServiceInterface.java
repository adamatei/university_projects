package fontys.sem3.it.ticketstore.serviceInterfaces;

import fontys.sem3.it.ticketstore.model.Cart;
import fontys.sem3.it.ticketstore.model.Concert;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ConcertServiceInterface {

    //getting all the concerts
    CompletableFuture<List<Concert>> getConcerts();

    //getting one concert based on id
    CompletableFuture<Concert> getConcertById(int id);

    //adding a new concert
    CompletableFuture<Concert> saveConcert(Concert concert);

    //delete an existing concert
    CompletableFuture<Boolean> deleteConcert(int id);

    //update an existing concert
    CompletableFuture<Concert> updateConcert(Concert concert);
}
