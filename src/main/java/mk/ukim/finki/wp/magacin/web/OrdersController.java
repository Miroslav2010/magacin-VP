package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.ShoppingCart;
import mk.ukim.finki.wp.magacin.service.ItemService;
import mk.ukim.finki.wp.magacin.service.OrderService;
import mk.ukim.finki.wp.magacin.service.ShoppingCartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;
    private final ItemService itemService;

    public OrdersController(ShoppingCartService shoppingCartService, OrderService orderService, ItemService itemService) {
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.itemService = itemService;
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpServletRequest request){
        ShoppingCart cart = this.shoppingCartService.getShoppingCart(request.getRemoteUser());
        List<Item> items = cart.getItems();
        Double totalPrice = 0.0;
        for (Item item :
                items) {
            totalPrice+=item.getPrice();
        }
        model.addAttribute("items",items);
        model.addAttribute("bodyContent","checkout");
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("itemNames", this.itemService.getItemNames());
        return "master-template";
    }
    @PostMapping("/checkout")
    public String placeOrder(@RequestParam String firstName, @RequestParam String lastName,
                               @RequestParam String address, @RequestParam String email,
                               @RequestParam String country, @RequestParam String city,
                               @RequestParam String zipCode, HttpServletRequest request){
        ShoppingCart cart = this.shoppingCartService.getShoppingCart(request.getRemoteUser());
        List<Item> items = cart.getItems();
        List<Long> itemsList = new ArrayList<>();
        for (Item item: items) {
            itemsList.add(item.getId());
        }
        this.shoppingCartService.deleteAllItems(cart.getId());
        this.orderService.placeOrder(firstName,lastName,email,address,country,city,zipCode,itemsList,request.getRemoteUser());
        return "redirect:/";
    }
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showOrders(Model model){
        model.addAttribute("orders", this.orderService.listAll());
        model.addAttribute("bodyContent", "orders");
        return "master-template";
    }
}
