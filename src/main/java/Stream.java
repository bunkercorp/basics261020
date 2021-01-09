import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.*;

public class Stream {
    public static List<String> words(String str, int mode){
        List<String> words = new ArrayList<>();
        if (mode == 1)
            words = Arrays.asList(str.split("\\s*[^a-zA-Z0-9_\'-]\\s*"));
        if (mode == 2) words = Arrays.asList(str.split(""));
            return words
                    .stream()
                    .filter(item -> !item.isEmpty())
                    .collect(toList());
    }


    public static Map<Integer, Integer> charEntries(String str) {
        IntStream result = str.chars();
        return result.
               mapToObj(numb -> Integer.valueOf(numb))
                        .collect(groupingBy(item -> item, counting()))
                .entrySet()
                .stream()
//                .map(item -> Math.toIntExact(item.getValue()))
                .collect(toMap(
                        entry -> entry.getKey(),
                        entry -> Integer.valueOf(String.valueOf(entry.getValue()))
                      ))
                ;
    }




    public static String beautify(String str, String mode) {
        final boolean validation = (str == null || mode == null);
        if (validation) return "null";
        mode = mode.toUpperCase();
        switch (mode) {
            case "LOWERCASE": {
                return words(str, 2).stream().map(String::toLowerCase).collect(Collectors.joining());
            }
//            case "UPPERCASE":
//                return result = new Stream(str)
//                        .toString()
//                        .toUpperCase();
//                        break;
//            default: break;
//            return  result;
//        }
            default:
                break;
        }
        return str;
    }

    public static Map<Integer, Integer> wordsLength(String str){
        List<String> words = Arrays.asList(str.split("\\s*[^a-zA-Z0-9_\'-]\\s*"));
        return words
                .stream()
                .filter(item -> !item.isEmpty())
                .collect(groupingBy(String::length))
                .entrySet()
                .stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().size()
                ));
    }

    public static void main(String[] args) {
        System.out.println(wordsLength("Lorem Ips-um&Dolor    ,,&^8 *&  $_Sit Am'et"));
        System.out.println(charEntries("cssbss"));
        System.out.println(beautify("JGHKJHLGHEK IU(*DUVNKL ", "LOWERCASE"));
//        getCharEntries("wsjsfdf;ssldfsl slfsd sls ").stream().forEach(c -> c.forEach(System.out::println));
    }
}
