import adapters.Hints;
import cli.HangmanRender;
import domain.Game;
import domain.GameStatus;
import domain.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class GameStateTest {
    Game game;
    private HangmanRender render;
    private List<String> stages;

    @BeforeEach
    void setUp() {
        Hints hints = new Hints();
        hints.addHint("собака", "домашнее животное");
        hints.addHint("пенал", "для хранения письменных принадлежностей");
        render = new HangmanRender(hints);
        stages = render.getStages();

    }

    @Test
    public void gameStatesWinTest() {
        game = new Game("пенал", 8);
        assertEquals("*****", game.getMasked());
        assertEquals(0, game.getMissesCount());
        assertEquals(GameStatus.IN_PROGRESS, game.status());
        assertEquals("""
                  +
                  |
                  |
                  |
                  |
                  |
            =========
            """, stages.get(0));

        Result res1 = game.guessLetter('п');
        assertTrue(res1 == Result.HIT);
        assertEquals("п****", game.getMasked());
        assertEquals(GameStatus.IN_PROGRESS, game.status());
        assertEquals("""
                  +
                  |
                  |
                  |
                  |
                  |
            =========
            """, stages.get(0));

        Result res2 = game.guessLetter('х');
        assertTrue(res2 == Result.MISS);
        assertEquals("п****", game.getMasked());
        assertEquals(GameStatus.IN_PROGRESS, game.status());
        assertEquals("""
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========
            """, stages.get(1));

        Result res3 = game.guessLetter('е');
        assertTrue(res3 == Result.HIT);
        assertEquals("пе***", game.getMasked());
        assertEquals(GameStatus.IN_PROGRESS, game.status());
        assertEquals("""
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========
            """, stages.get(1));

        Result res4 = game.guessLetter('а');
        assertTrue(res4 == Result.HIT);
        assertEquals("пе*а*", game.getMasked());
        assertEquals(GameStatus.IN_PROGRESS, game.status());
        assertEquals("""
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========
            """, stages.get(1));


        Result res5 = game.guessLetter('н');
        assertTrue(res5 == Result.HIT);
        assertEquals("пена*", game.getMasked());
        assertEquals(GameStatus.IN_PROGRESS, game.status());
        assertEquals("""
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========
            """, stages.get(1));

        Result res7 = game.guessLetter('н');
        assertTrue(res7 == Result.REPEAT);
        assertEquals("пена*", game.getMasked());
        assertEquals(GameStatus.IN_PROGRESS, game.status());
        assertEquals("""
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========
            """, stages.get(1));

        Result res6 = game.guessLetter('л');
        assertTrue(res6 == Result.FINISH);
        assertEquals("пенал", game.getMasked());
        assertEquals(GameStatus.WON, game.status());
        assertEquals("""
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========
            """, stages.get(1));
    }

    @Test
    public void gameStatesLoseTest() {
        game = new Game("собака", 4);
        assertEquals("******", game.getMasked());
        assertEquals(0, game.getMissesCount());
        assertEquals(GameStatus.IN_PROGRESS, game.status());


        Result res1 = game.guessLetter('п');
        assertTrue(res1 == Result.MISS);
        assertEquals("******", game.getMasked());
        assertEquals(GameStatus.IN_PROGRESS, game.status());
        assertEquals("""
              +---+
              |   |
              O   |
                  |
                  |
                  |
            =========
            """, stages.get(2));

        Result res2 = game.guessLetter('о');
        assertTrue(res2 == Result.HIT);
        assertEquals("*о****", game.getMasked());
        assertEquals(GameStatus.IN_PROGRESS, game.status());
        assertEquals("""
              +---+
              |   |
              O   |
                  |
                  |
                  |
            =========
            """, stages.get(2));



        Result res3 = game.guessLetter('h');
        assertTrue(res3 == Result.MISS);
        assertEquals("*о****", game.getMasked());
        assertEquals(GameStatus.IN_PROGRESS, game.status());
        assertEquals(        """
        +---+
        |   |
        O   |
       /|   |
            |
            |
      =========
      """, stages.get(4));

        Result res4 = game.guessLetter('г');
        assertTrue(res4 == Result.MISS);
        assertEquals("*о****", game.getMasked());
        assertEquals(GameStatus.IN_PROGRESS, game.status());
        System.out.println(game.status());
        assertEquals(        """
        +---+
        |   |
        O   |
       /|\\  |
            |
            |
      =========
      """, stages.get(5));

        Result res5 = game.guessLetter('д');
        assertTrue(res5 == Result.FINISH);
        assertEquals("*о****", game.getMasked());
        assertEquals(GameStatus.LOST, game.status());
        System.out.println(game.status());
        assertEquals(        """
        +---+
        |   |
        O   |
       /|\\  |
       / \\  |
            |
      =========
      """, stages.get(7));
    }
}
