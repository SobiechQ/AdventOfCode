package Day2;

import lombok.*;

import java.util.Arrays;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Game {
    private int red;
    private int green;
    private int blue;
    public Game(String toBeParsed){
        Arrays.stream(toBeParsed.split(", "))
                .map(s->s.split(" "))
                .forEach(s->{
                    if (s[1].equals("red"))
                        this.red = Integer.parseInt(s[0]);
                    if (s[1].equals("green"))
                        this.green = Integer.parseInt(s[0]);
                    if (s[1].equals("blue"))
                        this.blue = Integer.parseInt(s[0]);
                });
    }

    public Game(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public boolean isPossible(){
        return !(this.red > 12 || this.green > 13 || this.blue > 14);
    }
    public int getPower(){
        return this.getRed() * this.getGreen() * this.getBlue();
    }

}
