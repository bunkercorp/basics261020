import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public static boolean validateCode(String input){
        //Integer.parseInt()
        //Pattern p = Pattern.compile("[A-Z0-9]{20}//s[0-9]{6}");
        Pattern pattern = Pattern.compile("[A-Z0-9]{20}\\s[0-9]{6}");
        Matcher matcher = pattern.matcher(input);
        if(!matcher.find())
        return false;

        String right = input.substring(21);

        List<Integer> digit =
                input.chars()
                .mapToObj(item -> (int) item - 48)
                .filter(item -> {
                    return item >= 0 && item < 10;
                })
                .collect(Collectors.toList());

        if( digit.size() != 12)
            return  false;

        int res =1;
        Optional <Integer> left = IntStream
                .range(0, digit.size()/2)
                //.filter(digit.indexOf() % 2)
                .mapToObj(elem ->  {
                    if (elem %2 == 0)
                        return digit.get(elem) * 10 + digit.get(elem+1);
                    else
                       return digit.get(elem);
                })
                .collect(Collectors.toList())
                .stream()
                .filter(elem -> elem / 10 != 0 )
                .reduce((l, r) -> l*r);


        //System.out.println(left.get());
        return Long.parseLong(right) == left.get();


    }



}