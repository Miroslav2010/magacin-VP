package mk.ukim.finki.wp.magacin.rest.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.rest.ManufacturerApi;
import mk.ukim.finki.wp.magacin.rest.mapper.ManufacturerResourceMapper;
import mk.ukim.finki.wp.magacin.rest.model.CreateManufacturerRequestBody;
import mk.ukim.finki.wp.magacin.rest.model.ManufacturerResource;
import mk.ukim.finki.wp.magacin.rest.model.ManufacturerResponseBody;
import mk.ukim.finki.wp.magacin.service.ManufacturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManufacturerController implements ManufacturerApi {

  private final ManufacturerService manufacturerService;
  private final ManufacturerResourceMapper manufacturerResourceMapper;

  @Override
  public ResponseEntity<Void> createManufacturer(CreateManufacturerRequestBody createManufacturerRequestBody) {
    manufacturerService.create(createManufacturerRequestBody.getName());
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> deleteManufacturerById(Long manufacturerId) {
    manufacturerService.delete(manufacturerId);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<ManufacturerResource> getManufacturerById(Long manufacturerId) {
    return ResponseEntity.ok(manufacturerResourceMapper.toResource(manufacturerService.findById(manufacturerId)));
  }

  @Override
  public ResponseEntity<ManufacturerResponseBody> getManufacturers() {
    return ResponseEntity.ok(manufacturerResourceMapper.toResource(manufacturerService.listAll()));
  }
}
