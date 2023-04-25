package mk.ukim.finki.wp.magacin.rest.mapper;

import mk.ukim.finki.wp.magacin.rest.model.CreateWarehouseRequestBody;
import mk.ukim.finki.wp.magacin.service.command.CreateWarehouseCommand;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface WarehouseCommandMapper {

  CreateWarehouseCommand toCommand(CreateWarehouseRequestBody requestBody);
}
