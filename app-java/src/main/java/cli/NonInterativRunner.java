package cli;

import domain.Game;

public class NonInterativRunner implements Runner {
    String secret;
    String guess;

    /**
     * Констурктор раннера, где задаем загаданное и угаданное слова
     * @param secret загаданное слово
     * @param guess введенное пользователем слово
     */
    public NonInterativRunner(String secret, String guess) {
        this.secret = secret;
        this.guess = guess;
    }

    @Override
    public void run() {
        Game game = new Game(secret, 0);
        game.hitsAll(guess);
        System.out.println(game.batchResult(guess));
    }
}
