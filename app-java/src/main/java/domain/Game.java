package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс с основной логикой игры
 */
public class Game {
    private final String secretWord;
    private final int maxGuesses;
    private final Set<Character> hits = new HashSet<>();
    private final Set<Character> misses = new HashSet<>();

    /**
     * Конструктор с заданными параметрами, где:
     * @param secretWord загаданное слово
     * @param maxGuesses количество попыток
     */
    public Game(String secretWord, int maxGuesses) {
        this.secretWord = secretWord;
        this.maxGuesses = maxGuesses;
    }

    /**
     * Проверяет была ли использована буква
     * @param letter буква
     */
    public boolean wordIsUsed(char letter){
        return hits.contains(letter) || misses.contains(letter);
    }

    /**
     * Возвращает текущий статус игры
     * WON - Победа
     * LOST - Поражение
     * IN_PROGRESS - Игра все еще идет
     */
    public GameStatus status(){
        if (finishFinding()){
            return GameStatus.WON;
        }
        if (misses.size() >= maxGuesses){
            return GameStatus.LOST;
        }
        return GameStatus.IN_PROGRESS;
    }

    /**
     * Проверяет содержится ли буква в слове
     * @param letter буква, которую проверяем
     * @return возвращает результат проверки: есть, нет, повторно проверили букву, закончили игру.
     */
    public Result guessLetter(char letter){
        if (status() != GameStatus.IN_PROGRESS){
            return Result.FINISH;
        }
        if (wordIsUsed(letter)){
            return Result.REPEAT;
        }
        if (secretWord.indexOf(letter) >= 0) {
            hits.add(letter);
            return finishFinding()? Result.FINISH : Result.HIT;
        }
        else{
            misses.add(letter);
            return misses.size() >= maxGuesses? Result.FINISH : Result.MISS;
        }
    }

    private String masked(){
        StringBuilder result = new StringBuilder(secretWord.length());
        for (int i = 0; i < secretWord.length(); i++) {
            char c = secretWord.charAt(i);
            result.append(hits.contains(c) ? c : '*');
        }
        return result.toString();
    }

    /**
     * Проверят закончилась ли игра, возвращает true, если игра закончилась, в обратном случае - false
     */
    private boolean finishFinding(){
        for (int i = 0; i < secretWord.length(); i++) {
            if (!hits.contains(secretWord.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Проверяем целое слово на соответствие заданному
     * @param guess проверямое слово
     */
    public void checkGuessWord(String guess){
        for (int i = 0; i < guess.length() && status() == GameStatus.IN_PROGRESS; i++) {
            guessLetter(guess.charAt(i));
        }
    }

    /**
     * Возвращает Слово + POS в случае, если слово отгадано правильно, в ином случае -
     * возвращает слово с звездочками на места не отгаданных букв + NEG
     * @param guess введенное слово
     */
    public String batchResult(String guess){
        checkGuessWord(guess);
        String res = (status() == GameStatus.WON)? "POS" : "NEG";
        return masked() + ";" + res;
    }
}
