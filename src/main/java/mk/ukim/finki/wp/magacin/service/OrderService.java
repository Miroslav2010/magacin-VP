package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Order;
import mk.ukim.finki.wp.magacin.models.enumerations.OrderStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderService {
    Order placeOrder(String firstName, String lastName, String email, String address, String country, String city, String zipCode,Double totalPrice, List<Long> items, String username);
    void updateOrder(Long id, String firstName, String lastName, String address, String email, String city, String country,  String zipcode, OrderStatus status);
    Order delete(Long id);
    Order findById(Long id);
    List<Order> listAll();
    Order cancelOrder(Long id);
}
