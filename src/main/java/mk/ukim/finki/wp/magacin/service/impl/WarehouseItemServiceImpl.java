package mk.ukim.finki.wp.magacin.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.models.WarehouseItem;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Warehouse;
import mk.ukim.finki.wp.magacin.repository.WarehouseItemRepository;
import mk.ukim.finki.wp.magacin.service.WarehouseItemService;
import mk.ukim.finki.wp.magacin.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseItemServiceImpl implements WarehouseItemService {
  private final WarehouseItemRepository warehouseItemRepository;
  private final ItemService itemService;

  public Optional<WarehouseItem> findById(Long id) {
    return this.warehouseItemRepository.findById(id);
  }

  @Override
  public void addItems(int quantity, Warehouse warehouse, Item item) {
    Optional<WarehouseItem> itemInWarehouseOpt = this.warehouseItemRepository.findByItemAndWarehouse(item, warehouse);
    if (itemInWarehouseOpt.isPresent()) {
      WarehouseItem itemInWarehouse = itemInWarehouseOpt.get();
      itemInWarehouse.setQuantity(itemInWarehouse.getQuantity() + quantity);
      this.warehouseItemRepository.save(itemInWarehouse);
    } else
      this.warehouseItemRepository.save(new WarehouseItem(item, warehouse, quantity));
  }

  @Override
  public boolean lowerQuantity(Long id, Integer q) {
    Optional<WarehouseItem> eachItem = this.warehouseItemRepository.findById(id);
    if (eachItem.isEmpty()) {
      return false;
    }
    WarehouseItem item = eachItem.get();
    int quantity = item.getQuantity();

    if (quantity < q) {
      return false;
    }

    quantity -= q;

    if (quantity == 0) {
      this.warehouseItemRepository.deleteById(item.getId());
      return false;
    } else {
      item.setQuantity(quantity);
      this.warehouseItemRepository.save(item);
    }
    return true;
  }
}
