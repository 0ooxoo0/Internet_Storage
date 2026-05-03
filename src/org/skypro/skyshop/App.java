package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;

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
    }
}