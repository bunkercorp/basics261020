package homework_streamApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordBoundary {
    public  int start;
    public  int end;

    public WordBoundary(int s, int e) {
        start = s;
        end = e;
    }

    public WordBoundary() {
    }

    public static List<WordBoundary> wordBoundaries(String src) {
        class Entry {
            public final int item;
            public final boolean isWordMember;

            public Entry(int i, boolean iwm) {
                item = i;
                isWordMember = iwm;
            }
        }

        List<Entry> mappedChars = IntStream
                .range(0, src.length())
                .mapToObj(index -> {
                    final char curr = src.charAt(index);
                    final boolean isWordMember = Character.isLetterOrDigit(curr) || curr == '`' || curr == '_' || curr == '-';
                    return new Entry(index, isWordMember);
                })
                .collect(Collectors.toList());

        return mappedChars
                .stream()
                .reduce(new ArrayList<List<Entry>>(),
                        (acc, elem) -> {
                            if (acc.isEmpty()) {
                                List<Entry> first = new ArrayList<Entry>() {{
                                    add(elem);
                                }};
                                acc.add(first);
                            } else {
                                final List<Entry> lastList = acc.get(acc.size() - 1);
                                final boolean isFlagSame = lastList.get(0).isWordMember == elem.isWordMember;
                                if (isFlagSame) {
                                    lastList.add(elem);
                                } else {
                                    List<Entry> nextList = new ArrayList<Entry>() {{
                                        add(elem);
                                    }};
                                    acc.add(nextList);
                                }
                            }
                            return acc;
                        },
                        (a, b) -> null)
                .stream()
                .filter(list -> list.get(0).isWordMember)
                .map(list -> {
                    final boolean isSingleSymbol = list.size() == 1;
                    final Entry startEntry = list.get(0);
                    if (isSingleSymbol) {
                        return new WordBoundary(startEntry.item, startEntry.item);
                    }
                    final Entry endEntry = list.get(list.size() - 1);
                    return new WordBoundary(startEntry.item, endEntry.item);
                })
                .collect(Collectors.toList());
    }
}
