package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.Cart;
import fontys.sem3.it.ticketstore.model.Item;
import fontys.sem3.it.ticketstore.repository.CartRepository;
import fontys.sem3.it.ticketstore.serviceInterfaces.CartServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CartService implements CartServiceInterface {

    @Autowired
    private CartRepository repository;

    //getting all the carts
    @Async
    public CompletableFuture<List<Cart>> getAllCarts(){
        return  CompletableFuture.completedFuture(repository.findAll());
    }

    //getting a particular cart based on id
    @Async
    public CompletableFuture<Cart> getCart(int id) { return CompletableFuture.completedFuture(repository.findById(id).orElse(null)); }

    //getting a particular cart based on customer id
    @Async
    public CompletableFuture<Cart> getCartByCustomerId(int customerId){
       for(Cart cart : repository.findAll()){
           if(cart.getApiUser().getId() == customerId){
               return CompletableFuture.completedFuture(cart);
           }
        }
       return CompletableFuture.completedFuture(null);
    }

    //adding a new cart to the list
    @Async
    public CompletableFuture<Cart> addCart(Cart cart){ return CompletableFuture.completedFuture(repository.save(cart)); }

    //removing a cart from the list
    @Async
    public CompletableFuture<Boolean> removeCart(int id){
        repository.deleteById(id);
        return CompletableFuture.completedFuture(true);
    }

    //updating a specific cart
    @Async
    public CompletableFuture<Cart> updateCart(Cart cart){
        Cart existingCart = repository.findById(cart.getId()).orElse(null);
        existingCart.setTotalPrice(cart.calculatateTotalPrice());
        existingCart.setItems(cart.getItems());
        return CompletableFuture.completedFuture(repository.save(existingCart));
    }

    //add a new ticket to the cart
    @Async
    public CompletableFuture<Cart> addItem(int cartId, Item item){
        Cart existingCart = repository.findById(cartId).orElse(null);
        existingCart.addItem(item);
        existingCart.setTotalPrice(existingCart.calculatateTotalPrice());
       return updateCart(existingCart);

    }

    //getting all the items in the cart
    @Async
    public CompletableFuture<List<Item>> getCartItems(int customerId){
        for(Cart cart : repository.findAll()){
        if(cart.getApiUser().getId() == customerId){
            return CompletableFuture.completedFuture(cart.getItems());
        }
    }
        return CompletableFuture.completedFuture(null);
    }

    //updating the total price
    @Async
    public CompletableFuture<Cart> updatePrice(int cartId){
        Cart existingCart = repository.findById(cartId).orElse(null);
        existingCart.setTotalPrice(existingCart.calculatateTotalPrice());
        return updateCart(existingCart);
    }
}
