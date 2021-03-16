package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.ShoppingCart;
import mk.ukim.finki.wp.magacin.models.ShoppingCartItem;
import mk.ukim.finki.wp.magacin.models.User;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidItemIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidShoppingCartIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.magacin.models.exceptions.UserNotFoundException;
import mk.ukim.finki.wp.magacin.repository.*;
import mk.ukim.finki.wp.magacin.service.ShoppingCartItemService;
import mk.ukim.finki.wp.magacin.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ShoppingCartItemService shoppingCartItemService;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final WarehouseRepository warehouseRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ItemRepository itemRepository, ShoppingCartItemService shoppingCartItemService, ShoppingCartItemRepository shoppingCartItemRepository, WarehouseRepository warehouseRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.shoppingCartItemService = shoppingCartItemService;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.warehouseRepository = warehouseRepository;
    }


    @Override
    public ShoppingCart findById(Long id) {
        return this.shoppingCartRepository.findById(id).orElseThrow(InvalidShoppingCartIdException::new);
    }

    @Override
    public ShoppingCart deleteShoppingCart(Long id) {
        ShoppingCart shoppingCart = this.findById(id);
        User user = this.userRepository.findByUsername(shoppingCart.getUser().getUsername()).orElseThrow(InvalidUsernameOrPasswordException::new);
        user.setShoppingCart(null);
        this.userRepository.save(user);
        this.shoppingCartRepository.delete(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart deleteItem(ShoppingCartItem item, Long id) {
        ShoppingCart cart = this.shoppingCartRepository.findById(id).orElseThrow(InvalidShoppingCartIdException::new);
        List<ShoppingCartItem> itemList = cart.getShoppingCartItems();
        itemList.remove(item);
        cart.setShoppingCartItems(itemList);
        this.shoppingCartItemRepository.delete(item);
        return this.shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart deleteAllItems(Long id) {
        ShoppingCart cart = this.shoppingCartRepository.findById(id).orElseThrow(InvalidShoppingCartIdException::new);
        for (ShoppingCartItem item:cart.getShoppingCartItems()) {
            item.setShoppingCart(null);
        }
        cart.setShoppingCartItems(new ArrayList<>());
        this.shoppingCartRepository.save(cart);
        return cart;
    }

    @Override
    public ShoppingCart getShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
        return this.shoppingCartRepository
                .findByUser(user)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    user.setShoppingCart(cart);
                    cart = this.shoppingCartRepository.save(cart);
                    this.userRepository.save(user);
                    return cart;
                });

    }

    @Override
    public List<Item> listAllProductsInShoppingCart(Long id) {
        ShoppingCart cart = this.shoppingCartRepository.findById(id).orElseThrow(InvalidShoppingCartIdException::new);
        return cart.getItems();
    }

    @Override
    public List<ShoppingCartItem> listAllItems(Long id) {
        ShoppingCart cart = this.shoppingCartRepository.findById(id).orElseThrow(InvalidShoppingCartIdException::new);
        return cart.getShoppingCartItems();
    }

    @Override
    public void addProductToShoppingCart(String username, Long id, Long fromWarehouse) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findByUser(this.userRepository.findByUsername(username).orElseThrow(InvalidUsernameOrPasswordException::new)).orElseThrow(InvalidShoppingCartIdException::new);
        Item item = this.itemRepository.findById(id).orElseThrow(InvalidItemIdException::new);
        Optional<ShoppingCartItem> shoppingCartItemOpt = this.shoppingCartItemRepository.findByItemAndShoppingCartAndWarehouse(item,shoppingCart,this.warehouseRepository.findById(fromWarehouse).get());
        if(shoppingCartItemOpt.isPresent()){
            ShoppingCartItem sci = shoppingCartItemOpt.get();
            Integer numberOfItemsInWarehouse = sci.getWarehouse().stockOfItem(item.getId());
            if(numberOfItemsInWarehouse.equals(sci.getQuantity())){
                return;
            }
            sci.setQuantity(sci.getQuantity()+1);
            List<ShoppingCartItem> list = shoppingCart.getShoppingCartItems();
            list.add(sci);
            shoppingCart.setShoppingCartItems(list);
            this.shoppingCartRepository.save(shoppingCart);
            return;
        }
        ShoppingCartItem shoppingCartItem = this.shoppingCartItemService.create(1, item, shoppingCart,fromWarehouse);
        List<ShoppingCartItem> itemList = shoppingCart.getShoppingCartItems();
        itemList.add(shoppingCartItem);
        shoppingCart.setShoppingCartItems(itemList);
        this.shoppingCartRepository.save(shoppingCart);
    }
}
