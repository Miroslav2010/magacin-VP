package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Warehouse;
import mk.ukim.finki.wp.magacin.service.command.CreateWarehouseCommand;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface WarehouseService {
  List<Warehouse> listAll();
  Warehouse findById(@NotNull Long id);
  void create(@Valid CreateWarehouseCommand command);
  void delete(@NotNull Long id);
}
