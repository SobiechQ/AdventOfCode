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
        try (var stream = new BufferedReader(new FileReader("src/main/java/Day4/input"))){
            List<String> strings = stream.lines().toList();
            var a = strings.stream()
                    .map(s->s.replaceAll(" +", " "))
                    .map(s->s.split(" \\| "))
                    .map(s->new String[]{s[0].split(": ")[1], s[1]})
                    .map(s->List.of(
                            Arrays.stream(s[0].split(" ")).map(Integer::parseInt).toList(),
                            Arrays.stream(s[1].split(" ")).map(Integer::parseInt).toList()
                    ))
                    .collect((Supplier<List<List<Set<Integer>>>>) ArrayList::new,
                            (lists, lists2) -> lists.add(List.of(
                                    new HashSet<>(lists2.get(0)),
                                    new HashSet<>(lists2.get(1))
                            )),
                            (lists, lists2) -> {}
                    );
            a.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

