package mk.ukim.finki.wp.magacin.rest.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.rest.WarehouseApi;
import mk.ukim.finki.wp.magacin.rest.mapper.WarehouseCommandMapper;
import mk.ukim.finki.wp.magacin.rest.mapper.WarehouseResourceMapper;
import mk.ukim.finki.wp.magacin.rest.model.CreateWarehouseRequestBody;
import mk.ukim.finki.wp.magacin.rest.model.WarehouseResource;
import mk.ukim.finki.wp.magacin.rest.model.WarehousesResponseBody;
import mk.ukim.finki.wp.magacin.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WarehouseController implements WarehouseApi {

  private final WarehouseService warehouseService;
  private final WarehouseResourceMapper warehouseResourceMapper;
  private final WarehouseCommandMapper warehouseCommandMapper;

  @Override
  public ResponseEntity<Void> createWarehouse(CreateWarehouseRequestBody createWarehouseRequestBody) {
    warehouseService.create(warehouseCommandMapper.toCommand(createWarehouseRequestBody));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> deleteWarehouseById(Long warehouseId) {
    warehouseService.delete(warehouseId);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<WarehouseResource> getWarehouseById(Long warehouseId) {
    return ResponseEntity.ok(warehouseResourceMapper.toResource(warehouseService.findById(warehouseId)));
  }

  @Override
  public ResponseEntity<WarehousesResponseBody> getWarehouses() {
    return ResponseEntity.ok(warehouseResourceMapper.toResource(warehouseService.listAll()));
  }
}
