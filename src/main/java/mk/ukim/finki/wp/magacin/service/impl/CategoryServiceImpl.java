package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.Category;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.magacin.repository.CategoryRepository;
import mk.ukim.finki.wp.magacin.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
        this.categoryRepository.delete(category);
        return category;
    }
}
