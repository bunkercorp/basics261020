package homework_streamApi;

import java.util.LinkedHashMap;

//а где Stream API, собственно?
public class CharEntries {

    private LinkedHashMap<Integer, Integer> charEntriesMap;

    public String toString() {
        return String.format("%s", charEntriesMap);
    }

    public void charEnries(String suggestedString) {

        charEntriesMap = new LinkedHashMap<>();

        if (suggestedString == null) {
            charEntriesMap = null;
        } else {

            for (int i = 0; i < suggestedString.length(); i++) {
                Integer n = charEntriesMap.get((int) suggestedString.charAt(i));
                if (n == null) {
                    charEntriesMap.put((int) suggestedString.charAt(i), 1);
                } else {
                    charEntriesMap.put((int) suggestedString.charAt(i), ++n);
                }
            }
        }
    }
}
