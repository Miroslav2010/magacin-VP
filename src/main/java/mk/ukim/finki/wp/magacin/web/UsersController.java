package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.models.User;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.magacin.models.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.magacin.models.exceptions.UsernameAlreadyExistsException;
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

    public UsersController(UserService userService) {
        this.userService = userService;
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
        model.addAttribute("bodyContent", "userdetails");
        return "master-template";
    }
    @PostMapping("/userdetails")
    public String changeUserDetails(@RequestParam String username, @RequestParam(required = false) String password, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String address, @RequestParam String email, @RequestParam String city, @RequestParam String country, @RequestParam  String zipcode, @RequestParam String imageUrl){
        this.userService.updateUser(username,password,firstName,lastName,address,email,city,country,zipcode,imageUrl);
        return "redirect:/userdetails";
    }

}
