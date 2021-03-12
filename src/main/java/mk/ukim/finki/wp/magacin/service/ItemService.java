package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Category;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Manufacturer;
import mk.ukim.finki.wp.magacin.repository.ItemRepository;

import java.util.List;

public interface ItemService {
    List<Item> listAll();
    Item findbyId(Long id);
    Item create(String name, String description, String imageUrl, Boolean availability, Double price, Long categoryId, Long manufacturerId);
    Item update(Long id, String name, String description, String imageUrl, Boolean availability, Double price, Long categoryId, Long manufacturerId);
    Item delete(Long id);
    Item toggleAvailability(Long id);
    Item setAvailability(Long id,Boolean availability);
    List<String> getItemNames();
    List<Item> searchItemsByName(String search);
    List<Item> getItemsByCategoryManufacturerAndAvailability(Long categoryId,Long manufacturerId,Boolean availability);
    List<Item> findFirstThree();
}
