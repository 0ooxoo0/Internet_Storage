package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] items;
    private int size;

    public SearchEngine(int capacity) {
        items = new Searchable[capacity];
        size = 0;
    }

    public void add(Searchable item) {
        if (size < items.length) {
            items[size] = item;
            size++;
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int found = 0;

        for (int i = 0; i < size; i++) {
            Searchable item = items[i];
            if (item != null && item.getSearchTerm().contains(query)) {
                results[found] = item;
                found++;
                if (found == 5) {
                    break;
                }
            }
        }
        return results;
    }

    public Searchable searchBest(String query) throws BestResultNotFound {
        Searchable best = null;
        int maxCount = 0;

        for (int i = 0; i < size; i++) {
            Searchable item = items[i];
            if (item == null) continue;

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
            if (foundIndex == -1) {
                break;
            }
            count++;
            index = foundIndex + subLength;
        }
        return count;
    }
}