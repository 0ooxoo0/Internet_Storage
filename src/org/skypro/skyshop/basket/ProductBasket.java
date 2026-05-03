package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {

    private Product[] basket = new Product[5];

    public void addBasket(Product product) { // Метод добавления продукта в корзину
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

    public int allPriceBasket() // Метод получения общей стоимости корзины
    {
        int allPrice = 0;
        int i = 0;
        while (i < basket.length) {

            if (basket[i] != null) {
                allPrice += basket[i].getPrice();
            }
            i++;
        }
        if (allPrice <= 0)
            System.out.println("в корзине пусто");
        return allPrice;
    }

    public void printBasket() {
        if (allPriceBasket() == 0) {
            System.out.println("В корзине пусто");
            return;
        }
        int i = 0;
        while (i < basket.length) {
            if (basket[i] != null)
                System.out.println(basket[i].getName() + ": " + basket[i].getPrice());
            i++;
        }
        System.out.println("Итого: " + allPriceBasket());
    }

    public boolean hasProduct(String Name) // Метод, проверяющий продукт в корзине по имени
    {
        int i = 0;
        while (i < basket.length) {
            if (basket[i] != null) {
                if (basket[i].getName().equals(Name)   )
                    return true;
            }
            i++;
        }
        return false;
    }

    public void clearBasket() // Метод очистки корзины
    {
        int i = 0;
        while (i < basket.length) {
            basket[i] = null;
            i++;
        }
        //_basket = new BasketItem[5];
    }

//    private class BasketItem {
//        public String name;
//        public int price;
//    }
}
