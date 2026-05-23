package org.skypro.skyshop.search;

import java.util.LinkedList;
import java.util.List;

public class SearchEngine {
    private List<Searchable> items = new LinkedList<>(); // LinkedList согласно критериям

    public void add(Searchable item) {
        items.add(item);
    }

    /**
     * Поиск всех объектов, у которых searchTerm содержит подстроку query.
     * @return список всех подходящих Searchable (может быть пустым)
     */
    public List<Searchable> search(String query) {
        List<Searchable> results = new LinkedList<>();
        for (Searchable item : items) {
            if (item.getSearchTerm().contains(query)) {
                results.add(item);
            }
        }
        return results;
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