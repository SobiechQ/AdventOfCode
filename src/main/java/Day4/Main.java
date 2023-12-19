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
            var won = stream
                    .parallel()
                    .map(s -> s.replaceAll(" +", " "))
                    .map(s -> s.split(" \\| "))
                    .map(s -> new String[]{s[0].split(": ")[1], s[1]})
                    .map(s -> List.of(
                            Arrays.stream(s[0].split(" ")).map(Integer::parseInt).toList(),
                            Arrays.stream(s[1].split(" ")).map(Integer::parseInt).toList()
                    ))
                    .map(l -> l.getFirst().stream().filter(i -> l.getLast().contains(i)).count())
                    .mapToLong(i -> i).toArray();
            System.out.println(Arrays.toString(won));
            System.out.println(Arrays.stream(won).sum());
            long[] out = new long[won.length];
            Arrays.fill(out, 1);
            for (int i = 0; i < out.length; i++) {
                long oldValue = out[i];
                long wonTicket = won[i];
                for (int j = 1; j <= wonTicket; j++) {
                    out[j+i] += oldValue;
                }
            }
            System.out.println(Arrays.toString(out));
            System.out.println(Arrays.stream(out).sum());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

