package mk.ukim.finki.wp.magacin.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.models.Warehouse;
import mk.ukim.finki.wp.magacin.repository.WarehouseItemRepository;
import mk.ukim.finki.wp.magacin.repository.WarehouseRepository;
import mk.ukim.finki.wp.magacin.service.WarehouseService;
import mk.ukim.finki.wp.magacin.service.command.CreateWarehouseCommand;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

  private final WarehouseRepository warehouseRepository;
  private final WarehouseItemRepository warehouseItemRepository;

  @Override
  public List<Warehouse> listAllWarehouses() {
    return this.warehouseRepository.findAll();
  }

  @Override
  public Warehouse findWarehouseById(Long id) {
    return this.warehouseRepository.findById(id).orElseThrow();
  }

  @Override
  public void createWarehouse(CreateWarehouseCommand command) {
    this.warehouseRepository.save(Warehouse.builder()
        .name(command.getName())
        .lat(command.getLatitude())
        .lon(command.getLongitude())
      .build());
  }

  @Override
  public void deleteWarehouse(Long id) {
    Warehouse warehouse = this.warehouseRepository.findById(id).orElseThrow();

    // delete all item in warehouse entries before deleting the warehouse itself
    this.warehouseItemRepository.deleteAll(warehouse.getWarehouseItems());
    this.warehouseRepository.deleteById(id);
  }
}
