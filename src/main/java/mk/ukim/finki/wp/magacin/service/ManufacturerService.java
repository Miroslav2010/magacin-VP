package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Manufacturer;

import java.util.List;

public interface ManufacturerService {
  List<Manufacturer> listAll();

  Manufacturer findById(Long id);

  Manufacturer create(String name);

  Manufacturer delete(Long id);
}
