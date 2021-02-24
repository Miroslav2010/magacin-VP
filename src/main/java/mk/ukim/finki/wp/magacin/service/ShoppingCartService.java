package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart deleteItem(Item item, Long id);
    ShoppingCart deleteAllItems(Long id);
    ShoppingCart getShoppingCart(String username);
    List<Item> listAllProductsInShoppingCart(Long id);
    void addProductToShoppingCart(String username, Long id);
}
