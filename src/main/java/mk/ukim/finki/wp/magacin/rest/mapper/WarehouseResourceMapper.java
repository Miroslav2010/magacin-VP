package mk.ukim.finki.wp.magacin.rest.mapper;

import mk.ukim.finki.wp.magacin.models.Warehouse;
import mk.ukim.finki.wp.magacin.rest.model.WarehouseResource;
import mk.ukim.finki.wp.magacin.rest.model.WarehousesResponseBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface WarehouseResourceMapper {

  default WarehousesResponseBody toResource(List<Warehouse> warehouses) {
    return new WarehousesResponseBody()
      .warehouses(toResourceList(warehouses));
  }

  List<WarehouseResource> toResourceList(List<Warehouse> warehouses);

  @Mapping(target = "longitude", source = "lon")
  @Mapping(target = "latitude", source = "lat")
  WarehouseResource toResource(Warehouse warehouse);
}
