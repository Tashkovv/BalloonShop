package mk.finki.ukim.mk.laba.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/balloonOrder")
public class BalloonOrderController {
    @PostMapping
    public String postBalloonSize(@RequestParam String size, HttpServletRequest req){
        req.getSession().setAttribute("size", size);
        return "redirect:/balloonOrder";
    }

    @GetMapping
    public String getBalloonSize(Model model, HttpServletRequest req){
        model.addAttribute("size", req.getSession().getAttribute("size"));
        model.addAttribute("color", req.getSession().getAttribute("color"));
        model.addAttribute("bodyContent", "deliveryInfo");

        return "master-template";

    }
}
