package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Warehouse;

import java.util.List;


public interface WarehouseService {
    List<Warehouse> listAll();
    Warehouse findById(Long id);
    Warehouse create(String name,String location,Double lon,Double lan);
    Warehouse update(Long id,String name,String location,Double lon,Double lat);
    Warehouse delete(Long id);
}
