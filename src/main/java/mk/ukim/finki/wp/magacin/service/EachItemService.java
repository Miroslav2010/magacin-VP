package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.ItemInWarehouse;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Warehouse;

import java.util.Optional;

public interface EachItemService {

  /**
   * Find item with corresponding warehouse by id.
   *
   * @param id the technical id of the item.
   * @return Optional of {@link ItemInWarehouse}.
   */
  Optional<ItemInWarehouse> findById(Long id);

  /**
   * Lower quantity of said item in warehouse.
   *
   * @param id technical id.
   * @param quantity quantity to lower by.
   * @return {@code true} if successfully lowered quantity.
   */
  boolean lowerQuantity(long id, int quantity);

  /**
   * Add quantity of an item to a warehouse.
   *
   * @param quantity quantity to add.
   * @param warehouseId the id of the warehouse.
   * @param itemId the id of the item to add.
   */
  void addItems(int quantity, long warehouseId, long itemId);
}
