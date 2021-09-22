package dao;


import entity.User;
import entity.Vocabulary;
import entity.VocabularyStatus;
import entity.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dao.util.ConnectionManager;
import dao.util.PoolConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class VocabularyDao {
    private static final Logger logger = LoggerFactory.getLogger(VocabularyDao.class);

    private static final String FIND_VOCABULARY = "SELECT * FROM vocabulary WHERE UPPER(v_name) REGEXP UPPER(?)";
    private static final String GET_WORDS_FROM_VOCABULARY = "SELECT * FROM words WHERE id_vocabulary = ?";
    private static final String INSERT_VOCABULARY = "INSERT INTO vocabulary(v_name, v_date, id_user, v_status, next_repeat_time) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_VOCABULARY_BY_ID = "SELECT * FROM vocabulary WHERE id_vocabulary = ?";
    private static final String GET_VOCABULARIES_FOR_REPEAT = "SELECT * FROM vocabulary WHERE next_repeat_time < NOW() AND id_user = ?";
    private static final String GET_ALL_VOCABULARIES = "SELECT * FROM vocabulary WHERE id_user = ?";
    private static final String DELETE_VOCABULARY = "DELETE FROM vocabulary WHERE id_vocabulary = ?;";
    private static final String UPDATE_VOCABULARY_NAME = "UPDATE vocabulary SET v_name = ? WHERE id_vocabulary = ?";
    private static final String UPDATE_VOCABULARY = "UPDATE vocabulary SET v_name = ?, next_repeat_time = ?, v_status = ? WHERE id_vocabulary = ?";

    private ConnectionManager connectionManager;

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private Connection getConnection() throws SQLException {
        return connectionManager.getConnection();
    }

    public Vocabulary getVocabularyById(Long id) throws DaoException {
        Vocabulary vocabulary = null;

        try (Connection con = getConnection();
             PreparedStatement stmtFindVocabulary = con.prepareStatement(GET_VOCABULARY_BY_ID);
             PreparedStatement stmtFindWord = con.prepareStatement(GET_WORDS_FROM_VOCABULARY))
        {
            stmtFindVocabulary.setLong(1, id);

            ResultSet resultSet = stmtFindVocabulary.executeQuery();

            if(resultSet.next()) {
                String name = resultSet.getString("v_name");
                LocalDateTime date = resultSet.getTimestamp("v_date").toLocalDateTime();
                LocalDateTime repeatTime = resultSet.getTimestamp("next_repeat_time").toLocalDateTime();
                VocabularyStatus status = VocabularyStatus.values()[resultSet.getInt("v_status")];

                vocabulary = new Vocabulary(name, date, repeatTime, status, id);
            }

            stmtFindWord.setLong(1, id);
            ResultSet rsWithWords = stmtFindWord.executeQuery();


            while (rsWithWords.next()) {
                Long wordId = rsWithWords.getLong("id_word");
                String foreignWord = rsWithWords.getString("foreign_word");
                String nativeWord = rsWithWords.getString("native_word");
                String transcription = rsWithWords.getString("transcription");
                vocabulary.addWord(new Word(wordId, foreignWord, nativeWord, transcription));
            }
        } catch (SQLException e) {
            logger.error("Cannot get Vocabulary by Id: " + id + " : " + e.getMessage(), e);
            throw new DaoException();
        }

        return vocabulary;
    }

    public List<Vocabulary> getAllVocabularies(Long userId) throws DaoException {
        logger.debug("Method getVocabulariesForRepeat was invoked");

        List<Vocabulary> vocabularies = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmtFindVocabulary = con.prepareStatement(GET_ALL_VOCABULARIES);
             PreparedStatement stmtFindWord = con.prepareStatement(GET_WORDS_FROM_VOCABULARY)) {
            stmtFindVocabulary.setLong(1, userId);
            ResultSet rsWithVocabularies = stmtFindVocabulary.executeQuery();

            while (rsWithVocabularies.next()) {
                String name = rsWithVocabularies.getString("v_name");
                LocalDateTime date = rsWithVocabularies.getTimestamp("v_date").toLocalDateTime();
                LocalDateTime repeatTime = rsWithVocabularies.getTimestamp("next_repeat_time").toLocalDateTime();
                VocabularyStatus status = VocabularyStatus.values()[rsWithVocabularies.getInt("v_status")];
                Long id = rsWithVocabularies.getLong("id_vocabulary");

                Vocabulary vocabulary = new Vocabulary(name, date, repeatTime, status, id);

                stmtFindWord.setLong(1, id);
                ResultSet rsWithWords = stmtFindWord.executeQuery();
                logger.trace("Query was successfully invoked");

                while (rsWithWords.next()) {
                    Long wordId = rsWithWords.getLong("id_word");
                    String foreignWord = rsWithWords.getString("foreign_word");
                    String nativeWord = rsWithWords.getString("native_word");
                    String transcription = rsWithWords.getString("transcription");
                    vocabulary.addWord(new Word(wordId, foreignWord, nativeWord, transcription));
                }
                logger.debug("Vocabularies were received. Vocabulary (number {}): {}", vocabularies.size(), vocabulary);
                vocabularies.add(vocabulary);
            }

        } catch (SQLException ex) {
            logger.error("Cannot get all user's Vocabularies by UserId: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        return vocabularies;
    }

    public List<Vocabulary> getVocabulariesForRepeat(Long userId) throws DaoException {
        logger.debug("Method getVocabulariesForRepeat was invoked");

        List<Vocabulary> vocabularies = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmtFindVocabulary = con.prepareStatement(GET_VOCABULARIES_FOR_REPEAT);
             PreparedStatement stmtFindWord = con.prepareStatement(GET_WORDS_FROM_VOCABULARY)) {
            stmtFindVocabulary.setLong(1, userId);
            ResultSet rsWithVocabularies = stmtFindVocabulary.executeQuery();

            while (rsWithVocabularies.next()) {
                String name = rsWithVocabularies.getString("v_name");
                LocalDateTime date = rsWithVocabularies.getTimestamp("v_date").toLocalDateTime();
                LocalDateTime repeatTime = rsWithVocabularies.getTimestamp("next_repeat_time").toLocalDateTime();
                VocabularyStatus status = VocabularyStatus.values()[rsWithVocabularies.getInt("v_status")];
                Long id = rsWithVocabularies.getLong("id_vocabulary");

                Vocabulary vocabulary = new Vocabulary(name, date, repeatTime, status, id);

                stmtFindWord.setLong(1, id);
                ResultSet rsWithWords = stmtFindWord.executeQuery();
                logger.trace("Query was successfully invoked");

                while (rsWithWords.next()) {
                    Long wordId = rsWithWords.getLong("id_word");
                    String foreignWord = rsWithWords.getString("foreign_word");
                    String nativeWord = rsWithWords.getString("native_word");
                    String transcription = rsWithWords.getString("transcription");
                    vocabulary.addWord(new Word(wordId, foreignWord, nativeWord, transcription));
                }
                logger.debug("Vocabularies were received. Vocabulary (number {}): {}", vocabularies.size(), vocabulary);
                vocabularies.add(vocabulary);
            }

        } catch (SQLException ex) {
            logger.error("Cannot get vocabularies for repeat: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        return vocabularies;
    }

    public List<Vocabulary> findVocabulary(String pattern) throws DaoException {
        logger.debug("Method findVocabulary was invoked with pattern: {}", pattern);

        List<Vocabulary> vocabularies = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmtFindVocabulary = con.prepareStatement(FIND_VOCABULARY);
             PreparedStatement stmtFindWord = con.prepareStatement(GET_WORDS_FROM_VOCABULARY)) {
            stmtFindVocabulary.setString(1, ".*?" + pattern + ".*?");
            ResultSet rsWithVocabularies = stmtFindVocabulary.executeQuery();
            logger.trace("Query was successfully invoked");

            while (rsWithVocabularies.next()) {
                String name = rsWithVocabularies.getString("v_name");
                LocalDateTime date = rsWithVocabularies.getTimestamp("v_date").toLocalDateTime();
                LocalDateTime repeatTime = rsWithVocabularies.getTimestamp("next_repeat_time").toLocalDateTime();
                VocabularyStatus status = VocabularyStatus.values()[rsWithVocabularies.getInt("v_status")];
                Long id = rsWithVocabularies.getLong("id_vocabulary");

                Vocabulary vocabulary = new Vocabulary(name, date, repeatTime, status, id);

                stmtFindWord.setLong(1, id);
                ResultSet rsWithWords = stmtFindWord.executeQuery();

                while (rsWithWords.next()) {
                    Long wordId = rsWithWords.getLong("id_word");
                    String foreignWord = rsWithWords.getString("foreign_word");
                    String nativeWord = rsWithWords.getString("native_word");
                    String transcription = rsWithWords.getString("transcription");
                    vocabulary.addWord(new Word(wordId, foreignWord, nativeWord, transcription));
                }
                logger.debug("Vocabularies were received. Vocabulary (number {}): {}", vocabularies.size(), vocabulary);
                vocabularies.add(vocabulary);
            }

        } catch (SQLException ex) {
            logger.error("Cannot find Vocabulary by pattern: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        return vocabularies;
    }

    public Long saveVocabulary(Vocabulary vocabulary, User user) throws DaoException {
        logger.debug("Method saveVocabulary was invoked with \nVocabulary: {}\nUser: {}", vocabulary, user);

        Long result = -1L;
        WordDao wordDao = new WordDao();
        wordDao.setConnectionManager(new PoolConnectionManager());

        logger.debug("Vocabulary: {}", vocabulary);

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_VOCABULARY, new String[]{"id_vocabulary"})) {
            stmt.setString(1, vocabulary.getName());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(3, user.getId());
            stmt.setInt(4, VocabularyStatus.FIRST_REPEAT.ordinal());
            LocalDateTime nextRepeat = LocalDateTime.now().plusDays(1);
            stmt.setTimestamp(5, Timestamp.valueOf(nextRepeat));

            stmt.executeUpdate();
            logger.trace("Query was successfully invoked");

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getLong(1);
            }
            for(Word word : vocabulary.getWords()) {
                wordDao.saveWord(word, result);
            }
            generatedKeys.close();

        } catch (Exception ex) {
            logger.error("Cannot save Vocabulary: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        logger.debug("Method saveVocabulary returned {}", result);
        return result;
    }

    public boolean deleteVocabulary(Long id) throws DaoException {
        logger.debug("Method deleteVocabulary was invoked with id: {}", id);

        boolean change = false;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_VOCABULARY)) {
            stmt.setLong(1, id);
            int i = stmt.executeUpdate();
            logger.trace("Query was successfully invoked");
            if (i > 0) {
                change = true;
            }
        } catch (SQLException ex) {
            logger.error("Cannot delete Vocabulary by Id: " + ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        logger.debug("Method deleteVocabulary returned {}", change);
        return change;
    }

    public boolean updateVocabularyName(Vocabulary vocabulary) throws DaoException {
        boolean change = false;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_VOCABULARY_NAME)) {
            stmt.setString(1, vocabulary.getName());
            stmt.setLong(2, vocabulary.getId());
            int i = stmt.executeUpdate();
            logger.trace("Query was successfully invoked");
            if (i > 0) {
                change = true;
            }
        } catch (SQLException ex) {
            logger.error("Cannot update Vocabulary: " +ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        logger.debug("Method updateVocabulary returned {}", change);
        return change;
    }

    public boolean updateVocabulary(Vocabulary vocabulary) throws DaoException {
        boolean change = false;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_VOCABULARY)) {
            stmt.setString(1, vocabulary.getName());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(vocabulary.getRepeatTime()));
            stmt.setInt(3, vocabulary.getVocabularyStatus().ordinal());
            stmt.setLong(4, vocabulary.getId());
            int i = stmt.executeUpdate();
            logger.trace("Query was successfully invoked");
            if (i > 0) {
                change = true;
            }
        } catch (SQLException ex) {
            logger.error("Cannot update Vocabulary: " +ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        logger.debug("Method updateVocabulary returned {}", change);
        return change;
    }

    public void setVocabularyAsRepeated(Long id) throws DaoException {
        Vocabulary vocabulary = getVocabularyById(id);
        logger.info("Before changing: {}", vocabulary);
        vocabulary.setAsRepeated();
        logger.info("After changing: {}", vocabulary);
        updateVocabulary(vocabulary);
    }
}
