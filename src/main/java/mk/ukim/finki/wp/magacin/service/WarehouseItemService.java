package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.WarehouseItem;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Warehouse;

import java.util.Optional;

public interface WarehouseItemService {
  Optional<WarehouseItem> findById(Long id);

  boolean lowerQuantity(Long id, Integer q);

  void addItems(int quantity, Warehouse warehouse, Item item);
}
