package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.DisplayWarehouse;
import mk.ukim.finki.wp.magacin.models.Warehouse;

import java.util.List;


public interface WarehouseService {
    List<Warehouse> listAll();
    List<DisplayWarehouse> listAllForDisplay();
    Warehouse findById(Long id);
    Warehouse create(String name,Double lon,Double lat);
    Warehouse update(Long id,String name,Double lon,Double lat);
    Warehouse delete(Long id);
}
