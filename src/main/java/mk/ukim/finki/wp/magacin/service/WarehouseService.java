package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Warehouse;

import java.util.List;


public interface WarehouseService {
  List<Warehouse> listAll();
  Warehouse findById(Long id);
  void create(String name, Double lon, Double lat);
  void delete(Long id);
}
