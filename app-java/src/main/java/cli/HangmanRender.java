package cli;

import domain.Category;
import domain.Difficulty;
import domain.Game;
import adapters.Hints;
import out.Messages;

/**
 * Рисует висилицу поэтапно
 */
public class HangmanRender implements Render {
    private final Hints hints;
    private static final String[] STAGES = {
        """
            +
            |
            |
            |
            |
            |
      =========
      """,
        """
        +---+
        |   |
            |
            |
            |
            |
      =========
      """,
        """
        +---+
        |   |
        O   |
            |
            |
            |
      =========
      """,
        """
        +---+
        |   |
        O   |
        |   |
            |
            |
      =========
      """,
        """
        +---+
        |   |
        O   |
       /|   |
            |
            |
      =========
      """,
        """
        +---+
        |   |
        O   |
       /|\\  |
            |
            |
      =========
      """,
        """
        +---+
        |   |
        O   |
       /|\\  |
       /    |
            |
      =========
      """,
        """
        +---+
        |   |
        O   |
       /|\\  |
       / \\  |
            |
      =========
      """
    };

    public HangmanRender(Hints hints) {
        this.hints = hints;
    }

    @Override
    public void draw(Game game, Category category, Difficulty difficulty){
        int misses = game.getMissesCount();
        int idx = mapProgress(misses, game.getMaxGuesses());
        System.out.println(STAGES[idx]);
        int left = Math.max(0, game.getMaxGuesses() - misses);
        System.out.printf(Messages.CATEGORY_IS + category.name() + " " + Messages.DIFFICULTY_IS + difficulty.name() + "\n");
        System.out.printf(Messages.WORD_IS + game.getMasked() + "\n");
        System.out.printf(Messages.ATTEMPTS_IS + left + "\n");
        System.out.printf(Messages.USED_WORDS + game.getUsedLetters() + "\n");
        System.out.println();

        if (game.shouldShowHint()) {
            String hint = hints.getHint(game.getSecretWord());
            if (hint != null && !hint.isBlank()) {
                System.out.println(Messages.HINT + hint);
                game.markHintShown();
            }
        }
    }

    /**
     * Если у нас количество кадров не совпадает с количеством допустимых ошибок
     */
    private static int mapProgress(int misses, int maxGuesses) {
        int frames = STAGES.length - 1;
        if (maxGuesses <= 0) return Math.min(misses, frames);
        double ratio = Math.min(1.0, Math.max(0.0, (double) misses / (double) maxGuesses));
        int idx = (int) Math.round(ratio * frames);
        return Math.max(0, Math.min(idx, frames));
    }
}

