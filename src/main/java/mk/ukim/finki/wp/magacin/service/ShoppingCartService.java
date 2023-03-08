package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.ShoppingCart;
import mk.ukim.finki.wp.magacin.models.ShoppingCartItem;

import java.util.List;

public interface ShoppingCartService {
  ShoppingCart findById(Long id);

  void deleteShoppingCart(Long id);

  void deleteItem(ShoppingCartItem item, Long id);

  void deleteAllItems(Long id);

  ShoppingCart getShoppingCart(String username);

  List<ShoppingCartItem> listAllItems(Long id);

  void addProductToShoppingCart(String username, Long id, Long fromWarehouse);
}
