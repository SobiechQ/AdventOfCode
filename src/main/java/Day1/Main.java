package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try (var stream = new BufferedReader(new FileReader("src/main/java/Day1/input"))) {
//            System.out.println(
////                    .peek(System.out::println)
//                    .map(s -> s.replaceAll("([A-Za-z])", ""))
//                    .map(s -> s.chars().collect(ArrayList<Integer>::new, ArrayList::add, ArrayList::addAll))
//                    .map(s -> Integer.parseInt(Character.toString(s.getFirst()) + Character.toString(s.getLast())))
//
//                    .mapToInt(i->i)
//                    .sum());
            stream.lines()
                    .map(s -> {
                        String sCopy;
                        for (var sb = new StringBuilder(String.valueOf(s.charAt(0))); sb.length() < s.length() + 1; sb.append(s.charAt(sb.length() < s.length() ? sb.length() : 0))) {
                            sCopy = sb.toString();

                        }


//                        for (Number number : Number.values())
//                            s = s.replaceAll(number.name().toLowerCase(), String.valueOf(number.getValue()));
                        return s;
                    }).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
