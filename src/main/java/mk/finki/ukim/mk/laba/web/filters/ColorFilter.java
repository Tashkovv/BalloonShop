package mk.finki.ukim.mk.laba.web.filters;

import mk.finki.ukim.mk.laba.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class ColorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getServletPath();
        String method = req.getMethod();
        String color = "";
        if (!method.equals("POST")) {
            color = (String) req.getSession().getAttribute("color");
        }

        if (("/BalloonOrder.do".equals(path) ||
                "/ConfirmationInfo".equals(path) ||
                "/selectBalloonSize".equals(path)) &&
                color == null ||
                path.equals("/")) {
            resp.sendRedirect("/balloons");
        } else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
