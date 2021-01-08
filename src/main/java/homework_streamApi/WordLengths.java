package homework_streamApi;

import java.util.HashMap;
import java.util.List;

public class WordLengths extends WordBoundary {

    private HashMap<Integer, Integer> wordsMap;

    public String toString() {
        return String.format("%s", wordsMap);
    }

    public void wordLengths(String suggestedString) {

        wordsMap = new HashMap<>();

        if (suggestedString == null) {
            wordsMap = null;
        } else {

            List<WordBoundary> d = wordBoundaries(suggestedString);

            for (WordBoundary word : d) {
                int wordLenght = word.end + 1 - word.start;

                if (wordsMap.containsKey(wordLenght)) {
                    int value = wordsMap.get(wordLenght);
                    wordsMap.put(wordLenght, ++value);
                } else {
                    wordsMap.put(wordLenght, 1);
                }
            }
        }
    }
}

