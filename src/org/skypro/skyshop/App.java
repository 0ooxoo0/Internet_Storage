package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Article;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.product.Product;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Демонстрация проверок с try-catch
        System.out.println("=== Проверка невалидных данных ===");

        try {
            new SimpleProduct(null, 100);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new SimpleProduct("", 100);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new SimpleProduct("   ", 100);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new SimpleProduct("Товар", 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new SimpleProduct("Товар", -10);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new DiscountedProduct("Товар", 0, 10);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new DiscountedProduct("Товар", 100, -5);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new DiscountedProduct("Товар", 100, 101);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        // Создание продуктов и корзины
        ProductBasket basket = new ProductBasket();

        SimpleProduct apple = new SimpleProduct("Яблоко", 100);
        DiscountedProduct bread = new DiscountedProduct("Хлеб", 50, 20);
        SimpleProduct milk = new SimpleProduct("Молоко", 80);
        FixPriceProduct cheese = new FixPriceProduct("Сыр");
        DiscountedProduct juice = new DiscountedProduct("Сок", 120, 10);
        SimpleProduct chocolate = new SimpleProduct("Шоколад", 150); // будет использован только в поисковике

        basket.addBasket(apple);
        basket.addBasket(bread);
        basket.addBasket(milk);
        basket.addBasket(cheese);
        basket.addBasket(juice);

        System.out.println("\n=== Содержимое корзины ===");
        basket.printBasket();

        System.out.println("\n=== Стоимость корзины ===");
        System.out.println("Общая стоимость: " + basket.allPriceBasket());

        System.out.println("\n=== Поиск товара ===");
        System.out.println("Молоко есть в корзине? " + basket.hasProduct("Молоко"));
        System.out.println("Шоколад есть в корзине? " + basket.hasProduct("Шоколад"));

        // Демонстрация удаления продуктов по имени
        System.out.println("\n=== Удаление продуктов по имени ===");

        List<Product> removed = basket.removeProductsByName("Яблоко");
        System.out.println("Удалённые продукты:");
        if (removed.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            for (Product p : removed) {
                System.out.println(p);
            }
        }
        System.out.println("Содержимое корзины после удаления:");
        basket.printBasket();

        List<Product> removed2 = basket.removeProductsByName("Груша");
        System.out.println("Удалённые продукты (Груша):");
        if (removed2.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            for (Product p : removed2) {
                System.out.println(p);
            }
        }
        System.out.println("Содержимое корзины после попытки удаления несуществующего:");
        basket.printBasket();

        System.out.println("\n=== Очистка корзины ===");
        basket.clearBasket();

        System.out.println("\n=== Печать пустой корзины ===");
        basket.printBasket();

        System.out.println("\n=== Стоимость пустой корзины ===");
        System.out.println("Стоимость: " + basket.allPriceBasket());

        // Поисковый движок
        SearchEngine searchEngine = createSearchEngine(apple, bread, milk, cheese, juice, chocolate);

        System.out.println("\n=== Обычный поиск (сортированная мапа) ===");

        System.out.println("Поиск 'молок':");
        Map<String, Searchable> res1 = searchEngine.search("молок");
        for (Searchable s : res1.values()) {
            System.out.println(s.getStringRepresentation());
        }

        System.out.println("Поиск 'сыр':");
        Map<String, Searchable> res2 = searchEngine.search("сыр");
        for (Searchable s : res2.values()) {
            System.out.println(s.getStringRepresentation());
        }

        System.out.println("Поиск 'Java':");
        Map<String, Searchable> res3 = searchEngine.search("Java");
        for (Searchable s : res3.values()) {
            System.out.println(s.getStringRepresentation());
        }

        System.out.println("Поиск 'яблоко':");
        Map<String, Searchable> res4 = searchEngine.search("яблоко");
        for (Searchable s : res4.values()) {
            System.out.println(s.getStringRepresentation());
        }

        System.out.println("Поиск 'шоколад':");
        Map<String, Searchable> res5 = searchEngine.search("шоколад");
        for (Searchable s : res5.values()) {
            System.out.println(s.getStringRepresentation());
        }

        // Демонстрация метода searchBest
        System.out.println("\n=== Поиск наиболее подходящего элемента ===");
        try {
            Searchable best = searchEngine.searchBest("молоко");
            System.out.println("Лучший результат для 'молоко': " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        try {
            Searchable best = searchEngine.searchBest("газировка");
            System.out.println("Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    private static SearchEngine createSearchEngine(Searchable... products) {
        SearchEngine engine = new SearchEngine();

        for (Searchable product : products) {
            engine.add(product);
        }

        engine.add(new Article("Польза молока", "Молоко богато кальцием и белком"));
        engine.add(new Article("Рецепт сырников", "Для сырников нужен творог и сыр"));
        engine.add(new Article("Программирование на Java", "Java – объектно-ориентированный язык"));

        return engine;
    }
}