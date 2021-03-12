package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.ShoppingCart;
import mk.ukim.finki.wp.magacin.models.ShoppingCartItem;
import mk.ukim.finki.wp.magacin.models.Warehouse;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart findById(Long id);
    ShoppingCart deleteShoppingCart(Long id);
    ShoppingCart deleteItem(ShoppingCartItem item, Long id);
    ShoppingCart deleteAllItems(Long id);
    ShoppingCart getShoppingCart(String username);
    List<Item> listAllProductsInShoppingCart(Long id);
    List<ShoppingCartItem> listAllItems(Long id);
    void addProductToShoppingCart(String username, Long id, Long fromWarehouse);
}
