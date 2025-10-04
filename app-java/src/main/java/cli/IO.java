package cli;

import domain.Category;
import domain.Difficulty;
import domain.GameStatus;
import out.Messages;

import java.nio.charset.Charset;
import java.util.Locale;


/**
 * Класс, который отвечает за считывание и вывод данных, а также валидацию их и привидение к нижнему регистру
 */
public class IO {
    private final String firstArg;
    private final String secondArg;

    /**
     * Конструктор, здесь мы считываем и валидируем данные
     * @param firstArg первый аргумент
     * @param secondArg второй аргумент
     * @throws IllegalArgumentException выдает исключение в случае если один из аргументов null
     */
    public IO(String firstArg, String secondArg) throws IllegalArgumentException {
        if (firstArg == null || secondArg == null) {
            throw new IllegalArgumentException(Messages.NULL_ARGS);
        }
        String f = firstArg.trim();
        String s = secondArg.trim();
        if (f.isEmpty() || s.isEmpty()) {
            throw new IllegalArgumentException(Messages.NULL_ARGS);
        }
        if (!isAllLetters(f) || !isAllLetters(s)) {
            throw new IllegalArgumentException("Only letters are allowed");
        }
        f = f.toLowerCase(Locale.ROOT);
        s = s.toLowerCase(Locale.ROOT);
        this.firstArg  = f;
        this.secondArg = s;
    }

    /**
     * Проверяет что все символы слова(строки) - буквы
     * @param word слово, символы которого проверяем
     */
    private static boolean isAllLetters(String word) {
        int i = 0;
        while (i < word.length()) {
            int cp = word.codePointAt(i);
            if (!Character.isLetter(cp)) return false;
            i += Character.charCount(cp);
        }
        return true;
    }

    /**
     * Подстраивается под кодировку консоли
     */
    public static Charset consoleCs() {
        return (System.console() != null) ? System.console().charset()
            : Charset.defaultCharset();
    }

    /**
     * Конструктор без параметров для интерактивного режима
     */
    public IO(){
        this.firstArg = "";
        this.secondArg = "";
    }

    /**
     * Просит ввести букву
     */
    public void outEnterMessage(){
        System.out.print(Messages.INTER_LETTER);
    }

    /**
     * Считывает ввод пользователя, проверяет на корректность
     * @param letter введенная буква
     * @return букву, приведенную к нижнему регистру или сообщение об ошибке, в случае если ввод не является буквой
     * или является пустым символом
     */
    public char inputLetter(String letter){
        if (letter == null) return '\0';
        letter = letter.trim();
        if (letter.isEmpty() || letter.codePointCount(0, letter.length()) != 1) {
            System.out.println(Messages.INCORRECT_LETTER);
            return '\0';
        }
        int cp = letter.codePointAt(0);
        if (!Character.isLetter(cp)) {
            System.out.println(Messages.INCORRECT_LETTER);
            return '\0';
        }
        cp = Character.toLowerCase(cp);
        return (char) cp;
    }

    /**
     * Сообщает, что буква была использована
     */
    public void usedLetter(){
        System.out.println();
        System.out.println(Messages.USED_LETTER);
    }

    /**
     * Геттер для первого аргумента
     */
    public String getFirstArg(){
        return firstArg;
    }

    /**
     * Геттер для второго аргумента
     */
    public String getSecondArg(){
        return secondArg;
    }

    /**
     * Возвращает WIN! или LOSE! в зависимости от исхода игры
     */
    public String returnEnd(GameStatus status) {
        return status == GameStatus.WON ? Messages.WINNER : Messages.LOSER;
    }


    /**
     * Выводит категорию слова и сложность игры
     * @param category
     * @param difficulty
     */
    public void printDiffCat(Category category, Difficulty difficulty) {
        System.out.printf(Messages.CATEGORY_IS + category.name() + " " + Messages.DIFFICULTY_IS + difficulty.name() + "\n");
    }

    /**
     * Печатет маску слова
     */
    public void printMask(String mask) {
        System.out.println(mask);
    }

}
