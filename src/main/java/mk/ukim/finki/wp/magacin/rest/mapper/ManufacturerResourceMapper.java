package mk.ukim.finki.wp.magacin.rest.mapper;

import mk.ukim.finki.wp.magacin.models.Manufacturer;
import mk.ukim.finki.wp.magacin.rest.model.ManufacturerResource;
import mk.ukim.finki.wp.magacin.rest.model.ManufacturerResponseBody;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface ManufacturerResourceMapper {

  default ManufacturerResponseBody toResource(List<Manufacturer> manufacturers) {
    return new ManufacturerResponseBody()
      .manufacturers(toResourceList(manufacturers));
  }

  List<ManufacturerResource> toResourceList(List<Manufacturer> manufacturers);

  ManufacturerResource toResource(Manufacturer manufacturer);
}
