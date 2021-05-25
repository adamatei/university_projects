package fontys.sem3.it.ticketstore.controller;

import fontys.sem3.it.ticketstore.model.Cart;
import fontys.sem3.it.ticketstore.model.Item;
import fontys.sem3.it.ticketstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService service;

    //getting all carts
    @GetMapping()
    public CompletableFuture<List<Cart>> getAllCarts(){
       return service.getAllCarts();
    }

    //getting a particular cart based on id
    @GetMapping("{id}")
    //GET at http://localhost:XXXX/carts/3
    public CompletableFuture<Cart> getCart(@PathVariable(value = "id") int id) {
      return service.getCart(id);
    }


    //getting a particular cart based on customer's id
    @GetMapping("/customer/{customerId}")
    public CompletableFuture<Cart> getCartByCustomerId(@PathVariable(value = "customerId") int customerId) {
      return service.getCartByCustomerId(customerId);
    }


    //getting a particular cart based on customer's id
    @GetMapping("/items/{customerId}")
    public CompletableFuture<List<Item>> getCartItems(@PathVariable(value = "customerId") int customerId) {
      return service.getCartItems(customerId);
    }


    //adding a new cart to the list
    @PostMapping()
    public CompletableFuture<Cart> addCart(@RequestBody Cart cart){
       return service.addCart(cart);
    }


    //removing a cart from the list
    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/carts/3
    public CompletableFuture<Boolean> removeCart(@PathVariable(value = "id") int id){
      return service.removeCart(id);
    }


    //updating a specific cart
    @PutMapping()
    public CompletableFuture<Cart> updateCart(@RequestBody Cart cart){
       return service.updateCart(cart);
    }


    @PutMapping("/addItem/{cartId}")
    public CompletableFuture<Cart> addItemToCart(@PathVariable(value = "cartId") int cartId, @RequestBody Item item){
        return service.addItem(cartId, item);
    }

    @PutMapping("/updatePrice/{cartId}")
    public CompletableFuture<Cart> updatePrice(@PathVariable(value = "cartId") int cartId){
        return service.updatePrice(cartId);
    }
}
