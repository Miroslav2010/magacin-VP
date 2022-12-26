package mk.ukim.finki.wp.magacin.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.models.Category;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.magacin.repository.CategoryRepository;
import mk.ukim.finki.wp.magacin.repository.ItemRepository;
import mk.ukim.finki.wp.magacin.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @Override
    public List<Category> listAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(InvalidCategoryIdException::new);
    }

    @Override
    public Category create(String name) {
        return this.categoryRepository.save(new Category(name));
    }

    @Override
    public void delete(Long id) {
        Category category = this.findById(id);
        this.itemRepository.deleteAll(category.getItems());
        this.categoryRepository.delete(category);
    }
}
