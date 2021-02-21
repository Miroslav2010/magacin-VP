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
    public String showHome(Model model){
        model.addAttribute("bodyContent","homepage");
        return "master-template";
    }
}
