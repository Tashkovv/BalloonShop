package mk.finki.ukim.mk.laba.web.servlets;

import mk.finki.ukim.mk.laba.model.Balloon;
import mk.finki.ukim.mk.laba.service.BalloonService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchBalloonServlet", urlPatterns = "/search")
public class SearchBalloonServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BalloonService balloonService;

    public SearchBalloonServlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("searchText", req.getParameter("searchValue"));
        req.getSession().setAttribute("searchBy", req.getParameter("searchBy"));
        resp.sendRedirect("/search");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String text = (String) req.getSession().getAttribute("searchText");
        String searchBy = (String) req.getSession().getAttribute("searchBy");

        context.setVariable("result", balloonService.search(text, searchBy));
        context.setVariable("by", searchBy);

        springTemplateEngine.process("search.html", context, resp.getWriter());
    }
}
