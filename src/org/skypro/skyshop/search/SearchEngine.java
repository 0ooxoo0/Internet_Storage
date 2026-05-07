package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] items;   // хранилище
    private int size;                   // текущее количество добавленных объектов

    // Конструктор принимает размер хранилища
    public SearchEngine(int capacity) {
        items = new Searchable[capacity];
        size = 0;
    }

    // Добавление объекта в массив
    public void add(Searchable item) {
        if (size < items.length) {
            items[size] = item;
            size++;
        } // можно добавить обработку переполнения
    }

    // Поиск: возвращает массив до 5 первых подходящих элементов
    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5]; // фиксированный размер
        int found = 0;

        for (int i = 0; i < size; i++) {
            Searchable item = items[i];
            // проверяем, содержит ли searchTerm искомую подстроку
            if (item.getSearchTerm().contains(query)) {
                results[found] = item;
                found++;
                if (found == 5) {
                    break;
                }
            }
        }
        // если найдено меньше 5, остальные элементы останутся null – это допустимо
        return results;
    }
}