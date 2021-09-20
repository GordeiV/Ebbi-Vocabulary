package dao;

import entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import dao.util.DirectConnectionManager;

public class UserDaoTest {
    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Test
    public void saveUser1() throws DaoException {
        User user = new User("Person1", "123");
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        userDao.saveUser(user);
    }

    @Test(expected = DaoException.class)
    public void saveUser2() throws DaoException {
        User user = new User("Person1", "1234");
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        userDao.saveUser(user);
    }

    @Test
    public void saveUser3() throws DaoException {
        User user = new User("Person2", "123");
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        userDao.saveUser(user);
    }

    @Test
    public void getUser1() throws DaoException {
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        User user = userDao.getUser("theo");
        Assert.assertTrue(user.getId() == 7);
    }

    @Test
    public void getUser2() throws DaoException {
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        User user = userDao.getUser("dsanköыф");
        Assert.assertTrue(user == null);
    }

    @Test
    public void getUser3() throws DaoException {
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        User user = userDao.getUser("shela");
        Assert.assertTrue(user.getId() == 6);
    }

    @Test
    public void deleteUser1() throws DaoException {
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        userDao.deleteUser(4L);
        Assert.assertTrue(userDao.getUser("sally") == null);
    }

    @Test
    public void deleteUser2() throws DaoException {
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        userDao.deleteUser("kripoti");
        Assert.assertTrue(userDao.getUser("kripoti") == null);
    }

    @Test
    public void deleteUser3() throws DaoException {
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        boolean deleted = userDao.deleteUser("kripotidsadasdasddasd");
        Assert.assertFalse(deleted);
    }

    @Test
    public void updateUser1() throws DaoException {
        User user = new User("marks2", "passForTest1", 1L);
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        userDao.updateUser(user);
        User finalUser = userDao.getUser("marks2");
        Assert.assertTrue(finalUser.getPassword().equals("passForTest1"));
    }

    @Test
    public void updateUser2() throws DaoException {
        User user = new User("saam", "passForTest2", 2L);
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        userDao.updateUser(user);
        User finalUser = userDao.getUser("saam");
        Assert.assertTrue(finalUser.getPassword().equals("passForTest2"));
    }

    @Test
    public void updateUser3() throws DaoException {
        User user = new User("john", "passForTest3", 3L);
        UserDao userDao = new UserDao();
        userDao.setConnectionManager(new DirectConnectionManager());
        userDao.updateUser(user);
        User finalUser = userDao.getUser("john");
        Assert.assertTrue(finalUser.getPassword().equals("passForTest3"));
    }
}