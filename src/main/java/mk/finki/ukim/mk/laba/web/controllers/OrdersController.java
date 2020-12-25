package mk.finki.ukim.mk.laba.web.controllers;

import mk.finki.ukim.mk.laba.model.User;
import mk.finki.ukim.mk.laba.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
public class OrdersController {
    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String getOrdersPage(HttpServletRequest req, Model model) {
        String user = req.getRemoteUser();
        model.addAttribute("orders", orderService.getAllOrdersByUsername(user));
        model.addAttribute("bodyContent", "userOrders");
        return "master-template";
    }
}
