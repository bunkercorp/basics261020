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


    public static Map <Integer, Integer> charEntries(String input){

        return
                input.chars()
                .mapToObj(item -> (int) item)
                .collect(Collectors.toList())
                .stream()
                .collect(HashMap::new, (x, y) -> {
                    if (x.containsKey(y))
                        x.put(y, x.get(y) + 1);
                    else
                        x.put(y,1);
                        }, HashMap:: putAll);
    }

    public static Map<Integer, Integer> wordLengths(String input){
        return
                Stream.of(input.split("[^a-zA-Z0-9'`''-''_']+"))
                .collect(Collectors.groupingBy(str -> str.length()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        a -> a.getKey(),
                        a -> a.getValue().size()
                ));

    }

    public static int countWords(String input){
               return
                Stream.of(input.split("[^a-zA-Z0-9'`''-''_']+"))
                .collect(Collectors.toList()).size();
    }



}