package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("categories",this.categoryService.listAll());
        model.addAttribute("bodyContent","categories");
        return "master-template";
    }
    @PostMapping("/add")
    public String addNewCategory(@RequestParam String name){
        this.categoryService.create(name);
        return "redirect:/categories";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        this.categoryService.delete(id);
        return "redirect:/categories";
    }

}
