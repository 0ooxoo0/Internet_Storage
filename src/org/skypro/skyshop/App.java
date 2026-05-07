package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Article;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.SearchEngine;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();

        SimpleProduct apple = new SimpleProduct("Яблоко", 100);
        DiscountedProduct bread = new DiscountedProduct("Хлеб", 50, 20);
        SimpleProduct milk = new SimpleProduct("Молоко", 80);
        FixPriceProduct cheese = new FixPriceProduct("Сыр");
        DiscountedProduct juice = new DiscountedProduct("Сок", 120, 10);

        basket.addBasket(apple);
        basket.addBasket(bread);
        basket.addBasket(milk);
        basket.addBasket(cheese);
        basket.addBasket(juice);

        System.out.println("=== Попытка добавить в полную корзину ===");
        SimpleProduct chocolate = new SimpleProduct("Шоколад", 150);
        basket.addBasket(chocolate);

        System.out.println("\n=== Содержимое корзины ===");
        basket.printBasket();

        System.out.println("\n=== Стоимость корзины ===");
        System.out.println("Общая стоимость: " + basket.allPriceBasket());

        System.out.println("\n=== Поиск товара ===");
        System.out.println("Молоко есть в корзине? " + basket.hasProduct("Молоко"));
        System.out.println("Шоколад есть в корзине? " + basket.hasProduct("Шоколад"));

        System.out.println("\n=== Очистка корзины ===");
        basket.clearBasket();

        System.out.println("\n=== Печать пустой корзины ===");
        basket.printBasket();

        System.out.println("\n=== Стоимость пустой корзины ===");
        System.out.println("Стоимость: " + basket.allPriceBasket());

        ////////////////////////////////////

        SearchEngine searchEngine = createSearchEngine(apple, bread, milk, cheese, juice, chocolate);

        System.out.println("\n=== Поиск: 'молок' ===");
        Searchable[] res1 = searchEngine.search("молок");
        System.out.println(Arrays.toString(res1));

        System.out.println("\n=== Поиск: 'сыр' ===");
        Searchable[] res2 = searchEngine.search("сыр");
        System.out.println(Arrays.toString(res2));

        System.out.println("\n=== Поиск: 'Java' ===");
        Searchable[] res3 = searchEngine.search("Java");
        System.out.println(Arrays.toString(res3));

        System.out.println("\n=== Поиск: 'яблоко' ===");
        Searchable[] res4 = searchEngine.search("яблоко");
        System.out.println(Arrays.toString(res4));

        System.out.println("\n=== Поиск: 'шоколад' ===");
        Searchable[] res5 = searchEngine.search("шоколад");
        System.out.println(Arrays.toString(res5));
    }

    private static SearchEngine createSearchEngine(Searchable... products) {
        SearchEngine engine = new SearchEngine(20);

        for (Searchable product : products) {
            engine.add(product);
        }

        engine.add(new Article("Польза молока", "Молоко богато кальцием и белком"));
        engine.add(new Article("Рецепт сырников", "Для сырников нужен творог и сыр"));
        engine.add(new Article("Программирование на Java", "Java – объектно-ориентированный язык"));

        return engine;
    }
}