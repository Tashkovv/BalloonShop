package mk.finki.ukim.mk.laba.web.controllers;

import mk.finki.ukim.mk.laba.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.laba.service.UserRegisterService;
import mk.finki.ukim.mk.laba.service.UserService;
import mk.finki.ukim.mk.laba.service.impl.UserServiceImpl;
import org.openqa.selenium.InvalidArgumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserServiceImpl userService;
    private final UserRegisterService userRegisterService;

    public RegisterController(UserServiceImpl userService, UserRegisterService userRegisterService) {
        this.userService = userService;
        this.userRegisterService = userRegisterService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username, @RequestParam String password,
                           @RequestParam String repeatedPassword) {
        try{
            this.userService.register(username, password, repeatedPassword);
            return "redirect:/login";
        }catch (PasswordsDoNotMatchException | InvalidArgumentException e){
            return "redirect:/register?error"+e.getMessage();
        }
    }
}
