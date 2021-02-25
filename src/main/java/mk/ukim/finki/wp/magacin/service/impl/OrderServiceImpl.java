package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Order;
import mk.ukim.finki.wp.magacin.models.exceptions.UserNotFoundException;
import mk.ukim.finki.wp.magacin.repository.OrderRepository;
import mk.ukim.finki.wp.magacin.repository.UserRepository;
import mk.ukim.finki.wp.magacin.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order placeOrder(String firstName,
                            String lastName,
                            String email,
                            String address,
                            String country,
                            String city,
                            String zipCode,
                            List<Item> items,
                            String username) {
        Order order = new Order(this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new),
                items,firstName,lastName,email,address,country,city,zipCode);
        this.orderRepository.save(order);
        return order;
    }
}
