package mk.ukim.finki.wp.magacin.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.models.*;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidItemIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidManufacturerIdException;
import mk.ukim.finki.wp.magacin.repository.*;
import mk.ukim.finki.wp.magacin.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
  private final ItemRepository itemRepository;
  private final CategoryRepository categoryRepository;
  private final ManufacturerRepository manufacturerRepository;

  @Override
  public List<Item> listAll() {
    return this.itemRepository.findAll();
  }

  @Override
  public Item findById(Long id) {
    return this.itemRepository.findById(id).orElseThrow(InvalidItemIdException::new);
  }

  @Override
  public void create(String name, String description, String imageUrl, Double price, Long categoryId, Long manufacturerId) {
    Category category = this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new);
    Manufacturer manufacturer =
      this.manufacturerRepository.findById(manufacturerId).orElseThrow(InvalidManufacturerIdException::new);

    Item item = Item.builder()
      .name(name)
      .description(description)
      .imageUrl(imageUrl)
      .price(price)
      .category(category)
      .manufacturer(manufacturer)
      .build();

    this.itemRepository.save(item);
  }

  @Override
  public void update(Long id, String name, String description, String imageUrl, Double price, Long categoryId, Long manufacturerId) {
    Item item = this.findById(id);
    item.setName(name);
    item.setDescription(description);
    item.setImageUrl(imageUrl);
    item.setPrice(price);
    Category category = this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new);
    item.setCategory(category);
    Manufacturer manufacturer =
      this.manufacturerRepository.findById(manufacturerId).orElseThrow(InvalidManufacturerIdException::new);
    item.setManufacturer(manufacturer);
    this.itemRepository.save(item);
  }

  @Override
  public void delete(Long id) {
    this.itemRepository.deleteById(id);
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
    // TODO implemented this method
    return this.itemRepository.findAll();
  }
}
