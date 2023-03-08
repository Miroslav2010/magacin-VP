package mk.ukim.finki.wp.magacin.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.models.Warehouse;
import mk.ukim.finki.wp.magacin.repository.WarehouseItemRepository;
import mk.ukim.finki.wp.magacin.repository.WarehouseRepository;
import mk.ukim.finki.wp.magacin.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

  private final WarehouseRepository warehouseRepository;
  private final WarehouseItemRepository warehouseItemRepository;

  @Override
  public List<Warehouse> listAll() {
    return this.warehouseRepository.findAll();
  }

  @Override
  public Warehouse findById(Long id) {
    return this.warehouseRepository.findById(id).get();
  }

  @Override
  public void create(String name, Double lon, Double lat) {
    this.warehouseRepository.save(new Warehouse(name, lat, lon));
  }

  @Override
  public void delete(Long id) {
    Warehouse warehouse = this.warehouseRepository.findById(id).get();
    this.warehouseItemRepository.deleteAll(warehouse.getWarehouseItems());
    this.warehouseRepository.deleteById(id);
  }
}
