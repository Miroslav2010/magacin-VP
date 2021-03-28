package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.models.*;
import mk.ukim.finki.wp.magacin.models.enumerations.OrderStatus;
import mk.ukim.finki.wp.magacin.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;
    private final ItemService itemService;
    private final UserService userService;
    private final ReportService reportService;

    public OrdersController(ShoppingCartService shoppingCartService, OrderService orderService, ItemService itemService, UserService userService, ReportService reportService) {
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.itemService = itemService;
        this.userService = userService;
        this.reportService = reportService;
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpServletRequest request){
        User user = this.userService.getUser(request.getRemoteUser());
        ShoppingCart cart = this.shoppingCartService.getShoppingCart(request.getRemoteUser());
        List<ShoppingCartItem> items = cart.getShoppingCartItems();
        Double totalPrice = 0.0;
        for (ShoppingCartItem item :
                items) {
            totalPrice+=item.getQuantity()*item.getItem().getPrice();
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
                               @RequestParam String zipCode, @RequestParam Double totalPrice,
                               HttpServletRequest request){
        ShoppingCart cart = this.shoppingCartService.getShoppingCart(request.getRemoteUser());
        List<ShoppingCartItem> items = cart.getShoppingCartItems();
        List<Long> itemsList = new ArrayList<>();
        for (ShoppingCartItem item: items) {
            itemsList.add(item.getId());
        }
        this.shoppingCartService.deleteAllItems(cart.getId());
        this.orderService.placeOrder(firstName,lastName,email,address,country,city,zipCode,totalPrice,itemsList,request.getRemoteUser());
        for (ShoppingCartItem item: items){
            if(item.getItem().getTotalQuantity() <= 5)
                this.reportService.generateReport(item.getItem().getName());
        }
        return "redirect:/";
    }
    @GetMapping("/getorderspdf")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void getOrdersPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=orders_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Order> orders = orderService.listAll();

        OrdersPDFExporter exporter = new OrdersPDFExporter(orders);
        exporter.export(response);
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
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/myorders")
    public String showMyOrders(Model model, HttpServletRequest request){
        model.addAttribute("orders", this.userService.listAllUserOrders(this.userService.getUser(request.getRemoteUser()).getUsername()));
        model.addAttribute("bodyContent", "myorders");
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/myorders/{id}")
    public String showSpecificOrder(@PathVariable Long id, Model model){
        model.addAttribute("order", this.orderService.findById(id));
        model.addAttribute("pending", OrderStatus.PENDING);
        model.addAttribute("bodyContent", "userorder");
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/myorders/{id}")
    public String userOrderDetailsChange(@PathVariable Long id, @RequestParam(required = false) String username, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String address, @RequestParam String email, @RequestParam String city, @RequestParam String country, @RequestParam  String zipcode, @RequestParam(required = false) OrderStatus status){
        this.orderService.updateOrder(id, firstName, lastName, address, email, city, country, zipcode, OrderStatus.PENDING);
        return "redirect:/orders/myorders";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/myorders/{id}/cancel")
    public String cancelOrder(@PathVariable Long id){
        this.orderService.cancelOrder(id);
        return "redirect:/orders/myorders";
    }

}
