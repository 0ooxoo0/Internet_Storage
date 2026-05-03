package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();

        // 1. Добавление продуктов
        Product apple = new Product("Яблоко", 100);
        Product bread = new Product("Хлеб", 50);
        Product milk = new Product("Молоко", 80);
        Product cheese = new Product("Сыр", 200);
        Product juice = new Product("Сок", 120);
        Product chocolate = new Product("Шоколад", 150);

        basket.addBasket(apple);
        basket.addBasket(bread);
        basket.addBasket(milk);
        basket.addBasket(cheese);
        basket.addBasket(juice);

        // 2. Добавление в заполненную корзину
        System.out.println("=== Попытка добавить в полную корзину ===");
        basket.addBasket(chocolate);

        // 3. Печать содержимого
        System.out.println("\n=== Содержимое корзины ===");
        basket.printBasket();

        // 4. Получение стоимости
        System.out.println("\n=== Стоимость корзины ===");
        System.out.println("Общая стоимость: " + basket.allPriceBasket());

        // 5. Поиск товара, который есть
        System.out.println("\n=== Поиск товара ===");
        System.out.println("Молоко есть в корзине? " + basket.hasProduct("Молоко"));

        // 6. Поиск товара, которого нет
        System.out.println("Шоколад есть в корзине? " + basket.hasProduct("Шоколад"));

        // 7. Очистка корзины
        System.out.println("\n=== Очистка корзины ===");
        basket.clearBasket();

        // 8. Печать пустой корзины
        System.out.println("\n=== Печать пустой корзины ===");
        basket.printBasket();

        // 9. Стоимость пустой корзины
        System.out.println("\n=== Стоимость пустой корзины ===");
        System.out.println("Стоимость: " + basket.allPriceBasket());

        // 10. Поиск в пустой корзине
        System.out.println("\n=== Поиск в пустой корзине ===");
        System.out.println("Яблоко есть в корзине? " + basket.hasProduct("Яблоко"));
    }
}