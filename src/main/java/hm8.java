import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class hm8 {

        public static String beautify(String str, String beautifier) {

            final boolean comparison1 = str == null || str.isEmpty();
            final boolean comparison2 = beautifier == null || beautifier.isEmpty();
            if  (comparison1 || comparison2)
                return null;


            return Arrays.stream(str.split("(?=[^\\w\\d-_`])|(?<=[^\\w\\d-_`])"))
                    .map(word -> {
                        switch (beautifier) {
                            case "UPPERCASE":
                                return word.toUpperCase();

                            case "LOWERCASE":
                                return word.toLowerCase();

                            case "CAPITALIZE":
                                if(word == null || word.isEmpty()) {
                                    return word;
                                }
                                return word.substring(0, 1).toUpperCase() + word.substring(1);
                            default:
                                return "";
                        }
                    }).reduce((accumulator, word) -> accumulator + word).orElse(null);
        }

        public static Map<Character, Integer> charEntries(String str){
            if (str == null || str.isEmpty())
                return Collections.emptyMap();

            return str.chars().mapToObj(ch -> (char) ch).collect(Collectors.toMap(keySymb -> keySymb, keYValue -> 1, Integer::sum));


        }

        public static int countWords(String words){
            if (words == null || words.isEmpty())
                return 0;

            return (int)Arrays.stream(words.split("[^\\w\\d-_`]+"))
                    .filter(s -> !s.isEmpty())
                    .count();
        }

        public static Map<Integer, Integer> wordLengths(String str) {
            if (str == null || str.isEmpty())
                return Collections.emptyMap();

            return Arrays.stream(str.split("[^\\w\\d-_`]+"))
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toMap(String::length, value -> 1, Integer::sum));

        }

        public static boolean validateCode(String code){
            if (code == null || code == "")
                return false;

            String[] part = code.split(" ");
            int[] number = codeNumbers(part[0]);
            int target = Integer.parseInt(part[1]);
            return line(number) == target;
        }

        private static int [] codeNumbers(String code){
            String numbers = code.replaceAll("\\D+", "");
            int[] number = new int[numbers.length() / 2];
            for (int i = 0; i < numbers.length(); i += 2) {
                number [i/ 2] = Integer.parseInt(numbers.substring(i, i+2));
            }
            return number;
        }

        private static int line(int[] number) {
            int result = 1;
            for (int num : number) {
                result *= num;
            }
            return result;
        }

    }



