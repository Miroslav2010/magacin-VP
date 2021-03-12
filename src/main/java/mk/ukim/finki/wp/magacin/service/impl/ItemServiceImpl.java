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
    public Item findbyId(Long id) {
        return this.itemRepository.findById(id).orElseThrow(InvalidItemIdException::new);
    }


    @Override
    public Item create(String name, String description, String imageUrl, Boolean availability, Double price, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new);
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).orElseThrow(InvalidManufacturerIdException::new);
        Item item = new Item(name,description,imageUrl,availability,price,category,manufacturer);
        this.itemRepository.save(item);
        return item;
    }

    @Override
    public Item update(Long id, String name, String description, String imageUrl, Boolean availability, Double price, Long categoryId, Long manufacturerId) {
        Item item = this.findbyId(id);
        item.setName(name);
        item.setDescription(description);
        item.setImageUrl(imageUrl);
        item.setAvailability(availability);
        item.setPrice(price);
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new);
        item.setCategory(category);
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).orElseThrow(InvalidManufacturerIdException::new);
        item.setManufacturer(manufacturer);
        return this.itemRepository.save(item);

    }

    @Override
    public Item delete(Long id) {
        Item item = this.findbyId(id);
        this.itemRepository.delete(item);
        return item;
    }

    @Override
    public Item toggleAvailability(Long id) {
        Item item = this.itemRepository.findById(id).orElseThrow(InvalidItemIdException::new);
        item.setAvailability(!item.getAvailability());
        this.itemRepository.save(item);
        return item;
    }

    @Override
    public List<String> getItemNames() {
        return this.itemRepository.getItemNames();
    }

    @Override
    public List<Item> searchItemsByName(String search) {
        return this.itemRepository.findAllByNameContains(search);
    }

    @Override
    public List<Item> getItemsByCategoryManufacturerAndAvailability(Long categoryId, Long manufacturerId, Boolean availability) {
        if(categoryId!=null && manufacturerId == null && availability!=null && availability==false){
            return this.itemRepository.findAllByCategory(this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new));
        }else if(categoryId==null && manufacturerId!=null && availability!=null && availability==false){
            return this.itemRepository.findAllByManufacturer(this.manufacturerRepository.findById(manufacturerId).orElseThrow(InvalidManufacturerIdException::new));
        }else if(categoryId!=null && manufacturerId!=null && availability!=null && availability==false){
            return  this.itemRepository.findAllByCategoryAndManufacturer(this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new),
                    this.manufacturerRepository.findById(manufacturerId).orElseThrow(InvalidManufacturerIdException::new));
        }else if(categoryId!=null && manufacturerId!=null && availability!=null && availability==true){
            return this.itemRepository.findAllByCategoryAndManufacturerAndAvailability(this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new)
            ,this.manufacturerRepository.findById(manufacturerId).orElseThrow(InvalidManufacturerIdException::new),true);
        }else if(categoryId==null && manufacturerId==null && availability!=null && availability==true){
            return this.itemRepository.findAllByAvailability(true);
        }else if(categoryId!=null && manufacturerId == null && availability!=null && availability==true){
            return this.itemRepository.findAllByCategoryAndAvailability(this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new),true);
        }else if(categoryId==null && manufacturerId!=null && availability!=null && availability==true){
            return this.itemRepository.findAllByManufacturerAndAvailability(this.manufacturerRepository.findById(manufacturerId).orElseThrow(InvalidManufacturerIdException::new),true);
        }
        return this.itemRepository.findAll();
    }

    @Override
    public Item setAvailability(Long id,Boolean availability) {
        Item item = this.itemRepository.findById(id).orElseThrow(InvalidItemIdException::new);
        item.setAvailability(availability);
        return this.itemRepository.save(item);
    }

    @Override
    public List<Item> findFirstThree() {
        return this.itemRepository.findFirst3ByOrderById();
    }
}
