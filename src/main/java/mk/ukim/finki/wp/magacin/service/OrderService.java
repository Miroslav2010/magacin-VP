package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Order;
import mk.ukim.finki.wp.magacin.models.enumerations.OrderStatus;

import java.util.List;

public interface OrderService {
  void placeOrder(String firstName, String lastName, String email, String address, String country, String city, String zipCode, Double totalPrice, List<Long> items, String username);

  void updateOrder(Long id, String firstName, String lastName, String address, String email, String city, String country, String zipcode, OrderStatus status);

  Order delete(Long id);

  Order findById(Long id);

  List<Order> listAll();

  void cancelOrder(Long id);
}
