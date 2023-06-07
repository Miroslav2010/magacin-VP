package mk.ukim.finki.wp.magacin.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.models.*;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidItemIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidManufacturerIdException;
import mk.ukim.finki.wp.magacin.repository.*;
import mk.ukim.finki.wp.magacin.service.command.UpdateItemCommand;
import mk.ukim.finki.wp.magacin.service.mapper.ItemDomainMapper;
import mk.ukim.finki.wp.magacin.service.ItemService;
import mk.ukim.finki.wp.magacin.service.command.CreateItemCommand;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Validated
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
  private final ItemRepository itemRepository;
  private final CategoryRepository categoryRepository;
  private final ManufacturerRepository manufacturerRepository;

  private final ItemDomainMapper itemDomainMapper;

  @Override
  public List<Item> listAllItems(Long categoryId, Long manufacturerId, Boolean availability) {
    Stream<Item> itemsStream = itemRepository.findAll().stream();
    if (categoryId != null) {
      itemsStream = itemsStream.filter(item -> Objects.equals(item.getCategory().getId(), categoryId));
    }
    if (manufacturerId != null) {
      itemsStream = itemsStream.filter(item -> Objects.equals(item.getManufacturer().getId(), manufacturerId));
    }
    if (availability != null) {
      itemsStream = itemsStream.filter(Item::isAvailable);
    }
    return itemsStream.collect(Collectors.toList());
  }

  @Override
  public Item findItemById(Long id) {
    return itemRepository.findById(id).orElseThrow(InvalidItemIdException::new);
  }

  @Override
  public void createItem(CreateItemCommand createItemCommand) {
    Category category = categoryRepository.findById(createItemCommand.getCategoryId())
      .orElseThrow(InvalidCategoryIdException::new);

    Manufacturer manufacturer = manufacturerRepository.findById(createItemCommand.getManufacturerId())
        .orElseThrow(InvalidManufacturerIdException::new);

    Item item = itemDomainMapper.toDomain(createItemCommand);
    item.setCategory(category);
    item.setManufacturer(manufacturer);

    itemRepository.save(item);
  }

  @Override
  public void updateItem(UpdateItemCommand updateItemCommand) {
    Item item = findItemById(updateItemCommand.getId());
    item.setName(updateItemCommand.getName());
    item.setDescription(updateItemCommand.getDescription());
    item.setPrice(updateItemCommand.getPrice());

    Category category =
      categoryRepository.findById(updateItemCommand.getCategoryId()).orElseThrow(InvalidCategoryIdException::new);
    item.setCategory(category);

    Manufacturer manufacturer =
      manufacturerRepository.findById(updateItemCommand.getManufacturerId())
        .orElseThrow(InvalidManufacturerIdException::new);
    item.setManufacturer(manufacturer);

    itemRepository.save(item);
  }

  @Override
  public void deleteItem(Long id) {
    itemRepository.deleteById(id);
  }

  @Override
  public List<Item> searchItemsByName(String search) {
    return itemRepository.findAllByNameContainsIgnoreCase(search);
  }
}
