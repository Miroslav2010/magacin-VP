//package mk.ukim.finki.wp.magacin.web;
//
//import lombok.RequiredArgsConstructor;
//import mk.ukim.finki.wp.magacin.models.Item;
//import mk.ukim.finki.wp.magacin.service.CategoryService;
//import mk.ukim.finki.wp.magacin.service.ItemService;
//import mk.ukim.finki.wp.magacin.service.ManufacturerService;
//import mk.ukim.finki.wp.magacin.service.command.CreateItemCommand;
//import mk.ukim.finki.wp.magacin.service.command.UpdateItemCommand;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/items")
//public class ItemsController {
//  private final ItemService itemService;
//  private final CategoryService categoryService;
//  private final ManufacturerService manufacturerService;
//
//  @GetMapping
//  public String getProducts(
//    @RequestParam(required = false) String search,
//    @RequestParam(required = false) Long category,
//    @RequestParam(required = false) Long manufacturer,
//    @RequestParam(required = false) Boolean availability,
//    Model model) {
//    if (search != null && !search.isEmpty()) {
//      model.addAttribute("items", this.itemService.searchItemsByName(search));
//    } else {
//      model.addAttribute("items",
//        this.itemService.getItemsByCategoryAndManufacturer(category, manufacturer, availability));
//    }
//    model.addAttribute("bodyContent", "items");
//    model.addAttribute("categories", this.categoryService.listAll());
//    model.addAttribute("manufacturers", this.manufacturerService.listAll());
//    model.addAttribute("itemNames", this.itemService.getItemNames());
//    return "master-template";
//  }
//
//  @GetMapping("/{id}")
//  public String getItemInfo(@PathVariable Long id, Model model) {
//    Item item = this.itemService.findById(id);
//    model.addAttribute("bodyContent", "item-info");
//    model.addAttribute("item", item);
//    model.addAttribute("itemNames", this.itemService.getItemNames());
//    return "master-template";
//  }
//
//  @PreAuthorize("hasRole('ROLE_ADMIN')")
//  @GetMapping("/add")
//  public String addNewItemPage(Model model) {
//    model.addAttribute("bodyContent", "add-item");
//    model.addAttribute("categories", this.categoryService.listAll());
//    model.addAttribute("manufacturers", this.manufacturerService.listAll());
//    model.addAttribute("itemNames", this.itemService.getItemNames());
//    return "master-template";
//  }
//
//  @PreAuthorize("hasRole('ROLE_ADMIN')")
//  @GetMapping("/edit/{id}")
//  public String editNewItemPage(@PathVariable Long id, Model model) {
//    model.addAttribute("bodyContent", "add-item");
//    model.addAttribute("item", this.itemService.findById(id));
//    model.addAttribute("categories", this.categoryService.listAll());
//    model.addAttribute("manufacturers", this.manufacturerService.listAll());
//    model.addAttribute("itemNames", this.itemService.getItemNames());
//    return "master-template";
//  }
//
//  @PreAuthorize("hasRole('ROLE_ADMIN')")
//  @PostMapping("/add")
//  public String saveItem(
//    @RequestParam(required = false) Long id,
//    @RequestParam String name,
//    @RequestParam BigDecimal price,
//    @RequestParam String description,
//    @RequestParam String imageUrl,
//    @RequestParam Long category,
//    @RequestParam Long manufacturer) {
//    if (id != null) {
//      this.itemService.update(UpdateItemCommand.builder()
//        .id(id)
//
//        .build());
//    } else {
//      this.itemService.create(CreateItemCommand.builder()
//        .name(name)
//        .price(price)
//        .description(description)
//        .imageUrl(imageUrl)
//        .categoryId(category)
//        .manufacturerId(manufacturer)
//        .build());
//    }
//    return "redirect:/items";
//  }
//
//  @PreAuthorize("hasRole('ROLE_ADMIN')")
//  @DeleteMapping("/delete/{id}")
//  public String deleteItem(@PathVariable Long id) {
//    this.itemService.delete(id);
//    return "redirect:/items";
//  }
//}
