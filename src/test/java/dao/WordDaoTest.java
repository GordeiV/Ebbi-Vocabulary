package dao;

import entity.Word;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import dao.util.DirectConnectionManager;

public class WordDaoTest {
    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Test
    public void deleteWord1() throws DaoException {
        WordDao wordDao = new WordDao();
        wordDao.setConnectionManager(new DirectConnectionManager());
        wordDao.deleteWord(1L);
        Assert.assertNull(wordDao.getWord(1L));
    }

    @Test
    public void deleteWord2() throws DaoException {
        WordDao wordDao = new WordDao();
        wordDao.setConnectionManager(new DirectConnectionManager());
        wordDao.deleteWord(2L);
        Assert.assertNull(wordDao.getWord(2L));
    }

    @Test
    public void deleteWord3() throws DaoException {
        WordDao wordDao = new WordDao();
        wordDao.setConnectionManager(new DirectConnectionManager());
        Assert.assertFalse(wordDao.deleteWord(2L));
    }

    @Test
    public void getWord1() throws DaoException {
        WordDao wordDao = new WordDao();
        wordDao.setConnectionManager(new DirectConnectionManager());
        Assert.assertTrue(wordDao.getWord(3L).getForeignWord().equals("estate"));
    }

    @Test
    public void getWord2() throws DaoException {
        WordDao wordDao = new WordDao();
        wordDao.setConnectionManager(new DirectConnectionManager());
        Assert.assertTrue(wordDao.getWord(4L).getForeignWord().equals("apartment"));
    }

    @Test
    public void getWord3() throws DaoException {
        WordDao wordDao = new WordDao();
        wordDao.setConnectionManager(new DirectConnectionManager());
        Assert.assertTrue(wordDao.getWord(5L).getForeignWord().equals("bakery"));
    }

    @Test
    public void saveWord() throws DaoException {
        WordDao wordDao = new WordDao();
        wordDao.setConnectionManager(new DirectConnectionManager());
        Word word = new Word("testWord", "тестовоеСлово", "ttt");
        Long id = wordDao.saveWord(word, 1L);

        Word readWord = wordDao.getWord(id);
        Assert.assertTrue(readWord.getForeignWord().equals("testWord"));
        Assert.assertTrue(readWord.getNativeWord().equals("тестовоеСлово"));
        Assert.assertTrue(readWord.getTranscription().equals("ttt"));
    }

    @Test
    public void updateWord1() throws DaoException {
        WordDao wordDao = new WordDao();
        wordDao.setConnectionManager(new DirectConnectionManager());
        Word word = new Word(6L, "testWord", "тестовоеСлово", "ttt");
        wordDao.updateWord(word);

        Word readWord = wordDao.getWord(6L);
        Assert.assertTrue(readWord.getForeignWord().equals("testWord"));
        Assert.assertTrue(readWord.getNativeWord().equals("тестовоеСлово"));
        Assert.assertTrue(readWord.getTranscription().equals("ttt"));
    }

    @Test
    public void updateWord2() throws DaoException {
        WordDao wordDao = new WordDao();
        wordDao.setConnectionManager(new DirectConnectionManager());
        Word word = new Word(Long.MAX_VALUE, "testWord", "тестовоеСлово", "ttt");
        boolean b = wordDao.updateWord(word);

        Assert.assertFalse(b);
    }
}