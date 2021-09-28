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

@WebServlet(name = "vocabularies", urlPatterns = {"/vocabularies"})
public class VocabulariesServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(VocabulariesServlet.class);
    private List<Vocabulary> vocabularies;
    private VocabularyService vocabularyService;

    @Override
    public void init() throws ServletException {
        vocabularyService = new VocabularyService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        User user = (User) req.getSession().getAttribute("user");

        logger.trace("user: {}", user);

        if (user == null) {
            logger.trace("if-loop entered");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }


        try {
            vocabularies = vocabularyService.getUsersVocabularies(user);
        } catch (DaoException e) {
            logger.error(e.getMessage());
        }
        if(vocabularies.size() == 0) {
            req.getRequestDispatcher("/WEB-INF/no-vocabularies.jsp").forward(req, resp);
        } else {
            req.setAttribute("login", user.getLogin());
            req.setAttribute("vocabularies", vocabularies);
            req.getRequestDispatcher("/WEB-INF/show-vocabularies.jsp").forward(req, resp);
        }
    }
}
