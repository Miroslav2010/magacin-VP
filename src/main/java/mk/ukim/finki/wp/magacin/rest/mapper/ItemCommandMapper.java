package mk.ukim.finki.wp.magacin.rest.mapper;

import mk.ukim.finki.wp.magacin.rest.model.CreateItemRequestBody;
import mk.ukim.finki.wp.magacin.rest.model.UpdateItemRequestBody;
import mk.ukim.finki.wp.magacin.service.command.CreateItemCommand;
import mk.ukim.finki.wp.magacin.service.command.UpdateItemCommand;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface ItemCommandMapper {

  CreateItemCommand toCommand(CreateItemRequestBody requestBody);

  UpdateItemCommand toCommand(UpdateItemRequestBody requestBody);
}
