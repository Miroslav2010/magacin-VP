package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ItemsController {
    private final ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String getProducts(Model model){
        model.addAttribute("bodyContent","products");
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
}
