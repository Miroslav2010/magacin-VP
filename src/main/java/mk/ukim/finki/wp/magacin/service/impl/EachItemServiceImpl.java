package mk.ukim.finki.wp.magacin.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.ItemInWarehouse;
import mk.ukim.finki.wp.magacin.models.Warehouse;
import mk.ukim.finki.wp.magacin.repository.EachItemRepository;
import mk.ukim.finki.wp.magacin.repository.WarehouseRepository;
import mk.ukim.finki.wp.magacin.service.EachItemService;
import mk.ukim.finki.wp.magacin.service.ItemService;
import mk.ukim.finki.wp.magacin.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EachItemServiceImpl implements EachItemService {
  private final EachItemRepository eachItemRepository;
  private final ItemService itemService;
  private final WarehouseService warehouseService;

  public Optional<ItemInWarehouse> findById(Long id) {
    return this.eachItemRepository.findById(id);
  }

  @Override
  public void addItems(int quantity, long warehouseId, long itemId) {
    Item item  = itemService.findById(itemId);
    Warehouse warehouse = warehouseService.findById(warehouseId);

    Optional<ItemInWarehouse> itemInWarehouseOptional = eachItemRepository.findByItemAndWarehouse(item, warehouse);
    if (itemInWarehouseOptional.isPresent()) {
      ItemInWarehouse itemInWarehouse = itemInWarehouseOptional.get();
      itemInWarehouse.addQuantity(quantity);
      this.eachItemRepository.save(itemInWarehouse);
    } else {
      this.eachItemRepository.save(new ItemInWarehouse(item, warehouse, quantity));
    }
  }

  @Override
  public boolean lowerQuantity(long id, int quantityToDeduct) {
    Optional<ItemInWarehouse> itemInWarehouseOptional = this.eachItemRepository.findById(id);

    if (itemInWarehouseOptional.isEmpty()) {
      return false;
    }

    ItemInWarehouse itemInWarehouse = itemInWarehouseOptional.get();
    int quantity = itemInWarehouse.getQuantity();

    if (quantity < quantityToDeduct) {
      return false;
    }

    quantity -= quantityToDeduct;

    if (quantity == 0) {
      this.eachItemRepository.deleteById(itemInWarehouse.getId());
      return false;
    }
    itemInWarehouse.setQuantity(quantity);
    this.eachItemRepository.save(itemInWarehouse);
    return true;
  }
}
