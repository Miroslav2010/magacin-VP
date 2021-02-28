package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.DisplayWarehouse;
import mk.ukim.finki.wp.magacin.models.EachItem;
import mk.ukim.finki.wp.magacin.models.Warehouse;
import mk.ukim.finki.wp.magacin.repository.DisplayWarehouseRepository;
import mk.ukim.finki.wp.magacin.repository.EachItemRepository;
import mk.ukim.finki.wp.magacin.repository.WarehouseRepository;
import mk.ukim.finki.wp.magacin.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final DisplayWarehouseRepository displayWarehouseRepository;
    private final EachItemRepository eachItemRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, DisplayWarehouseRepository displayWarehouseRepository, EachItemRepository eachItemRepository) {
        this.warehouseRepository = warehouseRepository;
        this.displayWarehouseRepository = displayWarehouseRepository;
        this.eachItemRepository = eachItemRepository;
    }

    @Override
    public List<Warehouse> listAll() {
        return this.warehouseRepository.findAll();
    }

    @Override
    public List<DisplayWarehouse> listAllForDisplay() {
        return this.displayWarehouseRepository.findAll();
    }

    @Override
    public Warehouse findById(Long id) {
        return this.warehouseRepository.findById(id).get();
    }

    @Override
    public Warehouse create(String name, Double lon, Double lat) {
        this.displayWarehouseRepository.save(new DisplayWarehouse(name,lat,lon));
        return this.warehouseRepository.save(new Warehouse(name,lat,lon));
    }

    @Override
    public Warehouse update(Long id, String name, Double lon, Double lat) {
        Warehouse warehouse = this.warehouseRepository.findById(id).get();
        DisplayWarehouse displayWarehouse = this.displayWarehouseRepository.findById(id).get();
        warehouse.setName(name);
        displayWarehouse.setName(name);
        warehouse.setLon(lon);
        displayWarehouse.setLongitude(lon);
        warehouse.setLat(lat);
        displayWarehouse.setLatitude(lat);
        return this.warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse delete(Long id) {
        Warehouse warehouse = this.warehouseRepository.findById(id).get();
        this.displayWarehouseRepository.deleteById(id);
        for (EachItem item: warehouse.getEachItems()) {
            this.eachItemRepository.delete(item);
        }
        this.warehouseRepository.deleteById(id);
        return warehouse;
    }
}
