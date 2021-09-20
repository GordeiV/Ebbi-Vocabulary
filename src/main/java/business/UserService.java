package business;

import business.exceptions.NoUserFound;
import business.exceptions.WrongPassword;
import dao.DaoException;
import dao.UserDao;
import entity.User;
import dao.util.PoolConnectionManager;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
        userDao.setConnectionManager(new PoolConnectionManager());
    }

    public User logInUser(User user) throws NoUserFound, DaoException, WrongPassword {
        User checkedUser = userDao.getUser(user.getLogin());
        if(checkedUser == null) {
            String message = "There is no user with - Login: " + user.getLogin() + "| Password: " + user.getPassword();
            throw new NoUserFound(message);
        }
        if(!checkedUser.getPassword().equals(user.getPassword())) {
            String message = "The password is incorrect: " + user.getPassword()
                    + ". The correct password: " + checkedUser.getPassword();
            throw new WrongPassword(message);
        }
        return checkedUser;
    }

    public boolean isUserExist(String login) {
        try {
            User u = userDao.getUser(login);

            if (u == null) {
                return false;
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return true;
    }

    public Long createUser(User user) throws DaoException {
        return userDao.saveUser(user);
    }
}
