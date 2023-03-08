package mk.ukim.finki.wp.magacin.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.models.Manufacturer;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidManufacturerIdException;
import mk.ukim.finki.wp.magacin.repository.ItemRepository;
import mk.ukim.finki.wp.magacin.repository.ManufacturerRepository;
import mk.ukim.finki.wp.magacin.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {
  private final ManufacturerRepository manufacturerRepository;
  private final ItemRepository itemRepository;

  @Override
  public List<Manufacturer> listAll() {
    return this.manufacturerRepository.findAll();
  }

  @Override
  public Manufacturer findById(Long id) {
    return this.manufacturerRepository.findById(id).orElseThrow(InvalidManufacturerIdException::new);
  }

  @Override
  public Manufacturer create(String name) {
    return this.manufacturerRepository.save(new Manufacturer(name));
  }

  @Override
  public Manufacturer delete(Long id) {
    Manufacturer manufacturer = this.findById(id);
    this.itemRepository.deleteAll(manufacturer.getItems());
    this.manufacturerRepository.delete(manufacturer);
    return manufacturer;
  }
}
