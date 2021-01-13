import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class ht8 {

    // почему возвращаемый тип так жестко определен? Можно же просто List<String> возвращать
    public static ArrayList<String> getWords(String input) {
        String[] words = input.split("[^A-Za-z0-9_'-]");
        ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));
        wordList.removeAll(Arrays.asList("", null));
        return wordList;
        // жОстко. Как насчет  return Arrays.stream(input.split("[^A-Za-z0-9_'-]")).filter(s -> s != null && !s.isEmpty()).collect(Collectors.toList()); вместо всего, что написано выше?
    }

    public static boolean isNotEmpty(String input) {
        final boolean isNotEmpty = input != null && !input.isEmpty();
        return isNotEmpty;
    }

    public static Map<Object, Object> wordLengths(String input) {
        if (!isNotEmpty(input))
            return null;

        ArrayList<String> wordList = new ArrayList<String>(getWords(input));

        return wordList
                .stream()
                // мне идея тут подсвечивает
                .collect(Collectors.groupingBy(e -> e.length()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        // и тут
                        e -> e.getKey(),
                        e -> e.getValue().size()
                ));
    }

    public static long countWords(String input) {
        if (!isNotEmpty(input))
            return 0;

        // зачем ты делаешь полную копию выхлопа getWords? тебе достаточно сохранить на него ссылку: List<String> wordList = getWords(input)
        ArrayList<String> wordList = new ArrayList<String>(getWords(input));

        return wordList
                .size();

        // return isNotEmpty(input) ? getWords(input).size() : 0; И все.
    }

    // Map<Character, Integer> ???
    public static Map<Integer, Integer> charEntries(String input) {
        if (!isNotEmpty(input))
            return null; // не согласен. Вернуть пустую мапу здесь выглядит логичнее, так как для пустой\нулловой строки эта мапа именно что пустая, а не нулловая

        Map<Integer, Integer> charEntries = input.codePoints().boxed()
                .collect(toMap(
                        Function.identity(),
                        v -> 1,
                        (a, b) -> Integer.sum(a, b)));
        return charEntries;
    }

    enum Mode {
        UPPERCASE, LOWERCASE, CAPITALIZE;
    }

    public static String beautify(String input, Mode mode) {
        if (!isNotEmpty(input))
            return input;

        switch (mode) {
            case UPPERCASE: {
                //это оверкилл
                return Arrays.stream(input.split("\\s+"))
                        .map(n -> n.toUpperCase())
                        .collect(Collectors.joining(" "));

            }
            // и это оверкилл
            case LOWERCASE: {
                return Arrays.stream(input.split("\\s+"))
                        .map(n -> n.toLowerCase())
                        .collect(Collectors.joining(" "));

            }

            case CAPITALIZE: {
                return Arrays.stream(input.split("((?<=[^-`\\w])|(?=[^-`\\w]))"))
                        .map(n -> n.matches("\\w+") ? Character.toUpperCase(n.charAt(0)) + n.substring(1) : n)
                        .collect(Collectors.joining(""));
            }
        }
        return input;
    }

    public static boolean validateCode(String input) {
        if (!isNotEmpty(input))
            return false;

        final String[] inputArray = input.split("\\s");
        final String beforeSpace = inputArray[0];
        final String afterSpace = inputArray[1];
        return beforeSpace.chars()
                .filter(c -> Character.isDigit(c)
                        || (Character.isAlphabetic(c)
                        && Character.isUpperCase(c)))
                .count() == 20
                &&
                beforeSpace.chars().filter(c -> Character.isDigit(c)).count() == 6
                &&
                afterSpace.chars()
                        .filter(Character::isDigit)
                        .count() == 6;
    }

}