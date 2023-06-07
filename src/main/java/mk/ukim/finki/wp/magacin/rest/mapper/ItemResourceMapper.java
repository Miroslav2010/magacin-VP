package mk.ukim.finki.wp.magacin.rest.mapper;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Warehouse;
import mk.ukim.finki.wp.magacin.models.WarehouseItem;
import mk.ukim.finki.wp.magacin.rest.model.ItemResource;
import mk.ukim.finki.wp.magacin.rest.model.ItemsResponseBody;
import mk.ukim.finki.wp.magacin.rest.model.WarehouseResource;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface ItemResourceMapper {

  default ItemsResponseBody toResponseBody(List<Item> items) {
    ItemsResponseBody responseBody = new ItemsResponseBody();
    if (items != null) {
      responseBody.items(toResourceList(items));
    }
    return responseBody;
  }

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<ItemResource> toResourceList(List<Item> items);

  @Mapping(target = "quantity", source = "totalQuantity")
  @Mapping(target = "availableInWarehouses", source = ".", qualifiedByName = "mapItemWarehouseAvailability")
  ItemResource toResource(Item item);

  @Named("mapItemWarehouseAvailability")
  default List<WarehouseResource> mapItemWarehouseAvailability(Item item) {
    List<WarehouseItem> warehouseItems = item.getWarehouseItemList();
    return warehouseItems.stream().map(WarehouseItem::getWarehouse)
      .map(this::toWarehouseResource).collect(Collectors.toList());
  }

  WarehouseResource toWarehouseResource(Warehouse warehouse);
}
