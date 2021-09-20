package web;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "start_site", urlPatterns = {"/start"})
public class StartSiteServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(StartSiteServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (req.getSession().getAttribute("user") == null) {
            logger.trace("if-loop entered");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        logger.info("here");
        String login = req.getParameter("login");
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("login", user.getLogin());
        req.getRequestDispatcher("/WEB-INF/start.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        logger.info("here");
        doGet(req, response);
    }

}
