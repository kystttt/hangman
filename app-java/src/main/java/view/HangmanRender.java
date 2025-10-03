package view;

import domain.Category;
import domain.Difficulty;
import domain.Game;

/**
 * Рисует висилицу поэтапно
 */
public class HangmanRender implements Render{
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

    @Override
    public void draw(Game game, Category category, Difficulty difficulty){
        switch(difficulty){
            case EASY:

        }
    }
}
