import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.*;

public class Stream {
    private static List<String> words(String str, int mode){
        List<String> words = new ArrayList<>();
        if (mode == 1) words = Arrays.asList(str.split("\\s*[^a-zA-Z0-9_\'-]\\s*"));
        if (mode == 2) words = Arrays.asList(str.split("\\s+"));
            return words
                    .stream()
                    .filter(item -> !item.isEmpty())
                    .collect(toList());
    }
    
    public static Map<Integer, Integer> charEntries(String str) {
        if (str == null || str.isEmpty()) return null;
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
               // смысл аппер\ловеркейсить по словам?
                return words(str, 2)
                        .stream()
                        .map(String::toLowerCase)
                        .collect(Collectors.joining(" "));
            }
            case "UPPERCASE":
                return words(str, 2)
                        .stream()
                        .map(String::toUpperCase)
                        .collect(Collectors.joining(" "));
            case "CAPITALIZE":
                return words(str, 2)
                        .stream()
                        .map(sent -> {
                    sent.toLowerCase();
                    return sent = sent.substring(0,1).toUpperCase()+sent.substring(1).toLowerCase();
        }).collect(Collectors.joining(" "));
            default: return null;
        }
    }

    public static int countWords(String str){
        if (str == null || str.isEmpty()) return 0;
      //зачем копировать выхлоп? words(str, 1).size() это законно
        return new ArrayList<>(words(str, 1)).size();
    }

    public static Map<Integer, Integer> wordsLength(String str){
        return words(str, 1)
                .stream()
                .collect(groupingBy(String::length))
                .entrySet()
                .stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().size()
                ));
    }

    public static boolean validateCode(String str) {
        List<String> elements = words(str, 2);
        final boolean startValidation = (str == null || str.isEmpty() || elements.stream().count() != 2);
        if (startValidation) return false;
        final String firstPart = elements.get(0);
        final String secondPart = elements.get(1);
        final int getNumbers = Arrays.stream(firstPart
                .chars()
                .filter(Character::isDigit)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
                .split("(?<=\\G.{2})"))
                .map(Integer::parseInt)
                .reduce(1, (multiply, value) -> multiply * value);
        final int resultingNumb = Integer.parseInt(secondPart);
        final boolean validateNumbers = (getNumbers == resultingNumb);
        return firstPart.chars().filter(n -> Character.isDigit(n)
                || (Character.isAlphabetic(n)
                && Character.isUpperCase(n))).count() == 20
                && firstPart.chars().filter(Character::isDigit).count() == 6
                && validateNumbers
                && secondPart.chars().filter(Character::isDigit).count() == 6;
    }
}

