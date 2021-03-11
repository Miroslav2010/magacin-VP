package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<Item> list = this.itemService.findFirstThree();
        model.addAttribute("first",list.get(0));
        list.remove(list.get(0));
        model.addAttribute("firstThree",list);
        model.addAttribute("bodyContent","homepage");
        model.addAttribute("itemNames", this.itemService.getItemNames());
        return "master-template";
    }

}
