import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Home8 {


    public static String beautify(String input, String mode) {
        Stream<String> arrayStrings = Stream.of(input.split("[^a-zA-Z0-9'`''-''_']+"));
        //System.out.println(arrayStrings);
        switch (mode.toUpperCase()) {
            case "UPPERCASE":
                return arrayStrings.map(String::toUpperCase).
                        collect(Collectors.joining(" "));
            case "LOWERCASE":
                return arrayStrings.map(String::toLowerCase).
                        collect(Collectors.joining(" "));

            case "CAPITALIZE":
                return arrayStrings.map(a -> Character.toUpperCase(a.charAt(0))+a.substring(1).toLowerCase()).
                        collect(Collectors.joining(" "));
        }
        return input;
    }


    public static int countWords(String input){
        // List<Class_Example.WordBoundary> listIndex = ;
        return Class_Example.wordBoundaries(input).size();
    }

}