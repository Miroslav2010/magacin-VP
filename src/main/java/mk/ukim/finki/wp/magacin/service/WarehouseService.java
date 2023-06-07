package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Warehouse;
import mk.ukim.finki.wp.magacin.service.command.CreateWarehouseCommand;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface WarehouseService {
  List<Warehouse> listAllWarehouses();
  Warehouse findWarehouseById(@NotNull Long id);
  void createWarehouse(@Valid @NotNull CreateWarehouseCommand command);
  void deleteWarehouse(@NotNull Long id);
}
