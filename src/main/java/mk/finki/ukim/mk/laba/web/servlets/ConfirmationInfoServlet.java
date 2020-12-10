package mk.finki.ukim.mk.laba.web.servlets;

import mk.finki.ukim.mk.laba.model.User;
import mk.finki.ukim.mk.laba.service.BalloonService;
import mk.finki.ukim.mk.laba.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="ConfirmationInfoServlet", urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService, BalloonService balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");

        req.getSession().setAttribute("clientName", clientName);
        req.getSession().setAttribute("clientAddress", clientAddress);

        User user = (User) req.getSession().getAttribute("user");

        String clientColor = (String) req.getSession().getAttribute("color");
        String clientSize = (String) req.getSession().getAttribute("size");
        orderService.placeOrder(clientColor, clientSize, user.getUsername());

        resp.sendRedirect("/ConfirmationInfo");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientsBrowser = req.getHeader("User-Agent");
        String clientName = (String) req.getSession().getAttribute("clientName");
        String clientAddress = (String) req.getSession().getAttribute("clientAddress");
        String clientsColor = (String) req.getSession().getAttribute("color");
        String clientsSize = (String) req.getSession().getAttribute("size");
        String ipAddress = req.getRemoteAddr();

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("clientsBrowser", clientsBrowser);
        context.setVariable("clientName", clientName);
        context.setVariable("clientAddress", clientAddress);
        context.setVariable("clientsColor", clientsColor);
        context.setVariable("clientsSize", clientsSize);
        context.setVariable("clientsIpAddress", ipAddress);

        springTemplateEngine.process("confirmationInfo.html", context, resp.getWriter());
    }
}
