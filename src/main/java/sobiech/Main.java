package sobiech;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.BufferOverflowException;
import java.sql.Connection;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Character> instructions = null;
        Map<String, List<String>> directionsMap = null;
        try (var stream = new BufferedReader(new FileReader("src/main/java/sobiech/input"))) {
            instructions = stream.readLine().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            stream.readLine(); //Skip empty line
            directionsMap = stream.lines().
                    map(s -> Pattern.compile("([A-Z0-9])\\w+").matcher(s))
                    .map(matcher -> matcher.results().collect(Collectors.toList()))
                    .collect(HashMap::new,
                            (s1, s2) -> {
                                s1.putIfAbsent(s2.get(0).group(), new ArrayList<>());
                                s1.get(s2.get(0).group()).add(s2.get(1).group());
                                s1.get(s2.get(0).group()).add(s2.get(2).group());
                            },
                            (BiConsumer<Map<String, List<String>>, Map<String, List<String>>>) (s1, s2) -> {
                            }
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert instructions != null;
        assert directionsMap != null;

        int count = 0;
        /*
        String pointer = "AAA";
        do{
            Character instructionPointer = instructions.get((count++)%instructions.size());
            System.out.println("POINTER [" + pointer+"], INSTRUCTION [" + instructionPointer + "] ");
            pointer = directionsMap.get(pointer).get(instructionPointer.equals('L') ? 0:1);
        }while (!pointer.equals("ZZZ"));
        System.out.println("END POINTER [" + pointer+"], COUNT ["+count+"]");
        */

        /*

        AAA = (JXS, MFQ)
        RLA = (JSN, JVD)
        QLA = (TSH, RRN)
        QFA = (QQR, HDH)
        RXA = (NLJ, JPG)
        JSA = (TNJ, JXC)

         */


        String[] pointers = directionsMap.keySet().stream()
                .filter(s->Pattern.compile("[A-Z0-9]{2}A$").matcher(s).find()).toArray(String[]::new);
        boolean[] boolLeftInstructions = instructions.stream().map(new Function<Character, Boolean>() {
            @Override
            public Boolean apply(Character character) {
                return character.equals('L');
            }
        }).collect(Collectors.collectingAndThen(Collectors.toList(), booleans -> {
            boolean[] arr = new boolean[booleans.size()];
            for (int i = 0; i < booleans.size(); i++)
                arr[i] = booleans.get(i);
            return arr;
        }));
        Map<String, Integer> mapTimes = Arrays.stream(pointers).collect((Supplier<Map<String, Integer>>) HashMap::new, (stringIntegerMap, s) -> stringIntegerMap.putIfAbsent(s, 0), (stringIntegerMap, stringIntegerMap2) -> {});
        do{
            boolean boolPointerArr = boolLeftInstructions[(count)%boolLeftInstructions.length];
            for (int i = 0; i < pointers.length ; i++)
                pointers[i]=directionsMap.get(pointers[i]).get(boolPointerArr ? 0:1);
            if (Arrays.stream(pointers).filter(s->s.getBytes()[2]=='Z').count() == 1){
                String tmp = Arrays.stream(pointers).filter(s->s.getBytes()[2]=='Z').findAny().get();
                if (mapTimes.get(tmp) == 0)
                    mapTimes.put(tmp, -count);

                else {
                    int finalCount = count;
                    mapTimes.computeIfPresent(tmp, (s, integer) -> integer + finalCount);
                }
            }



        count++;
        }while (!Arrays.stream(pointers).allMatch(s->s.getBytes()[2]=='Z'));
        System.out.println("END POINTERS " + Arrays.toString(pointers) + ", COUNT [" + count + "]");

    }
}
