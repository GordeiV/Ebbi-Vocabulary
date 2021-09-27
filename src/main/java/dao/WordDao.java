package dao;

import entity.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dao.util.ConnectionManager;

import java.sql.*;

public class WordDao {
    private static final Logger logger = LoggerFactory.getLogger(WordDao.class);

    private static final String GET_WORD = "SELECT * FROM words WHERE id_word = ?";
    private static final String GET_AMOUNT_OF_WORDS = "SELECT COUNT(*) AS total FROM words w JOIN vocabulary v ON w.id_vocabulary = v.id_vocabulary WHERE id_user = ?";
    private static final String SAVE_WORD = "INSERT INTO words(foreign_word, native_word, transcription, id_vocabulary) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_WORD = "UPDATE words SET foreign_word = ?, native_word = ?, transcription = ? WHERE id_word = ?";
    private static final String DELETE_WORD = "DELETE FROM words WHERE id_word = ?;";

    private ConnectionManager connectionManager;

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private Connection getConnection() throws SQLException {
        return connectionManager.getConnection();
    }
    
    public Word getWord(Long id) throws DaoException {
        logger.debug("Method getWord was invoked with id: {}", id);

        Word word = null;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_WORD))
        {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            logger.trace("Query was successfully invoked");
            if(rs.next()) {
                word = new Word(
                        rs.getLong("id_word"),
                        rs.getString("foreign_word"),
                        rs.getString("native_word"),
                        rs.getString("transcription"));
            }
        } catch (SQLException ex) {
            logger.error("Cannot get Word by Id: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        logger.debug("Word was received: {}", word);
        return word;
    }

    public int getAmountOfWords(Long userId) throws DaoException {
        int amount = 0;

        logger.trace("Method getAmountOfWords was invoked with userId - {}", userId);

        try (Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_AMOUNT_OF_WORDS)){
            stmt.setLong(1, userId);

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()) {
                amount = resultSet.getInt("total");
            }
        } catch (SQLException ex) {
            logger.error("Cannot an amount of words: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        return amount;
    }

    public Long saveWord(Word word, Long vocabularyId) throws DaoException {
        logger.debug("Method saveWord was invoked with vocabularyId - {} for word: {}", vocabularyId, word);

        Long id = -1L;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SAVE_WORD, new String[]{"id_word"}))
        {
            stmt.setString(1, word.getForeignWord());
            stmt.setString(2, word.getNativeWord());
            stmt.setString(3, word.getTranscription());
            stmt.setLong(4, vocabularyId);
            stmt.executeUpdate();
            logger.trace("Query was successfully invoked");

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException ex) {
            logger.error("Cannot save Word in Vocabulary: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        logger.debug("Method saveWord returned {}", id);
        return id;
    }

    /**
     * id - id of word which must be updated
     * newWord - word, which must be inserted instead old one
     * */
    public boolean updateWord(Word word) throws DaoException {
        logger.debug("Method updateWord was invoked with word: {}", word);

        boolean change = false;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_WORD))
        {
            stmt.setString(1, word.getForeignWord());
            stmt.setString(2, word.getNativeWord());
            stmt.setString(3, word.getTranscription());
            stmt.setLong(4, word.getId());
            int i = stmt.executeUpdate();
            logger.trace("Query was successfully invoked");
            if(i > 0) {
                change = true;
            }
        } catch (SQLException ex) {
            logger.error("Cannot update Word: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        logger.debug("Method updateWord returned {}", change);
        return change;
    }

    public boolean deleteWord(Long id) throws DaoException {
        logger.debug("Method deleteWord was invoked with id: {}", id);

        boolean change = false;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_WORD))
        {
            stmt.setLong(1, id);
            int i = stmt.executeUpdate();
            logger.trace("Query was successfully invoked");
            if(i > 0) {
                change = true;
            }
        } catch (SQLException ex) {
            logger.error("Cannot delete Word: " +ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        logger.debug("Method deleteWord returned {}", change);
        return change;
    }
}
