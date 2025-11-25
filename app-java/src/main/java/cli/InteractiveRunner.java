package cli;

import domain.Category;
import domain.Difficulty;
import domain.Game;
import domain.GameStatus;
import domain.Result;
import adapters.Dictionary;
import out.Messages;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Реализует интерфейс Runner, интерактивный запуск игры
 */
public class InteractiveRunner implements Runner {
    private final Charset cs;
    private final IO io;
    private final Dictionary dict;
    private final HangmanRender render;

    public InteractiveRunner(Charset cs, IO io, Dictionary dict, HangmanRender render) {
        this.cs = cs;
        this.io = io;
        this.dict = dict;
        this.render = render;
    }

    @Override
    public void run() throws Exception {
        System.setOut(new PrintStream(System.out, true, cs));
        System.setErr(new PrintStream(System.err, true, cs));
        try (Scanner scanner = new Scanner(new InputStreamReader(System.in, cs))) {
            Difficulty difficulty = null;
            while (difficulty == null) {
                System.out.print(Messages.INTER_DIFFICULTY);
                var parsed = io.parseDifficulty(scanner.nextLine());
                if (parsed.isPresent()) difficulty = parsed.get();
            }
            Category category = Category.getRandomCategory();
            String secretWord = Dictionary.getInstance().getRandomWord(category);
            int attempts = difficulty.attempts();
            Game game = new Game(secretWord, attempts);
            io.printMask(game.getMasked());
            System.out.println("You have " + game.getAttemptsLeft() + " to miss!");
            io.printDiffCat(category, difficulty);
            while (game.status() == GameStatus.IN_PROGRESS) {

                io.outEnterMessage();
                char letter = io.inputLetter(scanner.nextLine());
                if (letter == '\0') {
                    continue;
                }
                var resualt = game.guessLetter(letter);
                if (resualt == Result.REPEAT) {
                    io.usedLetter();
                }
                render.draw(game, category, difficulty);

            }
            System.out.println(io.returnEnd(game.status()));
        }
    }
}
