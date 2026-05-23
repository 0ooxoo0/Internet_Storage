package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class ProductBasket {
    private List<Product> basket = new LinkedList<>();   // LinkedList согласно критериям

    public void addBasket(Product product) {
        basket.add(product);
    }

    /**
     * Удаляет из корзины все продукты с заданным именем.
     * @param name имя продукта для удаления
     * @return список удалённых продуктов (может быть пустым)
     */
    public List<Product> removeProductsByName(String name) {
        List<Product> removed = new LinkedList<>();
        Iterator<Product> iterator = basket.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equals(name)) {
                removed.add(product);
                iterator.remove();
            }
        }
        return removed;
    }

    // Остальные методы (allPriceBasket, printBasket, hasProduct, clearBasket) – как в предыдущем ответе,
    // с учётом перехода на LinkedList (они не зависят от конкретной реализации List).
    // Например:
    public int allPriceBasket() {
        int allPrice = 0;
        for (Product product : basket) {
            allPrice += product.getPrice();
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
        for (Product product : basket) {
            System.out.println(product);
            if (product.isSpecial()) {
                specialCount++;
            }
        }
        System.out.println("Итого: " + allPriceBasket());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean hasProduct(String name) {
        for (Product product : basket) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        basket.clear();
    }
}