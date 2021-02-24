package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.service.WarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public String getWarehouses(Model model){
        model.addAttribute("bodyContent","warehouses");
        model.addAttribute("warehouses",this.warehouseService.listAll());
        model.addAttribute("displayWarehouses",this.warehouseService.listAllForDisplay());
        return "master-template";
    }
    @PostMapping("/add")
    public String addNewWarehouse(@RequestParam String name,String location,Double lon,Double lat){
        this.warehouseService.create(name,location,lon,lat);
        return "redirect:/warehouses";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteManufacturer(@PathVariable Long id) {
        this.warehouseService.delete(id);
        return "redirect:/warehouses";
    }
}
