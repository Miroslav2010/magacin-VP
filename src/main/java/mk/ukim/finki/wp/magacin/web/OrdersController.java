package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.ShoppingCart;
import mk.ukim.finki.wp.magacin.models.User;
import mk.ukim.finki.wp.magacin.models.enumerations.OrderStatus;
import mk.ukim.finki.wp.magacin.service.ItemService;
import mk.ukim.finki.wp.magacin.service.OrderService;
import mk.ukim.finki.wp.magacin.service.ShoppingCartService;
import mk.ukim.finki.wp.magacin.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;
    private final ItemService itemService;
    private final UserService userService;

    public OrdersController(ShoppingCartService shoppingCartService, OrderService orderService, ItemService itemService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpServletRequest request){
        User user = this.userService.getUser(request.getRemoteUser());
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
        model.addAttribute("user",user);
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
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editOrder(@PathVariable Long id, Model model){
        model.addAttribute("order", this.orderService.findById(id));
        model.addAttribute("orderStatuses", OrderStatus.values());
        model.addAttribute("bodyContent", "orderedit");
        return "master-template";
    }
    @PostMapping("/edit/{id}")
    public String changeOrderDetails(@PathVariable Long id, @RequestParam(required = false) String username, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String address, @RequestParam String email, @RequestParam String city, @RequestParam String country, @RequestParam  String zipcode, @RequestParam OrderStatus status){
        this.orderService.updateOrder(id, firstName, lastName, address, email, city, country, zipcode, status);
        return "redirect:/orders";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id){
        this.orderService.delete(id);
        return "redirect:/orders";
    }
}
