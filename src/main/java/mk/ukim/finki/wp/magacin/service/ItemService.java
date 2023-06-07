package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.service.command.CreateItemCommand;
import mk.ukim.finki.wp.magacin.service.command.UpdateItemCommand;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ItemService {
  List<Item> listAllItems(Long categoryId, Long manufacturerId, Boolean availability);
  Item findItemById(@NotNull Long id);
  void createItem(@Valid @NotNull CreateItemCommand createItemCommand);
  void updateItem(@Valid @NotNull UpdateItemCommand updateItemCommand);
  void deleteItem(@NotNull Long id);
  List<Item> searchItemsByName(@NotBlank String search);
}
