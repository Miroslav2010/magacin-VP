package mk.ukim.finki.wp.magacin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String showHome(){
        return "homepage";
    }
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
