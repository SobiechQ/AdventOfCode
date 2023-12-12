package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
        try (var stream = new BufferedReader(new FileReader("src/main/java/Day1/input"))) {
            List<String> lines = stream.lines().toList();
            var changed = lines.stream()
                    .map(s -> {
                        StringBuilder sCopy = new StringBuilder();
                        for (int i = 0; i < s.length(); i++) {
                            sCopy.append(s.charAt(i));
                            for (Number number : Number.values())
                                sCopy = new StringBuilder(sCopy.toString().replaceAll(number.name().toLowerCase(), String.valueOf(number.getValue())));

                        }


                        return sCopy.toString();
                    })
                    .map(s -> s.replaceAll("([A-Za-z])", ""))
//                    .map(s -> s.chars().collect(ArrayList<Integer>::new, ArrayList::add, ArrayList::addAll))
//                    .map(s -> Integer.parseInt(Character.toString(s.getFirst()) + Character.toString(s.getLast())))
                    .toList();
            var changedEnd = changed.stream()
//                    .map(s -> s.replaceAll("([A-Za-z])", ""))
                    .map(s -> s.chars().collect(ArrayList<Integer>::new, ArrayList::add, ArrayList::addAll))
                    .map(s -> Integer.parseInt(Character.toString(s.getFirst()) + Character.toString(s.getLast())))
                    .toList();



            for (int i = 0; i < changed.size(); i++) {
                System.out.println("ORIGINAL [" + lines.get(i) + "], CHANGED [" + changed.get(i) + "]" + ", CHANGED END [" + changedEnd.get(i) + "] ");
            }
            AtomicLong abc = new AtomicLong();
            changedEnd.stream().forEach((i)-> abc.addAndGet(i));
            System.out.println(abc.get());
        } catch (IOException e) {
            e.printStackTrace();
            // [fiveseven4eightwolq], CHANGED [5748], CHANGED END [58]  powinno być 52
            //wiecej niż 54970
        }
    }
}
