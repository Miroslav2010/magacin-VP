package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    private final ItemService itemService;

    public MainController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String showHome(){
        return "homepage";
    }
    @GetMapping("/test")
    public String test(Model model){
        List<Item> items = this.itemService.listAll();
        model.addAttribute("items", items);
        return "test";
    }
}
