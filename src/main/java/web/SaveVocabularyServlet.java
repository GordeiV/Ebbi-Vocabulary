package web;

import business.UserService;
import business.VocabularyService;
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

@WebServlet(name = "save vocabulary", urlPatterns = {"/save/vocabulary"})
public class SaveVocabularyServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(StartSiteServlet.class);
    private VocabularyService vocabularyService;

    @Override
    public void init() throws ServletException {
        vocabularyService = new VocabularyService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (req.getSession().getAttribute("user") == null) {
            logger.trace("if-loop entered");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Vocabulary vocabulary = (Vocabulary) req.getSession().getAttribute("vocabulary");
        req.setAttribute("words", vocabulary.getWords());
        req.getRequestDispatcher("/WEB-INF/save-vocabulary.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Vocabulary vocabulary = (Vocabulary) req.getSession().getAttribute("vocabulary");
        User user = (User) req.getSession().getAttribute("user");
        req.getSession().removeAttribute("vocabulary");
        try {
            vocabularyService.saveVocabulary(vocabulary, user);
        } catch (DaoException e) {
            logger.error(e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/start");
    }
}
