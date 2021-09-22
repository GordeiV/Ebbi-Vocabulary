package entity;

public enum VocabularyStatus {
    FIRST_REPEAT,
    SECOND_REPEAT,
    THIRD_REPEAT,
    FOURTH_REPEAT,
    FIFTH_REPEAT,
    SIXTH_REPEAT,
    ARCHIVED;

    public static VocabularyStatus fromValue(int value) {
        for(VocabularyStatus vs : VocabularyStatus.values()) {
            if(vs.ordinal() == value) {
                return vs;
            }
        }
        throw new RuntimeException("Unknown value: " + value);
    }
}
