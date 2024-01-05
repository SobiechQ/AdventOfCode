package Day5;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Almanac {
    Set<Line> lines = new TreeSet<>((o1, o2) -> (int) (o2.top() - o1.top()));
    public Almanac(String toParse){

    }
}
