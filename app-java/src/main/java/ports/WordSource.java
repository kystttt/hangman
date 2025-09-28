package ports;

import domain.Category;
import domain.Difficulty;

/**
 * Контракт для абстрагирования способа хранения слов.
 * Реализации отвечают за выбор слова с учётом nкатегории
 */
public interface WordSource {
    public String getRandomWord(Category category);
}
