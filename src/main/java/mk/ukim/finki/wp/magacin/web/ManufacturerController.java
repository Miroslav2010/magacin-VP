package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("manufacturers",this.manufacturerService.listAll());
        model.addAttribute("bodyContent","manufacturers");
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
