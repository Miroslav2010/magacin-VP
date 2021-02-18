package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Category;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Manufacturer;
import mk.ukim.finki.wp.magacin.repository.ItemRepository;

import java.util.List;

public interface ItemService {
    List<Item> listAll();
    Item findbyId(String name);
    Item create(String name, String description, String imageUrl, Boolean availability, Double price, List<Long> eachItemIds, List<Long> warehousesIds, String categoryId, String manufacturerId);
    Item update(String name, String description, String imageUrl, Boolean availability, Double price, List<Long> eachItemIds, List<Long> warehousesIds, String categoryId, String manufacturerId);
    Item delete(String name);
}
