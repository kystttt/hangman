package ports;

import domain.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Синглтон-класс для хранения и работы со словарем слов
 * В ключе словаря хранится Category категория слова, значение по ключи - массив со словами
 */
public class Dictionary implements WordSource {
    private static final Dictionary instance = new Dictionary();
    private final Map<Category, List<String>> dict = new HashMap<>();

    /**
     * Возвращает единственный экземпляр
     */
    public static Dictionary getInstance() {
        return instance;
    }

    /**
     * Добавляет слово по указанной категории
     */
    public void addWord(Category category, String word) {
        List<String> words = dict.computeIfAbsent(category, k -> new ArrayList<>());
        if (!words.contains(word)) {
            words.add(word);
        }
    }

    /**
     * Проверяет существует ли заданное слово в словаре
     * @param category категория слова
     * @param lookingWord слово, которое мы пытаемся найти
     * @return false - в случае отсутсвия слова в словаре,
     *         true  - в случае, если слово есть в словаре
     */
    public boolean isWordExist(Category category, String lookingWord){
        for (String word : dict.get(category)){
            if (word.equals(lookingWord)){
                return true;
            }
        }
        return false;
    }
    /**
     * Приватный конструктор для реализации паттерна синглтон,
     * чтобы гарантировать единственный экземпляр
     */
    private Dictionary() {}

    /**
     * Возвращает случайное слово по заданной категории
     * @param category категория слова
     */
    @Override
    public String getRandomWord(Category category){
        int sizeOfCategoriesArray = dict.get(category).size();
        int idx = ThreadLocalRandom.current().nextInt(sizeOfCategoriesArray);
        return dict.get(category).get(idx);
    }
}
