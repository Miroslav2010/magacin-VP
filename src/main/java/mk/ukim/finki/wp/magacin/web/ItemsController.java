package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.service.CategoryService;
import mk.ukim.finki.wp.magacin.service.ItemService;
import mk.ukim.finki.wp.magacin.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemsController {
    private final ItemService itemService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ItemsController(ItemService itemService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getProducts(Model model){
        model.addAttribute("bodyContent","items");
        model.addAttribute("items", itemService.listAll());
        return "master-template";
    }
    @GetMapping("/{id}")
    public String getItemInfo(@PathVariable Long id, Model model){
        Item item = this.itemService.findbyId(id);
        model.addAttribute("bodyContent","itemInfo");
        model.addAttribute("item",item);
        return "master-template";
    }
    @GetMapping("/add")
    public String addNewItemPage(Model model){
        model.addAttribute("bodyContent","add-item");
        model.addAttribute("categories",this.categoryService.listAll());
        model.addAttribute("manufacturers",this.manufacturerService.listAll());
        return "master-template";
    }
    @GetMapping("/edit/{id}")
    public String editNewItemPage(@PathVariable Long id, Model model){
        model.addAttribute("bodyContent","add-item");
        model.addAttribute("item",this.itemService.findbyId(id));
        model.addAttribute("categories",this.categoryService.listAll());
        model.addAttribute("manufacturers",this.manufacturerService.listAll());
        return "master-template";
    }
    @PostMapping("/add")
    public String addNewItem(@RequestParam String name, @RequestParam Double price,
                             @RequestParam String description, @RequestParam String imageUrl,
                             @RequestParam Long category, @RequestParam Long manufacturer){
        this.itemService.create(name,description,imageUrl,true,price,category,manufacturer);
        return "redirect:/items";
    }
}
