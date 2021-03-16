package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.models.User;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.magacin.models.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.magacin.models.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.magacin.service.ItemService;
import mk.ukim.finki.wp.magacin.service.ShoppingCartService;
import mk.ukim.finki.wp.magacin.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class UsersController {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final ItemService itemService;

    public UsersController(UserService userService, ShoppingCartService shoppingCartService, ItemService itemService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.itemService = itemService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try{
            user = this.userService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        }
        catch (InvalidUsernameOrPasswordException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }

    @GetMapping("/register")
    public String getRegisterPage(@RequestParam(required = false) String error , Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String role) {
        try{
            this.userService.register(username, password, repeatedPassword, role);
            return "redirect:/login";
        } catch (PasswordsDoNotMatchException | InvalidUsernameOrPasswordException  | UsernameAlreadyExistsException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/userdetails")
    public String getUserDetails(HttpServletRequest req, Model model){
        model.addAttribute("user", this.userService.getUser(req.getRemoteUser()));
        model.addAttribute("items",this.itemService.listAll());
        model.addAttribute("bodyContent", "userdetails");
        return "master-template";
    }
    @PostMapping("/userdetails")
    public String changeUserDetails(@RequestParam String username,
                                    @RequestParam String password,
                                    @RequestParam(required = false) String firstName,
                                    @RequestParam(required = false) String lastName,
                                    @RequestParam(required = false) String address,
                                    @RequestParam(required = false) String email,
                                    @RequestParam(required = false) String city,
                                    @RequestParam(required = false) String country,
                                    @RequestParam(required = false) String zipcode){
        this.userService.updateUser(username,password,firstName,lastName,address,email,city,country,zipcode);
        return "redirect:/userdetails";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users", this.userService.getAllUsers());
        model.addAttribute("bodyContent", "userlist");
        model.addAttribute("items",this.itemService.listAll());
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users/edit/{username}")
    public String editUser(@PathVariable String username, Model model){
        model.addAttribute("user", this.userService.findUserById(username));
        model.addAttribute("items",this.itemService.listAll());
        model.addAttribute("bodyContent", "useredit");
        return "master-template";
    }
    @PostMapping("/users/edit/{username}")
    public String submitEditUser(@PathVariable String username, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String address, @RequestParam String email, @RequestParam String city, @RequestParam String country, @RequestParam  String zipcode){
        this.userService.adminUserUpdate(username,firstName,lastName,address,email,city,country,zipcode);
        return "redirect:/users";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/users/delete/{username}")
    public String deleteOrder(@PathVariable String username){
        this.userService.deleteUser(username);
        return "redirect:/users";
    }
    @PostMapping("/userdetails/changepassword/{username}")
    public String changePass(@PathVariable String username, @RequestParam String oldpass, @RequestParam String newpass, @RequestParam String confirmnewpass){
        if(newpass.equals(confirmnewpass)){
            this.userService.changePassword(username,oldpass,newpass);
        }
        return "redirect:/userdetails";
    }
    @PostMapping("/userdetails/deactivate/{username}")
    public String changePass(@PathVariable String username, @RequestParam String deactpass, @RequestParam String deactconfirmpass){
        if(deactpass.equals(deactconfirmpass)){
            if(userService.checkPassword(username,deactpass)) {
                if(this.userService.findUserById(username).getShoppingCart() != null)
                    this.shoppingCartService.deleteShoppingCart(this.userService.findUserById(username).getShoppingCart().getId());
                this.userService.deleteUser(username);
                return "redirect:/logout";
            }
        }
        return "redirect:/userdetails";
    }

}
