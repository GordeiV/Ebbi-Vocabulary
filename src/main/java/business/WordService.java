package business;

import dao.DaoException;
import dao.WordDao;
import dao.util.PoolConnectionManager;

public class WordService {
    WordDao wordDao;

    public WordService() {
        wordDao = new WordDao();
        wordDao.setConnectionManager(new PoolConnectionManager());
    }

    public int getAmountOfWords(Long userId) throws DaoException {
        return wordDao.getAmountOfWords(userId);
    }
}
