package dao;

import entity.User;
import entity.Vocabulary;
import entity.VocabularyStatus;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dao.util.DirectConnectionManager;

import java.time.LocalDateTime;
import java.util.List;


public class VocabularyDaoTest {
    private static final Logger logger = LoggerFactory.getLogger(VocabularyDaoTest.class);

    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }


    @Test
    public void saveVocabulary1() throws DaoException {
        logger.debug("Test");
        Vocabulary vocabulary = new Vocabulary("noway");
        User user = new User("testUser1", "testPassword", 1L);
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        Long id1 = userDao.saveUser(user);
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new DirectConnectionManager());
        Long id2 = vocabularyDao.saveVocabulary(vocabulary, user);
    }

    @Test
    public void saveVocabulary2() throws DaoException {
        Vocabulary vocabulary = new Vocabulary("number2");
        User user = new User("testUser2", "testPassword", 1L);
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        Long id1 = userDao.saveUser(user);
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new DirectConnectionManager());
        Long id2 = vocabularyDao.saveVocabulary(vocabulary, user);
    }

    @Test
    public void saveVocabulary3() throws DaoException {
        Vocabulary vocabulary = new Vocabulary("key");
        User user = new User("testUser3", "testPassword", 1L);
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        Long id1 = userDao.saveUser(user);
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new DirectConnectionManager());
        Long id2 = vocabularyDao.saveVocabulary(vocabulary, user);
    }

    @Test
    public void findVocabulary1() throws DaoException {
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new DirectConnectionManager());
        List<Vocabulary> list = vocabularyDao.findVocabulary("ay");
        Assert.assertTrue(list.size() == 3);
    }

    @Test
    public void findVocabulary2() throws DaoException {
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new DirectConnectionManager());
        List<Vocabulary> list = vocabularyDao.findVocabulary("e");
        Assert.assertTrue(list.size() == 11);
    }

    @Test
    public void findVocabulary3() throws DaoException {
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new DirectConnectionManager());
        List<Vocabulary> list = vocabularyDao.findVocabulary("nUMber");
        Assert.assertTrue(list.size() == 2);
    }

    @Test
    public void deleteVocabulary1() throws DaoException {
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new DirectConnectionManager());
        vocabularyDao.deleteVocabulary(5L);
        Assert.assertTrue(vocabularyDao.findVocabulary("ability").size() == 0);
    }

    @Test
    public void deleteVocabulary2() throws DaoException {
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new DirectConnectionManager());
        vocabularyDao.deleteVocabulary(10L);
        Assert.assertTrue(vocabularyDao.findVocabulary("money").size() == 0);
    }

    @Test
    public void deleteVocabulary3() throws DaoException {
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new DirectConnectionManager());
        vocabularyDao.deleteVocabulary(12L);
        Assert.assertTrue(vocabularyDao.findVocabulary("water").size() == 0);
    }

    @Test
    public void updateVocabulary() throws DaoException {
        Vocabulary vocabulary = new Vocabulary(
                "forTest",
                LocalDateTime.of(2002, 3, 3, 20, 0, 0),
                LocalDateTime.now(),
                VocabularyStatus.FIFTH_REPEAT,
                16L);
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new DirectConnectionManager());
        vocabularyDao.updateVocabulary(vocabulary);
        Assert.assertTrue(vocabularyDao.findVocabulary("question").size() == 0);
        Assert.assertTrue(vocabularyDao.findVocabulary("forTest").size() == 1);
    }



}