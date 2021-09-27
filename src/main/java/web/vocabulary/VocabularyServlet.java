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

@WebServlet(name = "vocabulary", urlPatterns = {"/vocabulary"})
public class VocabularyServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(VocabularyServlet.class);
    private VocabularyService vocabularyService;
    List<Vocabulary> vocabularies;

    @Override
    public void init() throws ServletException {
        vocabularyService = new VocabularyService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Vocabulary vocabularyToJsp = null;

        User user = (User) req.getSession().getAttribute("user");

        logger.trace("user: {}", user);

        if (user == null) {
            logger.trace("if-loop entered");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }


        try {
            String strId = req.getParameter("id");
            Long id = Long.parseLong(strId);
            vocabularies = vocabularyService.getUsersVocabularies(user);
            for(Vocabulary vocabulary : vocabularies) {
                if(vocabulary.getId().equals(id)) {
                    vocabularyToJsp = vocabulary;
                }
            }
        } catch (DaoException e) {
            logger.error("", e);
        }

        req.setAttribute("vocabulary", vocabularyToJsp);
        req.getRequestDispatcher("/WEB-INF/vocabulary.jsp").forward(req, resp);
    }
}
