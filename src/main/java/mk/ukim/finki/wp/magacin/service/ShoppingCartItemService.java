package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.ShoppingCart;
import mk.ukim.finki.wp.magacin.models.ShoppingCartItem;

public interface ShoppingCartItemService {
    ShoppingCartItem findById(Long id);
    ShoppingCartItem create(Integer quantity, Item item, ShoppingCart shoppingCart,Long warehouse);
    ShoppingCartItem updateQuantityOfItem(Long id, Integer quantity);
}
