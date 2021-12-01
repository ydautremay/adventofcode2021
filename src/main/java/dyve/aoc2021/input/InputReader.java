package dyve.aoc2021.input;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class InputReader implements Closeable {

    BufferedReader br;

    private InputReader(BufferedReader br){
        this.br = br;
    }

    public static InputReader readInput(int suffix) {
        final InputStream is = InputReader.class.getResourceAsStream("/day"+suffix);
        final Reader r = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new InputReader(new BufferedReader(r));
    }

    public Stream<String> stream(){
        return br.lines();
    }

    @Override
    public void close() throws IOException {
        br.close();
    }

    public static List<String> readInput(String suffix) throws IOException, URISyntaxException {
        try (Scanner scanner = new Scanner(Paths.get(InputReader.class.getClassLoader().getResource("resources/dyve.aoc2021.day"+suffix).toURI()))){
            scanner.useDelimiter("\\A");
            List<String> output = new ArrayList<>();
            while(scanner.hasNextLine()){
                output.add(scanner.nextLine());
            }
            return output;
        }
    }
}
