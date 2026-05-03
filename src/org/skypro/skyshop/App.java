package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();

        // Создаём SimpleProduct вместо Product
        SimpleProduct apple = new SimpleProduct("Яблоко", 100);
        SimpleProduct bread = new SimpleProduct("Хлеб", 50);
        SimpleProduct milk = new SimpleProduct("Молоко", 80);
        SimpleProduct cheese = new SimpleProduct("Сыр", 200);
        SimpleProduct juice = new SimpleProduct("Сок", 120);
        SimpleProduct chocolate = new SimpleProduct("Шоколад", 150);

        // Добавление продуктов
        basket.addBasket(apple);
        basket.addBasket(bread);
        basket.addBasket(milk);
        basket.addBasket(cheese);
        basket.addBasket(juice);

        // Попытка добавить в полную корзину
        System.out.println("=== Попытка добавить в полную корзину ===");
        basket.addBasket(chocolate);

        // Печать содержимого
        System.out.println("\n=== Содержимое корзины ===");
        basket.printBasket();

        // Стоимость корзины
        System.out.println("\n=== Стоимость корзины ===");
        System.out.println("Общая стоимость: " + basket.allPriceBasket());

        // Поиск товара
        System.out.println("\n=== Поиск товара ===");
        System.out.println("Молоко есть в корзине? " + basket.hasProduct("Молоко"));
        System.out.println("Шоколад есть в корзине? " + basket.hasProduct("Шоколад"));

        // Очистка корзины
        System.out.println("\n=== Очистка корзины ===");
        basket.clearBasket();

        // Печать пустой корзины
        System.out.println("\n=== Печать пустой корзины ===");
        basket.printBasket();

        // Стоимость пустой корзины
        System.out.println("\n=== Стоимость пустой корзины ===");
        System.out.println("Стоимость: " + basket.allPriceBasket());

        // Поиск в пустой корзине
        System.out.println("\n=== Поиск в пустой корзине ===");
        System.out.println("Яблоко есть в корзине? " + basket.hasProduct("Яблоко"));
    }
}