package mk.finki.ukim.mk.laba.web.controllers;

import mk.finki.ukim.mk.laba.service.BalloonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/selectBalloonSize")
public class SelectBalloonSizeController {
    private final BalloonService balloonService;

    public SelectBalloonSizeController(BalloonService balloonService) {
        this.balloonService = balloonService;
    }
    @GetMapping
    public String getBalloonSizePage(Model model, HttpServletRequest req){
        model.addAttribute("color", req.getSession().getAttribute("color"));
        model.addAttribute("bodyContent", "selectBalloonSize");
        return "master-template";
    }
    @PostMapping
    public String postBalloonSizeInfo(@RequestParam String color, HttpServletRequest request){
        request.getSession().setAttribute("color", color);
        return "redirect:/selectBalloonSize";
    }
}
