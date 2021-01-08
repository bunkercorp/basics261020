package homework_streamApi;

import java.util.List;

public class Beautify extends WordBoundary {

    public enum Type {
        UPPERCASE,
        LOWERCASE,
        CAPITALIZE
    }

    public String formattedString;

    public String toString() {
        return String.format("%s", formattedString);
    }

    public void beautify(String suggestedString, Type type) {

        if ((suggestedString == null) || (type == null)) {
            formattedString = null;
        } else {

            switch (type) {

                case UPPERCASE:
                    formattedString = suggestedString.toUpperCase().trim();
                    break;
                case LOWERCASE:
                    formattedString = suggestedString.toLowerCase().trim();
                    break;
                case CAPITALIZE:
                    List<WordBoundary> e = wordBoundaries(suggestedString);
                    for (WordBoundary i : e) {
                        suggestedString = suggestedString.substring(0, i.start).concat(suggestedString.substring(i.start, i.start + 1).toUpperCase()).concat(suggestedString.substring(i.start + 1));
                    }
                    formattedString = suggestedString.trim();
                    break;
                default:
                    formattedString = suggestedString;
            }
        }
    }
}
