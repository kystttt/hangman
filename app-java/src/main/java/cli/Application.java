package cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.Predicate;
import domain.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import scan.IO;
import static java.util.Objects.nonNull;

public class Application  {
    private static IO io;
    public static void main(String[] args) {
        io = new IO(args[0], args[1]);
        if (args.length == 2){
            runNonInteractive(io.getFirstArg(), io.getSecondArg());
        }
    }
    private static void runNonInteractive(String secret, String guess){
        Game game = new Game(secret, 0);
        game.hitsAll(guess);
        System.out.println(game.batchResult(guess));
    }
}
