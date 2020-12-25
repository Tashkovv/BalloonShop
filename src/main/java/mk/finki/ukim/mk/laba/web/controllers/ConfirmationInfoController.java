package mk.finki.ukim.mk.laba.web.controllers;

import mk.finki.ukim.mk.laba.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/ConfirmationInfo")
public class ConfirmationInfoController {
    private final OrderService orderService;

    public ConfirmationInfoController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public String getConfirmationInfo(HttpServletRequest req, Model model){
        String clientsBrowser = req.getHeader("User-Agent");
        String clientName = (String) req.getSession().getAttribute("clientName");
        String clientAddress = (String) req.getSession().getAttribute("clientAddress");
        String clientsColor = (String) req.getSession().getAttribute("color");
        String clientsSize = (String) req.getSession().getAttribute("size");
        String ipAddress = req.getRemoteAddr();

        model.addAttribute("clientsBrowser", clientsBrowser);
        model.addAttribute("clientName", clientName);
        model.addAttribute("clientAddress", clientAddress);
        model.addAttribute("clientsColor", clientsColor);
        model.addAttribute("clientsSize", clientsSize);
        model.addAttribute("clientsIpAddress", ipAddress);
        model.addAttribute("bodyContent", "confirmationInfo");

        return "master-template";
    }
    @PostMapping
    public String postInfo(@RequestParam String clientName, @RequestParam String clientAddress, HttpServletRequest req){
        String client_name = clientName;
        String client_address = clientAddress;

        req.getSession().setAttribute("clientName", clientName);
        req.getSession().setAttribute("clientAddress", clientAddress);

        String clientColor = (String) req.getSession().getAttribute("color");
        String clientSize = (String) req.getSession().getAttribute("size");
        String user = req.getRemoteUser();
        orderService.placeOrder(clientColor, clientSize, user);

        return "redirect:/ConfirmationInfo";

    }
}
