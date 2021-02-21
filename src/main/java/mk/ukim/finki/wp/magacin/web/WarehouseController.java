package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.service.WarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "master-template";
    }
}
