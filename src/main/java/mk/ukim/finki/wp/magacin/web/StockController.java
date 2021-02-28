package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.service.EachItemService;
import mk.ukim.finki.wp.magacin.service.ItemService;
import mk.ukim.finki.wp.magacin.service.WarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/stock")
public class StockController {
    private final ItemService itemService;
    private final WarehouseService warehouseService;
    private final EachItemService eachItemService;

    public StockController(ItemService itemService, WarehouseService warehouseService, EachItemService eachItemService) {
        this.itemService = itemService;
        this.warehouseService = warehouseService;
        this.eachItemService = eachItemService;
    }

    @GetMapping
    public String getAddNewStock(Model model){
        model.addAttribute("items",this.itemService.listAll());
        model.addAttribute("bodyContent","stock");
        model.addAttribute("warehouses",this.warehouseService.listAll());
        model.addAttribute("itemNames", this.itemService.getItemNames());
        return "master-template";
    }
    @PostMapping
    public String addNewStock(@RequestParam Integer quantity,
                              @RequestParam Long warehouse,
                              @RequestParam Long item){
        this.eachItemService.addItems(quantity,this.warehouseService.findById(warehouse), this.itemService.findbyId(item));
        return "redirect:/";
    }
}
