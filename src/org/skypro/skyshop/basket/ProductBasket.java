package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> basket = new HashMap<>();

    public void addBasket(Product product) {
        basket.computeIfAbsent(product.getName(), k -> new LinkedList<>()).add(product);
    }

    public List<Product> removeProductsByName(String name) {
        List<Product> removed = basket.remove(name);
        return removed != null ? removed : new LinkedList<>();
    }

    public int allPriceBasket() {
        int total = basket.values().stream()
                .flatMap(Collection::stream)      // из списков в плоский поток товаров
                .mapToInt(Product::getPrice)
                .sum();
        if (total == 0) {
            System.out.println("в корзине пусто");
        }
        return total;
    }

    public void printBasket() {
        if (basket.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }
        basket.values().stream()
                .flatMap(Collection::stream)
                .forEach(System.out::println);
        System.out.println("Итого: " + allPriceBasket());
        System.out.println("Специальных товаров: " + getSpecialCount());
    }

    private long getSpecialCount() {
        return basket.values().stream()
                .flatMap(Collection::stream)
                .filter(Product::isSpecial)
                .count();
    }

    public boolean hasProduct(String name) {
        return basket.containsKey(name);
    }

    public void clearBasket() {
        basket.clear();
    }
}