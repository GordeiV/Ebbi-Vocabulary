package business;

import dao.DaoException;
import dao.VocabularyDao;
import entity.User;
import entity.Vocabulary;
import dao.util.PoolConnectionManager;

import java.util.List;

public class VocabularyService {
    private VocabularyDao vocabularyDao;

    public VocabularyService() {
        vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new PoolConnectionManager());
    }

    public List<Vocabulary> getUsersVocabularies(User user) throws DaoException {
        return vocabularyDao.getAllVocabularies(user.getId());
    }

    public List<Vocabulary> findUsersVocabularies(String pattern, Long userId) throws DaoException {
        return vocabularyDao.findUsersVocabulary(pattern, userId);
    }

    public void saveVocabulary(Vocabulary vocabulary, User user) throws DaoException {
        vocabularyDao.saveVocabulary(vocabulary, user);
    }

    public List<Vocabulary> getVocabulariesForRepeat(User user) throws DaoException {
        return vocabularyDao.getVocabulariesForRepeat(user.getId());
    }

    public void setVocabularyAsRepeated(Long id) throws DaoException {
        vocabularyDao.setVocabularyAsRepeated(id);
    }

    public int getAmountOfVocabularies(Long userId) throws DaoException {
        return vocabularyDao.getAmountOfVocabularies(userId);
    }
}
