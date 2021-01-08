package homework_streamApi;

import java.util.List;

public class CountWords extends WordBoundary {

    public int numberOfWords;

    public String toString() {
        return String.format("%d", numberOfWords);
    }

    public void numberOfWords(String suggestedString) {
        if (suggestedString == null){
            numberOfWords = -1;
        } else {
            List<WordBoundary> d = wordBoundaries(suggestedString);
            numberOfWords = d.size();
        }
    }
}
