package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.*;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidItemIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidManufacturerIdException;
import mk.ukim.finki.wp.magacin.repository.*;
import mk.ukim.finki.wp.magacin.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final EachItemRepository eachItemRepository;
    private final WarehouseRepository warehouseRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ItemServiceImpl(ItemRepository itemRepository, EachItemRepository eachItemRepository, WarehouseRepository warehouseRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.itemRepository = itemRepository;
        this.eachItemRepository = eachItemRepository;
        this.warehouseRepository = warehouseRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Item> listAll() {
        return this.itemRepository.findAll();
    }

    @Override
    public Item findbyId(String name) {
        return this.itemRepository.findById(name).orElseThrow(InvalidItemIdException::new);
    }

    @Override
    public Item create(String name, String description, String imageUrl, Boolean availability, Double price, List<Long> eachItemIds, List<Long> warehousesIds, String categoryId, String manufacturerId) {
        List<EachItem> eachItems = this.eachItemRepository.findAllById(eachItemIds);
        List<Warehouse> warehouses = this.warehouseRepository.findAllById(eachItemIds);
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new);
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).orElseThrow(InvalidManufacturerIdException::new);
        return this.itemRepository.save(new Item(name,description,imageUrl,availability,price,eachItems,warehouses,category,manufacturer));
    }

    @Override
    public Item update(String name, String description, String imageUrl, Boolean availability, Double price, List<Long> eachItemIds, List<Long> warehousesIds, String categoryId, String manufacturerId) {
        Item item = this.findbyId(name);
        item.setName(name);
        item.setDescription(description);
        item.setImageUrl(imageUrl);
        item.setAvailability(availability);
        item.setPrice(price);
        List<EachItem> eachItems = this.eachItemRepository.findAllById(eachItemIds);
        item.setEachItemList(eachItems);
        List<Warehouse> warehouses = this.warehouseRepository.findAllById(eachItemIds);
        item.setWarehouses(warehouses);
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new);
        item.setCategory(category);
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).orElseThrow(InvalidManufacturerIdException::new);
        item.setManufacturer(manufacturer);
        return this.itemRepository.save(item);

    }

    @Override
    public Item delete(String name) {
        Item item = this.findbyId(name);
        this.itemRepository.delete(item);
        return item;
    }

}
