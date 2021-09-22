package entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Vocabulary {
    private ArrayList<Word> words = new ArrayList<>();
    private String name;
    private LocalDateTime date;
    private LocalDateTime repeatTime;
    private VocabularyStatus vocabularyStatus;
    private Long id;

    public Vocabulary(String name, LocalDateTime date, LocalDateTime repeatTime, VocabularyStatus vocabularyStatus, Long id) {
        this.name = name;
        this.date = date;
        this.repeatTime = repeatTime;
        this.vocabularyStatus = vocabularyStatus;
        this.id = id;
    }

    public Vocabulary(String name) {
        this.name = name;
    }

    public Vocabulary() {
    }

    public void addWord(String foreignWord, String nativeWord) {
        words.add(new Word(foreignWord, nativeWord));
    }

    public void addWord(String foreignWord, String nativeWord, String transcription) {
        words.add(new Word(foreignWord, nativeWord, transcription));
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public List<Word> findWord(String pattern) {
        List<Word> resultWords = new ArrayList<>();

        for (Word word : words) {
            if(word.getForeignWord().matches(".*?" + pattern + ".*?")) {
                resultWords.add(word);
            }
        }

        return resultWords;
    }

    public void setAsRepeated() {
        switch(vocabularyStatus) {
            case FIRST_REPEAT:
                repeatTime = LocalDateTime.now().plusDays(1);
                break;
            case SECOND_REPEAT:
                repeatTime = LocalDateTime.now().plusDays(2);
                break;
            case THIRD_REPEAT:
                repeatTime = LocalDateTime.now().plusDays(4);
                break;
            case FOURTH_REPEAT:
                repeatTime = LocalDateTime.now().plusDays(7);
                break;
            case FIFTH_REPEAT:
                repeatTime = LocalDateTime.now().plusDays(14);
                break;
            case SIXTH_REPEAT:
                repeatTime = LocalDateTime.now().plusDays(30);
                break;
        }

        if(vocabularyStatus != VocabularyStatus.ARCHIVED) {
            int newStatus = vocabularyStatus.ordinal() + 1;
            vocabularyStatus = VocabularyStatus.fromValue(newStatus);
        }
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(LocalDateTime repeatTime) {
        this.repeatTime = repeatTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VocabularyStatus getVocabularyStatus() {
        return vocabularyStatus;
    }

    public void setVocabularyStatus(VocabularyStatus vocabularyStatus) {
        this.vocabularyStatus = vocabularyStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "words=" + words +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", repeatTime=" + repeatTime +
                ", vocabularyStatus=" + vocabularyStatus +
                ", id=" + id +
                '}';
    }
}
