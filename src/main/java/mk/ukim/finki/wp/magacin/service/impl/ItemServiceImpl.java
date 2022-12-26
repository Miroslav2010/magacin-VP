package mk.ukim.finki.wp.magacin.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.models.Category;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Manufacturer;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidItemIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidManufacturerIdException;
import mk.ukim.finki.wp.magacin.repository.CategoryRepository;
import mk.ukim.finki.wp.magacin.repository.ItemRepository;
import mk.ukim.finki.wp.magacin.repository.ManufacturerRepository;
import mk.ukim.finki.wp.magacin.service.ItemService;
import mk.ukim.finki.wp.magacin.service.command.CreateItemCommand;
import mk.ukim.finki.wp.magacin.service.command.UpdateItemCommand;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
  private final ItemRepository itemRepository;
  private final CategoryRepository categoryRepository;
  private final ManufacturerRepository manufacturerRepository;

  @Override
  public List<Item> listAll() {
    return itemRepository.findAll();
  }

  @Override
  public Item findById(Long id) {
    return itemRepository.findById(id).orElseThrow(InvalidItemIdException::new);
  }

  @Override
  public void create(CreateItemCommand createItemCommand) {
    saveItemToDatabase(createItemCommand);
  }

  @Override
  public void update(UpdateItemCommand updateItemCommand) {
    delete(updateItemCommand.getId());

    saveItemToDatabase(updateItemCommand);
  }

  private void saveItemToDatabase(CreateItemCommand command) {
    Category category =
      categoryRepository.findById(command.getCategoryId()).orElseThrow(InvalidCategoryIdException::new);
    Manufacturer manufacturer =
      manufacturerRepository
        .findById(command.getManufacturerId())
        .orElseThrow(InvalidManufacturerIdException::new);

    Item item = Item.builder()
      .name(command.getName())
      .description(command.getDescription())
      .imageUrl(command.getImageUrl())
      .price(command.getPrice())
      .category(category)
      .manufacturer(manufacturer)
      .build();

    itemRepository.save(item);
  }

  @Override
  public void delete(Long id) {
    itemRepository.deleteById(id);
  }

  @Override
  public List<String> getItemNames() {
    return itemRepository.getItemNames();
  }

  @Override
  public List<Item> searchItemsByName(String query) {
    return itemRepository.findAllByNameContains(query);
  }

  @Override
  public List<Item> getItemsByCategoryAndManufacturer(Long categoryId, Long manufacturerId, boolean available) {

    List<Item> resultList;

    if (categoryId != null && manufacturerId != null) {
      resultList = itemRepository.findAllByCategoryIdAndManufacturerId(categoryId, manufacturerId);
    } else if (categoryId != null) {
      resultList = itemRepository.findAllByCategoryId(categoryId);
    } else if (manufacturerId != null) {
      resultList = itemRepository.findAllByManufacturerId(manufacturerId);
    } else {
      resultList = itemRepository.findAll();
    }
    return filterItemsByAvailability(resultList, available);
  }

  private List<Item> filterItemsByAvailability(List<Item> items, boolean availability) {

    if (!availability) {
      return items.stream().filter(item -> !item.isAvailable()).collect(Collectors.toList());
    }

    return items.stream().filter(Item::isAvailable).collect(Collectors.toList());
  }
}
