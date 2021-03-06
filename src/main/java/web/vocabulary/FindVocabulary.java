package web.vocabulary;

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
import java.util.List;

@WebServlet(name = "findVocabulary", urlPatterns = {"/find"})
public class FindVocabulary extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(FindVocabulary.class);
    private VocabularyService vocabularyService;

    @Override
    public void init() throws ServletException {
        vocabularyService = new VocabularyService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath()+ "/start");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

//        logger.info()

        User user = (User) req.getSession().getAttribute("user");

        logger.trace("user: {}", user);

        if (user == null) {
            logger.trace("if-loop entered");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }


        req.removeAttribute("message");
        req.removeAttribute("vocabularies");


        String pattern = req.getParameter("vocabulary-name");
        logger.info("pattern: {}", pattern);
        List<Vocabulary> vocabularies = null;
        try {
            vocabularies = vocabularyService.findUsersVocabularies(pattern, user.getId());
            logger.info("Size: {}", vocabularies.size());
        } catch (DaoException e) {
            logger.error("", e);
        }


        if(vocabularies != null && vocabularies.size() == 0) {
            logger.info("No size branch");
            req.setAttribute("message", "No vocabularies found");
        } else {
            logger.info("Vocabularies found branch");
            req.setAttribute("vocabularies", vocabularies);
        }
        req.getRequestDispatcher("/WEB-INF/start.jsp").forward(req, resp);

    }
}
