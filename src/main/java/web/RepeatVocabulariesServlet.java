package web;

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

@WebServlet(name = "vocabularies for repeat", urlPatterns = "/repeat")
public class RepeatVocabulariesServlet extends HttpServlet {
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

//        logger.info()

        User user = (User) req.getSession().getAttribute("user");

        logger.trace("user: {}", user);

        if (user == null) {
            logger.trace("if-loop entered");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }



        try {
            vocabularies = vocabularyService.getVocabulariesForRepeat(user);
        } catch (DaoException e) {
            logger.error(e.getMessage());
        }

        String id = req.getParameter("id");
        if (id != null) {
            doPut(req, resp);
        }

        if(vocabularies.size() == 0) {
            resp.getWriter().print("There are no vocabularies to repeat");
        } else {
            req.setAttribute("vocabularies", vocabularies);
            req.getRequestDispatcher("/WEB-INF/show-vocabularies-for-repeat.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long vocabularyId = Long.parseLong(req.getParameter("id"));
        logger.info(vocabularyId.toString());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long vocabularyId = Long.parseLong(req.getParameter("id"));
        logger.info("Vocabulary Id: " + vocabularyId);
        try {
            vocabularyService.setVocabularyAsRepeated(vocabularyId);
        } catch (DaoException e) {
            logger.error("", e);
        }
        vocabularies.removeIf(vocabulary -> vocabulary.getId().equals(vocabularyId));
    }
}
