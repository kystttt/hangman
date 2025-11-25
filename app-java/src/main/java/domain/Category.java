package domain;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Енам для хранения всевозможных категорий слов
 */
public enum Category {
    ANIMAL,
    ITEM,
    JOB;

    /**
     * Вовзвращает рандомную категорию слова
     */
    public static Category getRandomCategory(){
        Category[] VALUES = values();
        int i = ThreadLocalRandom.current().nextInt(VALUES.length);
        return VALUES[i];
    }
}
