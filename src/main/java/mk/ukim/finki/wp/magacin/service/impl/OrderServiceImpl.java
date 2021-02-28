package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.EachItem;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Order;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidItemIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.UserNotFoundException;
import mk.ukim.finki.wp.magacin.repository.ItemRepository;
import mk.ukim.finki.wp.magacin.repository.OrderRepository;
import mk.ukim.finki.wp.magacin.repository.UserRepository;
import mk.ukim.finki.wp.magacin.service.EachItemService;
import mk.ukim.finki.wp.magacin.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final EachItemService eachItemService;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ItemRepository itemRepository, EachItemService eachItemService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.eachItemService = eachItemService;
    }

    @Override
    public Order placeOrder(String firstName,
                            String lastName,
                            String email,
                            String address,
                            String country,
                            String city,
                            String zipCode,
                            List<Long> items,
                            String username) {
        for (Long itemId: items) {
            Item item = this.itemRepository.findById(itemId).orElseThrow(InvalidItemIdException::new);
            List<EachItem> list = item.getEachItemList();
            boolean tmp = this.eachItemService.lowerQuantity(list.get(list.size()-1).getId());
            if(!tmp){
                list.remove(list.get(list.size()-1)); //in case something breaks old was list.remove(item)
            }
        }
        Order order = new Order(this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new),
                this.itemRepository.findAllById(items),firstName,lastName,email,address,country,city,zipCode);
        this.orderRepository.save(order);
        return order;
    }
}
