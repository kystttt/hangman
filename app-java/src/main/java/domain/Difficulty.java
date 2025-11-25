package domain;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Енам для хранения всевозможных сложностей игры
 */
public enum Difficulty {
    EASY(8),
    MEDIUM(6),
    HARD(4);

    private final int attempts;

    Difficulty(int attempts) {
        this.attempts = attempts;
    }

    /**
     * Сколько попыток даёт эта сложность
     */
    public int attempts() {
        return attempts;
    }

    /**
     * Возвращает рандомную сложность игры
     */
    public static Difficulty getRandomDifficulty() {
        Difficulty[] VALUES = values();
        int i = ThreadLocalRandom.current().nextInt(VALUES.length);
        return VALUES[i];
    }
}
