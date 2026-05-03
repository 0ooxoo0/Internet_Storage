package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private Product[] basket = new Product[5];

    public void addBasket(Product product) {
        int i = 0;
        while (i < basket.length) {
            if (basket[i] == null) {
                basket[i] = product;
                return;
            }
            i++;
        }
        System.out.println("Невозможно добавить продукт");
    }

    public int allPriceBasket() {
        int allPrice = 0;
        for (Product product : basket) {
            if (product != null) {
                allPrice += product.getPrice();
            }
        }
        if (allPrice <= 0) {
            System.out.println("в корзине пусто");
        }
        return allPrice;
    }

    public void printBasket() {
        if (allPriceBasket() == 0) {
            System.out.println("В корзине пусто");
            return;
        }
        int specialCount = 0;
        for (Product product : basket) {
            if (product != null) {
                System.out.println(product); // используется toString()
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }
        System.out.println("Итого: " + allPriceBasket());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean hasProduct(String name) {
        for (Product product : basket) {
            if (product != null && product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        for (int i = 0; i < basket.length; i++) {
            basket[i] = null;
        }
    }
}