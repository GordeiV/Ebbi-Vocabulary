package web.user;

import business.UserService;
import business.VocabularyService;
import business.WordService;
import dao.DaoException;
import entity.User;
import entity.Vocabulary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="profile", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(ProfileServlet.class);
    private VocabularyService vocabularyService;
    private WordService wordService;

    @Override
    public void init() throws ServletException {
        vocabularyService = new VocabularyService();
        wordService = new WordService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            logger.trace("if-loop entered");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try {
            int vocabulariesAmount = vocabularyService.getAmountOfVocabularies(user.getId());
            int wordsAmount = wordService.getAmountOfWords(user.getId());
            req.setAttribute("num_voc", vocabulariesAmount);
            req.setAttribute("num_words", wordsAmount);
            req.setAttribute("login", user.getLogin());
        } catch (DaoException e) {
            logger.error("", e);
        }

        req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
    }
}
