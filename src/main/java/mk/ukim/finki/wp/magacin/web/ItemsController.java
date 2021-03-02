package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.service.CategoryService;
import mk.ukim.finki.wp.magacin.service.ItemService;
import mk.ukim.finki.wp.magacin.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public String getProducts(@RequestParam(required = false) String search, @RequestParam(required = false) Long category,
                              @RequestParam(required = false) Long manufacturer,
                              @RequestParam(required = false) Boolean availability,
                              Model model){
        if(search != null && !search.isEmpty()){
            model.addAttribute("items",this.itemService.searchItemsByName(search));
        }else{
            model.addAttribute("items",this.itemService.getItemsByCategoryManufacturerAndAvailability(category,manufacturer,availability));
        }
        model.addAttribute("bodyContent","items");
        model.addAttribute("categories",this.categoryService.listAll());
        model.addAttribute("manufacturers",this.manufacturerService.listAll());
        model.addAttribute("itemNames", this.itemService.getItemNames());
        return "master-template";
    }
    @GetMapping("/{id}")
    public String getItemInfo(@PathVariable Long id, Model model){
        Item item = this.itemService.findbyId(id);
        model.addAttribute("bodyContent","itemInfo");
        model.addAttribute("item",item);
        model.addAttribute("itemNames", this.itemService.getItemNames());
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add")
    public String addNewItemPage(Model model){
        model.addAttribute("bodyContent","add-item");
        model.addAttribute("categories",this.categoryService.listAll());
        model.addAttribute("manufacturers",this.manufacturerService.listAll());
        model.addAttribute("itemNames", this.itemService.getItemNames());
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit/{id}")
    public String editNewItemPage(@PathVariable Long id, Model model){
        model.addAttribute("bodyContent","add-item");
        model.addAttribute("item",this.itemService.findbyId(id));
        model.addAttribute("categories",this.categoryService.listAll());
        model.addAttribute("manufacturers",this.manufacturerService.listAll());
        model.addAttribute("itemNames", this.itemService.getItemNames());
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public String saveItem(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam String description,
            @RequestParam String imageUrl,
            @RequestParam(required = false) Boolean availability,
            @RequestParam Long category,
            @RequestParam Long manufacturer) {
        if (id != null) {
            this.itemService.update(id, name , description,imageUrl,availability,price, category, manufacturer);
        } else {
            this.itemService.create(name,description,imageUrl,false, price, category, manufacturer);
        }
        return "redirect:/items";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id){
        this.itemService.delete(id);
        return "redirect:/items";
    }
}
