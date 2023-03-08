package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
  List<Category> listAll();

  Category findById(Long id);

  void create(String name);

  void delete(Long id);
}
