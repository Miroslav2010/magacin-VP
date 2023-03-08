package mk.ukim.finki.wp.magacin.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.models.WarehouseItem;
import mk.ukim.finki.wp.magacin.models.Order;
import mk.ukim.finki.wp.magacin.models.ShoppingCartItem;
import mk.ukim.finki.wp.magacin.models.enumerations.OrderStatus;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidItemIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidOrderIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.UserNotFoundException;
import mk.ukim.finki.wp.magacin.repository.ItemRepository;
import mk.ukim.finki.wp.magacin.repository.OrderRepository;
import mk.ukim.finki.wp.magacin.repository.ShoppingCartItemRepository;
import mk.ukim.finki.wp.magacin.repository.UserRepository;
import mk.ukim.finki.wp.magacin.service.WarehouseItemService;
import mk.ukim.finki.wp.magacin.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final ItemRepository itemRepository;
  private final WarehouseItemService warehouseItemService;
  private final ShoppingCartItemRepository shoppingCartItemRepository;

    /*@Override
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
            List<WarehouseItem> list = item.getWarehouseItemList();
            boolean tmp = this.warehouseItemService.lowerQuantity(list.get(list.size()-1).getId());
            if(!tmp){
                list.remove(list.get(list.size()-1)); //in case something breaks old was list.remove(item)
            }
        }
        Order order = new Order(this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new),
                this.itemRepository.findAllById(items),firstName,lastName,email,address,country,city,zipCode);
        this.orderRepository.save(order);
        return order;
    }*/

  @Override
  public void placeOrder(String firstName, String lastName, String email,
                         String address, String country, String city,
                         String zipCode, Double totalPrice, List<Long> items, String username) {
    for (Long itemId : items) {
      ShoppingCartItem item = this.shoppingCartItemRepository.findById(itemId)
        .orElseThrow(InvalidItemIdException::new);
      List<WarehouseItem> list = item.getItem().getWarehouseItemList();
      boolean tmp = this.warehouseItemService.lowerQuantity(list.get(list.size() - 1).getId(), item.getQuantity());
      if (!tmp) {
        list.remove(list.get(list.size() - 1));
      }
    }
    Order order = new Order(this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new),
      this.shoppingCartItemRepository.findAllById(items), firstName, lastName, email, address, country, city, zipCode, totalPrice);
    this.orderRepository.save(order);
  }

  @Override
  public void updateOrder(Long id, String firstName, String lastName, String address, String email, String city, String country, String zipcode, OrderStatus status) {
    Order order = this.findById(id);
    order.setFirstName(firstName);
    order.setLastName(lastName);
    order.setAddress(address);
    order.setEmail(email);
    order.setCity(city);
    order.setCountry(country);
    order.setZipCode(zipcode);
    order.setStatus(status);
    this.orderRepository.save(order);
  }

  @Override
  public Order delete(Long id) {
    Order order = this.findById(id);
    this.orderRepository.delete(order);
    return order;
  }

  @Override
  public Order findById(Long id) {
    return this.orderRepository.findById(id).orElseThrow(InvalidOrderIdException::new);
  }

  @Override
  public List<Order> listAll() {
    return this.orderRepository.findAll();
  }

  @Override
  public void cancelOrder(Long id) {
    Order order = this.findById(id);
    order.setStatus(OrderStatus.CANCELED);
    this.orderRepository.save(order);
  }
}
