package org.skypro.skyshop.search;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> items = new HashSet<>();

    public void add(Searchable item) {
        items.add(item);   // дубликаты по имени не добавятся благодаря equals/hashCode
    }

    /**
     * Поиск объектов, у которых searchTerm содержит подстроку query.
     * @return TreeSet, отсортированный по длине имени по убыванию,
     *         а при равной длине — в алфавитном порядке.
     */
    public Set<Searchable> search(String query) {
        // Компаратор: сначала сравнение длин (по убыванию), затем естественный порядок имён
        Comparator<Searchable> comparator = Comparator
                .comparingInt((Searchable s) -> s.getName().length())
                .reversed()
                .thenComparing(Searchable::getName);

        return items.stream()
                .filter(item -> item.getSearchTerm().contains(query))
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }

    public Searchable searchBest(String query) throws BestResultNotFound {
        Searchable best = null;
        int maxCount = 0;
        for (Searchable item : items) {
            int count = countOccurrences(item.getSearchTerm(), query);
            if (count > maxCount) {
                maxCount = count;
                best = item;
            }
        }
        if (best == null || maxCount == 0) {
            throw new BestResultNotFound(query);
        }
        return best;
    }

    private int countOccurrences(String str, String substring) {
        int count = 0;
        int index = 0;
        int subLength = substring.length();
        while (true) {
            int foundIndex = str.indexOf(substring, index);
            if (foundIndex == -1) break;
            count++;
            index = foundIndex + subLength;
        }
        return count;
    }
}