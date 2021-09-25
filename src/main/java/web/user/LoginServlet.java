package web.user;

import business.UserService;
import business.exceptions.NoUserFound;
import business.exceptions.WrongPassword;
import dao.DaoException;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Log in", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(StartSiteServlet.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("here");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/WEB-INF/authorization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = userService.logInUser(new User(login, password));
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/start");
        } catch (NoUserFound noUserFound) {
            req.setAttribute("error", "Unknown user, please try again");
            req.getRequestDispatcher("/WEB-INF/authorization.jsp").forward(req, resp);
        } catch (WrongPassword wrongPassword) {
            req.setAttribute("error", "Password or username is incorrect");
            req.getRequestDispatcher("/WEB-INF/authorization.jsp").forward(req, resp);
        } catch (DaoException e) {
            logger.error(e.getMessage());
        }
    }
}
