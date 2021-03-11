package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.ShoppingCart;
import mk.ukim.finki.wp.magacin.models.ShoppingCartItem;
import mk.ukim.finki.wp.magacin.repository.ShoppingCartItemRepository;
import mk.ukim.finki.wp.magacin.service.ShoppingCartItemService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {
    private final ShoppingCartItemRepository shoppingCartItemRepository;

    public ShoppingCartItemServiceImpl(ShoppingCartItemRepository shoppingCartItemRepository) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
    }

    @Override
    public ShoppingCartItem create(Integer quantity, Item item, ShoppingCart shoppingCart) {
        return this.shoppingCartItemRepository.save(new ShoppingCartItem(quantity,item,shoppingCart));
    }
}
