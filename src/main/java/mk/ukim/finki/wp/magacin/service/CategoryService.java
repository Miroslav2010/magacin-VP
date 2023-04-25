package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface CategoryService {

  List<Category> listAll();

  Category findById(@NotNull Long id);

  void create(@NotBlank String name);

  void delete(@NotNull Long id);
}
