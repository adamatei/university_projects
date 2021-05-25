package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.Concert;
import fontys.sem3.it.ticketstore.repository.ConcertRepository;
import fontys.sem3.it.ticketstore.serviceInterfaces.ConcertServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ConcertService implements ConcertServiceInterface {

    @Autowired
    private ConcertRepository repository;

    //getting all the concerts
    @Async
    public CompletableFuture<List<Concert>> getConcerts(){ return CompletableFuture.completedFuture(repository.findAll()); }

    //getting one concert based on id
    @Async
    public CompletableFuture<Concert> getConcertById(int id){
        return CompletableFuture.completedFuture(repository.findById(id).orElse(null));
    }

    //adding a new concert
    @Async
    public CompletableFuture<Concert> saveConcert(Concert concert){
        return CompletableFuture.completedFuture(repository.save(concert));
    }

    //delete an existing concert
    @Async
    public CompletableFuture<Boolean> deleteConcert(int id){
        repository.deleteById(id);
        return CompletableFuture.completedFuture(true);
    }

    //update an existing concert
    @Async
    public CompletableFuture<Concert> updateConcert(Concert concert){
        Concert existingConcert = repository.findById(concert.getId()).orElse(null);
        existingConcert.setLocation(concert.getLocation());
        existingConcert.setCountry(concert.getCountry());
        existingConcert.setCity(concert.getCity());
        existingConcert.setDateTime(concert.getDateTime());
        return CompletableFuture.completedFuture(repository.save(existingConcert));
    }
}
