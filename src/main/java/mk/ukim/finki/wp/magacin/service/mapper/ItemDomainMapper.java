package mk.ukim.finki.wp.magacin.service.mapper;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.service.command.CreateItemCommand;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface ItemDomainMapper {

  Item toDomain(CreateItemCommand command);
}
