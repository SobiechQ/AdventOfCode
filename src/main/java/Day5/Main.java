package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception{
        try (var stream = new BufferedReader(new FileReader("src/main/java/Day5/input"))) {
            var seeds = Arrays.stream(stream.readLine().split(": ")[1].split(" ")).map(Long::parseLong).toList();
//            seeds.forEach(System.out::println);
            StringBuilder sb = new StringBuilder();
            stream.lines()
                    .skip(1)
                    .flatMap(l->{
                        if (l.contains("map"))
                            return null;
                        if (l.isEmpty()){
                            String copy = sb.toString();
                            sb.setLength(0);
                            return Stream.of(copy);
                        }
                        sb.append(l).append("\n");
                        return null;
                    })
                    .forEach(System.out::println);
        }
    }
}
