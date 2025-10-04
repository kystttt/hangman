package ports;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, где хранятся подсказки
 */
public class Hints {
    private final Map<String, String> hints = new HashMap<>();

    /**
     * Заполняет подсказку по ключу-слову
     * @param word слово
     * @param hintForWord подсказка
     */
    public void addHint(String word, String hintForWord) {
        hints.put(word.toLowerCase(), hintForWord.toLowerCase());
    }

    /**
     * Возвращает подсказку по заданному слову
     * @param word слово, для которого нужна подсказка
     */
    public String getHint(String word){
        return hints.get(word);
    }
}
