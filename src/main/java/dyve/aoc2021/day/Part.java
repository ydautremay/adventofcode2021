package dyve.aoc2021.day;

import dyve.aoc2021.input.InputReader;

import java.time.Duration;
import java.time.Instant;

public abstract class Part {

    public void subMain(int day) throws Exception {
        Instant instant = Instant.now();
        try(InputReader reader = InputReader.readInput(day)){
            System.out.println(execute(reader).toString());
        }
        System.out.println("Finished in : " + Duration.between(instant, Instant.now()).toMillis() + " ms");
        System.out.println("Consuming " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/(1024*1024)) + " MB memory");
    }

    protected abstract Object execute(InputReader reader) throws Exception;
}
