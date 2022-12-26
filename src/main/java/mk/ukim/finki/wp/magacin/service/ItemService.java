package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.service.command.CreateItemCommand;
import mk.ukim.finki.wp.magacin.service.command.UpdateItemCommand;

import java.util.List;

public interface ItemService {
    List<Item> listAll();
    Item findById(Long id);
    void create(CreateItemCommand createItemCommand);
    void update(UpdateItemCommand updateItemCommand);
    void delete(Long id);
    List<String> getItemNames();
    List<Item> searchItemsByName(String query);
    List<Item> getItemsByCategoryAndManufacturer(Long categoryId, Long manufacturerId, boolean available);
}
