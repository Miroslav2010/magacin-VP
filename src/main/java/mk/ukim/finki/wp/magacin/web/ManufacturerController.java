package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.service.ItemService;
import mk.ukim.finki.wp.magacin.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;
    private final ItemService itemService;

    public ManufacturerController(ManufacturerService manufacturerService, ItemService itemService) {
        this.manufacturerService = manufacturerService;
        this.itemService = itemService;
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("manufacturers",this.manufacturerService.listAll());
        model.addAttribute("bodyContent","manufacturers");
        model.addAttribute("itemNames", this.itemService.getItemNames());
        return "master-template";
    }
    @PostMapping("/add")
    public String addNewManufacturer(@RequestParam String name){
        this.manufacturerService.create(name);
        return "redirect:/manufacturers";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteManufacturer(@PathVariable Long id) {
        this.manufacturerService.delete(id);
        return "redirect:/manufacturers";
    }
}
