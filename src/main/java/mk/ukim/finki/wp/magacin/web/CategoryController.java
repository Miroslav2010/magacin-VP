//package mk.ukim.finki.wp.magacin.web;
//
//import lombok.RequiredArgsConstructor;
//import mk.ukim.finki.wp.magacin.service.CategoryService;
//import mk.ukim.finki.wp.magacin.service.ItemService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/categories")
//public class CategoryController {
//  private final CategoryService categoryService;
//  private final ItemService itemService;
//
//  @GetMapping
//  public String getAll(Model model) {
//    model.addAttribute("categories", categoryService.listAll());
//    model.addAttribute("bodyContent", "categories");
//    model.addAttribute("itemNames", itemService.getItemNames());
//    return "master-template";
//  }
//
//  @PostMapping("/add")
//  public String addNewCategory(@RequestParam String name) {
//    this.categoryService.create(name);
//    return "redirect:/categories";
//  }
//
//  @DeleteMapping("/delete/{id}")
//  public String deleteCategory(@PathVariable Long id) {
//    this.categoryService.delete(id);
//    return "redirect:/categories";
//  }
//
//}
