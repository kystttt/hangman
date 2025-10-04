import cli.IO;
import domain.Game;
import domain.GameStatus;
import domain.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестит некорректный ввод пользователя
 */
class MultiCharInputTest {

    /**
     * Проверяет что ничего фатального не произойдет при вводе 2 символов одновременно
     */
    @Test
    void multiCharInputDoesNotChangeState() {
        Game g = new Game("пенал", 4);
        IO io = new IO();
        String masked1 = g.getMasked();
        int misses1 = g.getMissesCount();
        GameStatus status1 = g.status();
        char bad = io.inputLetter("па");
        assertEquals('\0', bad, "Incorrect letter\n Please try again");
        assertEquals(masked1, g.getMasked());
        assertEquals(misses1, g.getMissesCount());
        assertEquals(status1, g.status());
        char ok = io.inputLetter("п");
        assertEquals('п', ok);
        Result r = g.guessLetter(ok);
        assertTrue(r == Result.HIT || r == Result.FINISH);
        assertEquals("п****", g.getMasked());
        assertEquals(GameStatus.IN_PROGRESS, g.status());
    }

    /**
     * Проверяет что ввод не буквы не производит краша приложения
     */
    @Test
    void nonLetterInputDoesNotChangeState() {
        Game g = new Game("кот", 4);
        IO io = new IO();
        String masked0 = g.getMasked();
        int misses0 = g.getMissesCount();
        char bad = io.inputLetter(",");
        assertEquals('\0', bad);
        assertEquals(masked0, g.getMasked());
        assertEquals(misses0, g.getMissesCount());
        assertEquals(GameStatus.IN_PROGRESS, g.status());
    }
}
