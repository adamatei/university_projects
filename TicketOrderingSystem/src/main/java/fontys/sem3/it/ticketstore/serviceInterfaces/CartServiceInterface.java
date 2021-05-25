package fontys.sem3.it.ticketstore.serviceInterfaces;

import fontys.sem3.it.ticketstore.model.Cart;
import fontys.sem3.it.ticketstore.model.Item;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CartServiceInterface {

    //getting all the carts
    CompletableFuture<List<Cart>> getAllCarts();

    //getting a particular cart based on id
    CompletableFuture<Cart> getCart(int id);

    //getting a particular cart based on customer id
    CompletableFuture<Cart> getCartByCustomerId(int customerId);

    //adding a new cart to the list
    CompletableFuture<Cart> addCart(Cart cart);

    //removing a cart from the list
    CompletableFuture<Boolean> removeCart(int id);

    //updating a specific cart
    CompletableFuture<Cart> updateCart(Cart cart);

    //add a new ticket to the cart
    CompletableFuture<Cart> addItem(int cartId, Item item);

    //getting all the items in the cart
    CompletableFuture<List<Item>> getCartItems(int customerId);

    //updating the total price
    CompletableFuture<Cart> updatePrice(int cartId);
}
