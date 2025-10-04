package domain;

import scan.IO;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * Класс с основной логикой игры
 */
public class Game {
    private final String secretWord;
    private final int maxGuesses;
    private final Set<Character> hits = new HashSet<>();
    private final Set<Character> misses = new HashSet<>();
    private boolean hintShown = false;

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

    /**
     * Выводит загаданное слово с масками на месте неугоаданных букв
     */
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
        IO io = new IO();
        String res = io.returnEnd(status());
        return masked() + ";" + res;
    }

    /**
     * Помечает все буквы слова как использованные
     */
    public void hitsAll(String word){
        for (int i = 0; i < word.length(); i++) {
            hits.add(word.charAt(i));
        }
    }

    /**
     * Показывает количество неправильных букв
     */
    public int getMissesCount() { return misses.size(); }

    /**
     * Показывает количество допустипых ошибок
     */
    public int getMaxGuesses() { return maxGuesses; }

    /**
     * Возвращает маску слова
     */
    public String getMasked() { return masked(); }

    /**
     * Показывает использованные буквы
     */
    public String getUsedLetters() {
        return Stream.concat(hits.stream(), misses.stream())
            .map(String::valueOf)
            .sorted()
            .collect(Collectors.joining(", "));
    }

    /**
     * Решает показывать ли подсказку в текущий момент игры или нет
     */
    public boolean shouldShowHint() {
        return !hintShown && maxGuesses > 0 && getAttemptsLeft() <= maxGuesses / 2;
    }

    /**
     * Помечает, что подсказка была показана
     */
    public void markHintShown() {
        this.hintShown = true;
    }

    /**
     * Показывает оставшиеся попытки
     */
    public int getAttemptsLeft() {
        return Math.max(0, maxGuesses - misses.size());
    }

    /**
     * Показывает загаданное слово
     */
    public String getSecretWord() {
        return secretWord;
    }
}
