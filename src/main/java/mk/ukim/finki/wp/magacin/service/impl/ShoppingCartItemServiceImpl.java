package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.ShoppingCart;
import mk.ukim.finki.wp.magacin.models.ShoppingCartItem;
import mk.ukim.finki.wp.magacin.repository.ShoppingCartItemRepository;
import mk.ukim.finki.wp.magacin.repository.WarehouseRepository;
import mk.ukim.finki.wp.magacin.service.ShoppingCartItemService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final WarehouseRepository warehouseRepository;

    public ShoppingCartItemServiceImpl(ShoppingCartItemRepository shoppingCartItemRepository, WarehouseRepository warehouseRepository) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public ShoppingCartItem findById(Long id) {
        return this.shoppingCartItemRepository.findById(id).get();
    }

    @Override
    public ShoppingCartItem create(Integer quantity, Item item, ShoppingCart shoppingCart,Long warehouse) {
        return this.shoppingCartItemRepository.save(new ShoppingCartItem(quantity,item,shoppingCart,this.warehouseRepository.findById(warehouse).get()));
    }

    @Override
    public ShoppingCartItem updateQuantityOfItem(Long id, Integer quantity) {
        ShoppingCartItem item = this.shoppingCartItemRepository.findById(id).get();
        item.setQuantity(quantity);
        return this.shoppingCartItemRepository.save(item);
    }
}
