package mk.ukim.finki.wp.magacin.rest.mapper;

import mk.ukim.finki.wp.magacin.models.Category;
import mk.ukim.finki.wp.magacin.rest.model.CategoriesResponseBody;
import mk.ukim.finki.wp.magacin.rest.model.CategoryResource;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface ResourceMapper {

  default CategoriesResponseBody toResource(List<Category> categories) {
    return new CategoriesResponseBody()
      .categories(toResourceList(categories));
  }

  List<CategoryResource> toResourceList(List<Category> categories);
}
