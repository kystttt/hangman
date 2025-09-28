import cli.IO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Проверяет, что программа не запускается при невалидных аргументах
 */
class StartValidationTest {
    @Test
    void failsOnTooShortSecretOrGuess() {
        assertThrows(IllegalArgumentException.class, () -> new IO("а", "яйцо"));
        assertThrows(IllegalArgumentException.class, () -> new IO("яйцо", "б"));
        assertThrows(IllegalArgumentException.class, () -> new IO("", "яйцо"));
        assertThrows(IllegalArgumentException.class, () -> new IO(null, "яйцо"));
        assertThrows(IllegalArgumentException.class, () -> new IO("лол", null));
    }
}
