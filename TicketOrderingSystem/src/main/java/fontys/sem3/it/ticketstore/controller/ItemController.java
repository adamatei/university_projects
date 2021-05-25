package fontys.sem3.it.ticketstore.controller;

import fontys.sem3.it.ticketstore.model.Item;
import fontys.sem3.it.ticketstore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService service;

    //getting a particular item based on id
    @GetMapping("{id}")
    public CompletableFuture<Item> getItem(@PathVariable(value = "id") int id) {
        return service.getItemById(id);
    }

    //adding a new item to the list
    @PostMapping()
    public CompletableFuture<Item> addItem(@RequestBody Item item){
        return service.saveItem(item);
    }

    //removing a item from the list
    @DeleteMapping("{id}")
    public CompletableFuture<Boolean> removeItem(@PathVariable(value = "id") int id){
        return service.deleteItem(id);
    }

    //updating a specific item
    @PutMapping()
    public CompletableFuture<Item> updateItem(@RequestBody Item item){
        return service.updateItem(item);
    }
}
