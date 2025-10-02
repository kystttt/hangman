package view;


import domain.Category;
import domain.Difficulty;
import domain.Game;


/**
 * Интерфейс для объектов визуализации
 */
public interface Render {
    /**
     * Рисует объект
     */
    void draw(Game game, Category category, Difficulty difficulty);
}

