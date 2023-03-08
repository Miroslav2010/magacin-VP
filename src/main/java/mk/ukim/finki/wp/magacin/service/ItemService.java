package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Item;

import java.util.List;

public interface ItemService {
  List<Item> listAll();
  Item findById(Long id);
  void create(String name, String description, String imageUrl, Double price, Long categoryId, Long manufacturerId);
  void update(Long id, String name, String description, String imageUrl, Double price, Long categoryId, Long manufacturerId);
  void delete(Long id);
  List<String> getItemNames();
  List<Item> searchItemsByName(String search);
  List<Item> getItemsByCategoryManufacturerAndAvailability(Long categoryId, Long manufacturerId, Boolean availability);
}
