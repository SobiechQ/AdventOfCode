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
            System.out.println(stream.lines()
                    .map(Main::reduceTwoNumbers)
                    .map(Main::replaceNames)
                    .map(s -> s.replaceAll("[a-z]", ""))
                    .mapToInt(Integer::parseInt)
                    .sum());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String reduceTwoNumbers(String s){
        StringBuilder sb = new StringBuilder();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            String string = sb.toString();
            output.append(s.charAt(i));
            for (Number number : Number.values())
                if (string.contains(number.name().toLowerCase()) || string.contains(String.valueOf(number.getValue()))) {
                    i = s.length();
                    break;
                }
        }
        sb.setLength(0);
        for (int i = s.length()-1; i >= 0 ; i--) {
            sb.reverse();
            sb.append(s.charAt(i));
            sb.reverse();
            String string = sb.toString();
            for (Number number : Number.values()){
                if (string.contains(number.name().toLowerCase()) || string.contains(String.valueOf(number.getValue()))) {
                    i = 0;
                    break;
                }
            }
        }
        output.append(sb);
        return output.toString();
    }

    public static String replaceNames(String s){
        for (Number number : Number.values())
            s = s.replace(number.name().toLowerCase(), String.valueOf(number.getValue()));
        return s;
    }
}
