package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.ShoppingCart;
import mk.ukim.finki.wp.magacin.service.ItemService;
import mk.ukim.finki.wp.magacin.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ItemService itemService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ItemService itemService) {
        this.shoppingCartService = shoppingCartService;
        this.itemService = itemService;
    }
    @GetMapping
    public String showShoppingCarts(HttpServletRequest req, Model model){
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getShoppingCart(username);
        model.addAttribute("products", this.shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));
        model.addAttribute("cart",shoppingCart);
        model.addAttribute("bodyContent", "shopping-cart");
        return "master-template";
    }
    @PostMapping("/add-product/{id}")
    public String addToShoppingCart(@PathVariable Long id, HttpServletRequest req){
        try {
            String username = req.getRemoteUser();
            this.shoppingCartService.getShoppingCart(username);
            this.shoppingCartService.addProductToShoppingCart(username, id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }
    @DeleteMapping("/deleteitem/{id}/{itemid}")
    public String deleteItem(@PathVariable Long id, @PathVariable Long itemid){
        Item item = this.itemService.findbyId(itemid);
        this.shoppingCartService.deleteItem(item,id);
        return "redirect:/shopping-cart";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCart(@PathVariable Long id) {
        this.shoppingCartService.deleteAllItems(id);
        return "redirect:/shopping-cart";
    }
}
