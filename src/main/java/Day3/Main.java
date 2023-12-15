package Day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try (var stream = new BufferedReader(new FileReader("src/main/java/Day3/input"))){
            List<String> strings = stream.lines().toList();
            //mm może nie xD
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
