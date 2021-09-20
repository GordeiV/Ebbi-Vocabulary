package dao;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dao.util.ConnectionManager;

import java.sql.*;

public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    private static final String INSERT_USER = "INSERT INTO users (login, u_password) VALUES (?, ?)";
    private static final String GET_USER = "SELECT * FROM users WHERE login = ?";
    private static final String CHECK_USER = "SELECT * FROM users WHERE login = ? AND u_password = ?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id_user = ?";
    private static final String DELETE_USER_BY_LOGIN = "DELETE FROM users WHERE login = ?";
    private static final String UPDATE_USER = "UPDATE users SET login = ?, u_password = ? WHERE id_user = ?";

    private ConnectionManager connectionManager;

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    
    private Connection getConnection() throws SQLException {
        return connectionManager.getConnection();
    }

    public Long saveUser(User user) throws DaoException {
        logger.debug("Method saveUser saves User: {}", user);

        Long id = -1L;

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_USER, new String[]{"id_user"}))
        {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());

            stmt.executeUpdate();
            logger.trace("Query was successfully invoked and user was successfully saved");
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException ex) {
            logger.error("Saving of user failed: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        logger.debug("Method saveUser returned {}", id);
        return id;
    }

    /**
     * @param user
     * @return If there is no user found, 'null' will return
     * @throws DaoException
     */
    public User checkUser(User user) throws DaoException {
        try (Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(CHECK_USER)){
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            ResultSet keys = stmt.executeQuery();
            if(keys.next() == true) {
                user = new User(keys.getString("login"), keys.getString("u_password"), keys.getLong("id_user"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            logger.error("User cannot be checked: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    /**
     * @param login
     * @return If there is no user found, return null
     * @throws SQLException
     */
    public User getUser(String login) throws DaoException {
        logger.debug("Method getUser tries to get User with login: {}", login);

        User user = null;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_USER))
        {
            stmt.setString(1, login);

            ResultSet keys = stmt.executeQuery();
            logger.trace("Query was successfully invoked");
            if(keys.next() == true) {
                user = new User(keys.getString("login"), keys.getString("u_password"), keys.getLong("id_user"));
            }
        } catch (SQLException ex) {
            logger.error("Cannot get User by login: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        logger.debug("User was received: {}", user);
        return user;
    }

    public boolean deleteUser(Long id) throws DaoException {
        logger.debug("Method deleteUser tries to delete User with id: {}", id);

        boolean change = false;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_USER_BY_ID))
        {
            stmt.setLong(1, id);
            int i = stmt.executeUpdate();
            logger.trace("Query was successfully invoked");
            if(i > 0) {
                change = true;
            }
        } catch (SQLException ex) {
            logger.error("Cannot delete User by Id: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        logger.debug("Method deleteUser returned {}", change);
        return change;
    }

    public boolean deleteUser(String login) throws DaoException {
        logger.debug("Method deleteUser tries to delete User with login: {}", login);

        boolean change = false;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_USER_BY_LOGIN))
        {
            stmt.setString(1, login);
            int i = stmt.executeUpdate();
            logger.trace("Query was successfully invoked");
            if(i > 0) {
                change = true;
            }
        } catch (SQLException ex) {
            logger.error("Cannot delete User by login: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        logger.debug("Method deleteUser returned {}", change);
        return change;
    }

    public boolean updateUser(User user) throws DaoException {
        logger.debug("Method updateUser tries to update User: {}", user);

        boolean change = false;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_USER))
        {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setLong(3, user.getId());

            int i = stmt.executeUpdate();
            logger.trace("Query was successfully invoked");
            if(i > 0) {
                change = true;
            }
        } catch (SQLException ex) {
            logger.error("Cannot update User: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        logger.debug("Method updateUser returned {}", change);
        return change;
    }
}
