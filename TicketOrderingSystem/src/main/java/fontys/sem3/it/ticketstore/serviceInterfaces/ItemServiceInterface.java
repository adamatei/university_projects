package fontys.sem3.it.ticketstore.serviceInterfaces;

import fontys.sem3.it.ticketstore.model.Item;

import java.util.concurrent.CompletableFuture;

public interface ItemServiceInterface {

    //getting one item based on id
    CompletableFuture<Item> getItemById(int id);

    //adding a new item
    CompletableFuture<Item> saveItem(Item item);

    //delete an existing item
    CompletableFuture<Boolean> deleteItem(int id);

    //update an existing item
    CompletableFuture<Item> updateItem(Item item);
}
