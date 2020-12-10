package mk.finki.ukim.mk.laba.web.controllers;

import mk.finki.ukim.mk.laba.model.User;
import mk.finki.ukim.mk.laba.model.enums.TYPE;
import mk.finki.ukim.mk.laba.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.laba.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage(){
        return "login";
    }
    @PostMapping
    public String login(HttpServletRequest req, Model model) {
        User user = null;
        try {
            user = this.userService.login(req.getParameter("username"), req.getParameter("password"));
            req.getSession().setAttribute("user", user);
            return "redirect:/balloons";
        } catch (InvalidUserCredentialsException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error",e.getMessage());
            return "login";
        }
    }
}
