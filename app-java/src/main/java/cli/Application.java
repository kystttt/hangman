package cli;

import domain.Category;
import adapters.Dictionary;
import adapters.Hints;

/**
 * Главный класс приложения, где запускаем программу
 */
public class Application {
    private static IO io;
    Hints hints = new Hints();

    private static void seedDictionary(Dictionary dictionary) {
        dictionary.addWord(Category.ANIMAL, "бегемот");
        dictionary.addWord(Category.ANIMAL, "собака");
        dictionary.addWord(Category.JOB, "строитель");
        dictionary.addWord(Category.JOB, "Врач");
        dictionary.addWord(Category.ITEM, "ручка");
        dictionary.addWord(Category.ITEM, "парта");
        dictionary.addWord(Category.ITEM, "шезлонг");
        dictionary.addWord(Category.JOB, "Полицейский");
    }

    private static void seedHints(Hints hints) {
        hints.addHint("бегемот", "Большое животное, живет в Африке");
        hints.addHint("собака", "Друг человека");
        hints.addHint("строитель", "Возводит здания");
        hints.addHint("врач", "помогает людям");
        hints.addHint("ручка", "Ее постоянно используют школьники/студенты/работники офиса");
        hints.addHint("стол", "Есть в любом классе в школе");
        hints.addHint("Шезлонг", "Люди на нём проводят время у моря");
        hints.addHint("Полицейский", "Хранитель закона");
    }

    public static void main(String[] args) throws Exception {
        Dictionary dict = Dictionary.getInstance();
        seedDictionary(Dictionary.getInstance());
        Hints hints = new Hints();
        seedHints(hints);
        Runner runner;
        IO io;
        if (args.length == 2) {
            io = new IO(args[0], args[1]);
            runner = new NonInterativRunner(io.getFirstArg(), io.getSecondArg());
        } else if (args.length == 0) {
            io = new IO();
            runner = new InteractiveRunner(IO.consoleCs(), io, dict, new HangmanRender(hints));
        } else {
            throw new IllegalArgumentException("Input Error!");
        }
        runner.run();
    }
}
