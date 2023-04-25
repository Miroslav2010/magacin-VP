package mk.ukim.finki.wp.magacin.rest.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.rest.CategoryApi;
import mk.ukim.finki.wp.magacin.rest.mapper.CategoryResourceMapper;
import mk.ukim.finki.wp.magacin.rest.model.CategoriesResponseBody;
import mk.ukim.finki.wp.magacin.rest.model.CategoryResource;
import mk.ukim.finki.wp.magacin.rest.model.CreateCategoryRequestBody;
import mk.ukim.finki.wp.magacin.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryApi {

  private final CategoryService categoryService;
  private final CategoryResourceMapper categoryResourceMapper;

  @Override
  public ResponseEntity<CategoriesResponseBody> getCategories() {
    return ResponseEntity.ok(categoryResourceMapper.toResource(categoryService.listAll()));
  }

  @Override
  public ResponseEntity<CategoryResource> getCategoryById(Long categoryId) {
    return ResponseEntity.ok(categoryResourceMapper.toResource(categoryService.findById(categoryId)));
  }

  @Override
  public ResponseEntity<Void> createCategory(CreateCategoryRequestBody requestBody) {
    categoryService.create(requestBody.getName());
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> deleteCategoryById(Long categoryId) {
    categoryService.delete(categoryId);
    return ResponseEntity.noContent().build();
  }
}
