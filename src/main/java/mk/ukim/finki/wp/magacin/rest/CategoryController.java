package mk.ukim.finki.wp.magacin.rest;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.rest.mapper.ResourceMapper;
import mk.ukim.finki.wp.magacin.rest.model.CategoriesResponseBody;
import mk.ukim.finki.wp.magacin.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryApi {

  private final CategoryService categoryService;
  private final ResourceMapper resourceMapper;

  @Override
  public ResponseEntity<CategoriesResponseBody> getCategories() {
    return ResponseEntity.ok(resourceMapper.toResource(categoryService.listAll()));
  }
}
