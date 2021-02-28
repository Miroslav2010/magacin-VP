package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.Category;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.magacin.repository.CategoryRepository;
import mk.ukim.finki.wp.magacin.repository.ItemRepository;
import mk.ukim.finki.wp.magacin.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Category> listAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findbyId(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(InvalidCategoryIdException::new);
    }

    @Override
    public Category create(String name) {
        return this.categoryRepository.save(new Category(name));
    }

    @Override
    public Category update(Long id, String name) {
        Category category = this.findbyId(id);
        category.setName(name);
        return this.categoryRepository.save(category);
    }

    @Override
    public Category delete(Long id) {
        Category category = this.findbyId(id);
        for (Item item : category.getItems()) {
            this.itemRepository.delete(item);
        }
        this.categoryRepository.delete(category);
        return category;
    }
}
