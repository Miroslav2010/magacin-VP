package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Category;
import mk.ukim.finki.wp.magacin.models.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    List<Manufacturer> listAll();
    Manufacturer findbyId(Long id);
    Manufacturer create(String name);
    Manufacturer update(Long id, String name);
    Manufacturer delete(Long id);
}
