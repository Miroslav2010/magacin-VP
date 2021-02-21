package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.Warehouse;
import mk.ukim.finki.wp.magacin.repository.WarehouseRepository;
import mk.ukim.finki.wp.magacin.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<Warehouse> listAll() {
        return this.warehouseRepository.findAll();
    }

    @Override
    public Warehouse findById(Long id) {
        return this.warehouseRepository.findById(id).get();
    }

    @Override
    public Warehouse create(String name, String location, Double lon, Double lan) {
        return this.warehouseRepository.save(new Warehouse(name,location,lon,lan));
    }

    @Override
    public Warehouse update(Long id, String name, String location, Double lon, Double lat) {
        Warehouse warehouse = this.warehouseRepository.findById(id).get();
        warehouse.setName(name);
        warehouse.setLocation(location);
        warehouse.setLon(lon);
        warehouse.setLat(lat);
        return this.warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse delete(Long id) {
        Warehouse warehouse = this.warehouseRepository.findById(id).get();
        this.warehouseRepository.delete(warehouse);
        return warehouse;
    }
}
