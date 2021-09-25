package web.vocabulary;

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
    List<Vocabulary> vocabularies;

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


        String strId = req.getParameter("id");
        Long id = Long.parseLong(strId);
        vocabularies = (List<Vocabulary>) req.getSession().getAttribute("vocabularies");
        for(Vocabulary vocabulary : vocabularies) {
            if(vocabulary.getId().equals(id)) {
                vocabularyToJsp = vocabulary;
            }
        }

        req.setAttribute("vocabulary", vocabularyToJsp);
        req.getRequestDispatcher("/WEB-INF/vocabulary.jsp").forward(req, resp);
    }
}
