package scan;

import java.util.Locale;
import java.util.Objects;

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
        if ((firstArg == null) ^ (secondArg == null)) {
            throw new IllegalArgumentException(Messages.NULL_ARGS);
        }
        this.firstArg  = Objects.requireNonNullElse(firstArg,  "").trim().toLowerCase(Locale.ROOT);
        this.secondArg = Objects.requireNonNullElse(secondArg, "").trim().toLowerCase(Locale.ROOT);
    }

    /**
     * Просит ввести букву
     */
    public String outEnterMessage(){
        return Messages.INTER_LETTER;
    }

    /**
     * Считывает ввод пользователя, проверяет на корректность
     * @param letter введенная буква
     * @return букву, приведенную к нижнему регистру или сообщение об ошибке, в случае если ввод не является буквой
     * или является пустым символом
     */
    public char inputLetter(String letter){
        if (letter.length() != 1){
            System.out.println(Messages.INTER_LETTER);
            return '\0';
        }
        letter = letter.toLowerCase(Locale.ROOT);
        return letter.charAt(0);
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
}
