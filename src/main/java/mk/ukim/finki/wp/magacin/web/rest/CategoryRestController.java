package mk.ukim.finki.wp.magacin.web.rest;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.service.CategoryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryRestController {
  private final CategoryService categoryService;
}
