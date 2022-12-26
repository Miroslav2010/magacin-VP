package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Category;

import java.util.List;

/**
 * Category service.
 */
public interface CategoryService {

  /**
   * Get all existing categories.
   *
   * @return Collection of {@link Category}.
   */
  List<Category> listAll();

  /**
   * Get category by id.
   *
   * @param id the id of the category.
   * @return {@link Category}.
   */
  Category findById(Long id);

  /**
   * Create a new category.
   *
   * @param name the name of the category to be created.
   * @return {@link Category}.
   */
  Category create(String name);

  /**
   * Delete a category by its id.
   *
   * @param id the id of the category to be deleted.
   */
  void delete(Long id);
}
