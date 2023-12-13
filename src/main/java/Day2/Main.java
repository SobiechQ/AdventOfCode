package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;



public class Main {
    public static void main(String[] args) {
        try (var stream = new BufferedReader(new FileReader("src/main/java/Day2/input"))) {
            var gamesMap = stream.lines()
                    .map(s->s.split(": "))
                    .collect((Supplier<HashMap<Integer, List<Game>>>) HashMap::new,
                            (map, games) -> {
                                map.putIfAbsent(Integer.parseInt(games[0].split(" ")[1]), new LinkedList<>());
                                map.get(Integer.parseInt(games[0].split(" ")[1])).addAll(Arrays.stream(games[1].split("; ")).map(Game::new).toList());
                            },
                            (integerListHashMap, integerListHashMap2) -> {});
            /*
            System.out.println(gamesMap.entrySet().stream()
                    //12 red, 13 geen, 14 blue
                    .filter(e -> e.getValue().stream().allMatch(Game::isPossible))
                    .mapToInt(Map.Entry::getKey)
                    .sum());
             */
            System.out.println(gamesMap.values().stream()
                    .map(l -> new Game(
                            l.stream().max(Comparator.comparingInt(Game::getRed)).get().getRed(),
                            l.stream().max(Comparator.comparingInt(Game::getGreen)).get().getGreen(),
                            l.stream().max(Comparator.comparingInt(Game::getBlue)).get().getBlue()))
                    .mapToInt(Game::getPower)

                    .sum());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
