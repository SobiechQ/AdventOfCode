package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try (var stream = new BufferedReader(new FileReader("src/main/java/Day4/input")).lines()){
            long start = System.currentTimeMillis();
            System.out.println(stream
//                    .parallel()
                    .map(s -> s.replaceAll(" +", " "))
                    .map(s -> s.split(" \\| "))
                    .map(s -> new String[]{s[0].split(": ")[1], s[1]})
                    .map(s -> List.of(
                            Arrays.stream(s[0].split(" ")).map(Integer::parseInt).toList(),
                            Arrays.stream(s[1].split(" ")).map(Integer::parseInt).toList()
                    ))
                    .map(l -> l.getFirst().stream().filter(i -> l.getLast().contains(i)).count())
                    .mapToLong(i -> i)
                    .filter(i -> i != 0)
                    .map(i -> (long) Math.pow(2, i - 1))
                    .sum());
            long finish = System.currentTimeMillis();
            System.out.println(finish - start);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

