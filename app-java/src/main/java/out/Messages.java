package out;

/**
 * Хранятся сообщения, которые видит игрок в консоли
 */
public final class Messages {
    public static final String NULL_ARGS = "One of arguments is null";
    public static final String INTER_LETTER = "Inter one letter: ";
    public static final String INCORRECT_LETTER = "Incorrect letter\n Please try again";
    public static final String USED_LETTER = "Letter is used";
    public static final String CATEGORY_IS = "Category: ";
    public static final String DIFFICULTY_IS = "Difficulty: ";
    public static final String WORD_IS = "Word: ";
    public static final String USED_WORDS = "Used: ";
    public static final String ATTEMPTS_IS = "Attempts: ";
    public static final String WINNER = "WIN!";
    public static final String LOSER = "LOSE!";
    public static final String HINT = "Hint: ";
    public static final String INTER_DIFFICULTY = "Please enter difficulty game (EASY/MEDIUM/HARD, empty = random): ";
    public static final String INTER_INCORRECT_DIFFICULTY = "Incorrect difficulty. Type EASY, MEDIUM or HARD (empty =" +
        " random). Try again:";
    private Messages(){}
}
