package web.user;

import business.UserService;
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

@WebServlet(name = "user-register", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegisterServlet.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
        req.removeAttribute("error");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("user");
        String password = req.getParameter("psw");
        User user = new User(userName, password);

        String repeatedPassword = req.getParameter("psw-repeat");
        if(!password.equals(repeatedPassword)) {
            req.setAttribute("error", "The passwords are not equal");
            doGet(req, resp);
            return;
        }

        if(userService.isUserExist(user.getLogin())) {
            req.setAttribute("error", "Username already exists");
            doGet(req, resp);
            return;
        }
        Long id = 0L;
        try {
            id = userService.createUser(user);
        } catch (DaoException e) {
            logger.error(e.getMessage());
        }
        user.setId(id);
        req.getSession().setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/start");
    }
}
