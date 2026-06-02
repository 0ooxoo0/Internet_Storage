package org.skypro.skyshop.search;

import java.util.*;

public class SearchEngine {
    private List<Searchable> items = new LinkedList<>();

    public void add(Searchable item) {
        items.add(item);
    }

    public Map<String, Searchable> search(String query) {
        Map<String, Searchable> result = new TreeMap<>();
        for (Searchable item : items) {
            if (item.getSearchTerm().contains(query)) {
                result.put(item.getName(), item);
            }
        }
        return result;
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