package hw_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class HW_8 {
    enum Type {
        UPPERCASE,
        LOWERCASE,
        CAPITALIZE
    }

    private static final Collector<Object, StringBuilder, String> CHAR_SEQUENCE_COLLECTOR = Collector.of(
            StringBuilder::new,
            StringBuilder::append,
            StringBuilder::append,
            StringBuilder::toString);

    public static String beautify(String s, Type type) {


        switch (type) {
            //смысл аппер\ловеркейсить посимвольно?
            case LOWERCASE -> {
                return s.chars().mapToObj(obj -> Character.toLowerCase((char) obj)).collect(CHAR_SEQUENCE_COLLECTOR);
            }
            case UPPERCASE -> {
                return s.chars().mapToObj(obj -> Character.toUpperCase((char) obj)).collect(CHAR_SEQUENCE_COLLECTOR);
            }
            case CAPITALIZE -> {
                final Spacer spacer = new Spacer();
                return s.chars().mapToObj(obj -> {
                    char symb = (char) obj;
                    if (spacer.firstChar) {
                        symb = Character.toUpperCase(symb);
                        spacer.firstChar = false;
                    }
                    if (symb == ' ') {
                        spacer.isSpace = true;
                        return symb;
                    }
                    if (spacer.isSpace) {
                        symb = Character.toUpperCase(symb);
                        spacer.isSpace = false;
                    }
                    return symb;
                }).collect(CHAR_SEQUENCE_COLLECTOR);
            }
        }
        return s;
    }

    private static class Spacer {
        private boolean isSpace = false;
        private boolean firstChar = true;
    }

     // Map<Character, Integer> ???
    public static Map<Integer, Integer> charEntries(String statistic) {
        return statistic.codePoints().boxed()
                .collect(toMap(
                        Function.identity(),
                        n -> 1,
                        Integer::sum));
    }

    private static List<String> findWord(String s) {
        String[] words = s.split("[^A-Za-z0-9_'-]");
        List<String> wordList = new ArrayList<>(Arrays.asList(words));
        wordList.removeAll(Arrays.asList("", null));
        return wordList;
    }

    public static Map<Object, Object> wordLengths(String s) {
        return findWord(s)
                .stream()
                .collect(Collectors.groupingBy(String::length))
                .entrySet()
                .stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        n -> n.getValue().size()
                ));
    }

    public static long countWords(String s) {
        return findWord(s)
                .size();
    }

    public static boolean validateCode(String inputString) {
        final String[] inputArray = inputString.split("\\s");
        final String beforeSpace = inputArray[0];
        final String afterSpace = inputArray[1];
        IntStream numbers = beforeSpace.chars().filter(Character::isDigit);
        String numb = numbers.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        int numbInt = Integer.parseInt(afterSpace);
        int reduce = Arrays.stream(numb.split("(?<=\\G.{2})")).map(Integer::parseInt)
                .reduce(1, (multiply, value) -> multiply * value);
        return beforeSpace.chars().filter(n -> Character.isDigit(n) || (Character.isAlphabetic(n) && Character.isUpperCase(n))).count() == 20
                &&
                beforeSpace.chars().filter(Character::isDigit).count() == 6
                &&
                numbInt == reduce
                &&
                afterSpace.chars().filter(Character::isDigit).count() == 6;

    }
}

