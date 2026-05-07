package org.skypro.skyshop.product;
import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        this.name = name;
    }
    @Override
    public String getSearchTerm() {
        return name; // ищем по имени товара
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }
    @Override
    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();
}