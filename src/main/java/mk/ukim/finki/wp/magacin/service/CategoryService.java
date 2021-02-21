package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Category;
import mk.ukim.finki.wp.magacin.models.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> listAll();
    Category findbyId(Long id);
    Category create(String name);
    Category update(Long id, String name);
    Category delete(Long id);
}
