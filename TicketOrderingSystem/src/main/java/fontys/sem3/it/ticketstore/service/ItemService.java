package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.Item;
import fontys.sem3.it.ticketstore.repository.ItemRepository;
import fontys.sem3.it.ticketstore.serviceInterfaces.ItemServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ItemService implements ItemServiceInterface {

    @Autowired
    private ItemRepository repository;

    //getting one item based on id
    @Async
    public CompletableFuture<Item> getItemById(int id){
        return CompletableFuture.completedFuture(repository.findById(id).orElse(null));
    }

    //adding a new item
    @Async
    public CompletableFuture<Item> saveItem(Item item){
        return CompletableFuture.completedFuture(repository.save(item));
    }

    //delete an existing item
    @Async
    public CompletableFuture<Boolean> deleteItem(int id){
        repository.deleteById(id);
        return CompletableFuture.completedFuture(true);
    }

    //update an existing item
    @Async
    public CompletableFuture<Item> updateItem(Item item){
        Item existingItem = repository.findById(item.getId()).orElse(null);
        existingItem.setPrice(item.calculatePrice());
        existingItem.setQuantity(item.getQuantity());
        existingItem.setTicket(item.getTicket());
        return CompletableFuture.completedFuture(repository.save(existingItem));
    }
}
