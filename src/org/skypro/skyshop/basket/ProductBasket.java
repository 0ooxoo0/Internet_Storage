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
        int allPrice = 0;
        for (List<Product> productList : basket.values()) {
            for (Product product : productList) {
                allPrice += product.getPrice();
            }
        }
        if (allPrice == 0) {
            System.out.println("в корзине пусто");
        }
        return allPrice;
    }

    public void printBasket() {
        if (basket.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }
        int specialCount = 0;
        for (List<Product> productList : basket.values()) {
            for (Product product : productList) {
                System.out.println(product);
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }
        System.out.println("Итого: " + allPriceBasket());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean hasProduct(String name) {
        return basket.containsKey(name);
    }

    public void clearBasket() {
        basket.clear();
    }
}