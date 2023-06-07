package mk.ukim.finki.wp.magacin.rest.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.rest.ItemApi;
import mk.ukim.finki.wp.magacin.rest.mapper.ItemCommandMapper;
import mk.ukim.finki.wp.magacin.rest.mapper.ItemResourceMapper;
import mk.ukim.finki.wp.magacin.rest.model.CreateItemRequestBody;
import mk.ukim.finki.wp.magacin.rest.model.ItemResource;
import mk.ukim.finki.wp.magacin.rest.model.ItemsResponseBody;
import mk.ukim.finki.wp.magacin.rest.model.UpdateItemRequestBody;
import mk.ukim.finki.wp.magacin.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController implements ItemApi {

  private final ItemService itemService;

  private final ItemResourceMapper itemResourceMapper;

  private final ItemCommandMapper itemCommandMapper;

  @Override
  public ResponseEntity<ItemsResponseBody> getItems(Long categoryId, Long manufacturerId, Boolean availability) {
    return ResponseEntity.ok(
      itemResourceMapper.toResponseBody(itemService.listAllItems(categoryId, manufacturerId, availability))
    );
  }

  @Override
  public ResponseEntity<ItemResource> getItem(Long itemId) {

    return ResponseEntity.ok(itemResourceMapper.toResource(itemService.findItemById(itemId)));
  }

  @Override
  public ResponseEntity<Void> deleteItem(Long itemId) {
    itemService.deleteItem(itemId);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<ItemsResponseBody> searchItems(String query) {
    return ResponseEntity.ok(itemResourceMapper.toResponseBody(itemService.searchItemsByName(query)));
  }

  @Override
  public ResponseEntity<Void> createItem(CreateItemRequestBody createItemRequestBody) {
    itemService.createItem(itemCommandMapper.toCommand(createItemRequestBody));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> updateItem(UpdateItemRequestBody updateItemRequestBody) {
    itemService.updateItem(itemCommandMapper.toCommand(updateItemRequestBody));
    return ResponseEntity.noContent().build();
  }
}
