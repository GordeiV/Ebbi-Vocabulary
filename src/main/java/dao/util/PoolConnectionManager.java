package dao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionManager implements ConnectionManager{
    private DataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(PoolConnectionManager.class);


    public PoolConnectionManager() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/wordsapp");
        } catch (NamingException ex) {
            logger.error("", ex);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
