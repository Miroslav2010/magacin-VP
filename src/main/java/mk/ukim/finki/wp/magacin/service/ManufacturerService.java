package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Manufacturer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ManufacturerService {
  List<Manufacturer> listAll();

  Manufacturer findById(@NotNull Long id);

  Manufacturer create(@NotBlank String name);

  Manufacturer delete(@NotNull Long id);
}
