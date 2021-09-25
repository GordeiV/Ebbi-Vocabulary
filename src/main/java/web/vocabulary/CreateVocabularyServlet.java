package web.vocabulary;

import entity.Vocabulary;
import entity.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "new vocabulary", urlPatterns = {"/create/vocabulary"})
public class CreateVocabularyServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CreateVocabularyServlet.class);
    private Vocabulary vocabulary;

    @Override
    public void init() throws ServletException {
        vocabulary = new Vocabulary();
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

        req.getSession().setAttribute("vocabulary", vocabulary);
        req.getRequestDispatcher("/WEB-INF/create-vocabulary-name.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (vocabulary.getName() == null) {
            String name = req.getParameter("name");
            vocabulary.setName(name);
            req.getRequestDispatcher("/WEB-INF/create-vocabulary-word.jsp").forward(req, resp);
            return;
        }

        String foreignWord = req.getParameter("word");
        String transcription = req.getParameter("transcription");
        String translation = req.getParameter("translation");
        if (foreignWord != null && translation != null) {
            Word word = new Word(foreignWord, transcription, translation);
            vocabulary.addWord(word);
        } else {
            req.setAttribute("error", "There must be a word and its translation");
        }
        req.getRequestDispatcher("/WEB-INF/create-vocabulary-word.jsp").forward(req, resp);
    }
}
