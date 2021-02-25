package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(String firstName, String lastName, String email, String address, String country, String city, String zipCode, List<Item> items, String username);
}
