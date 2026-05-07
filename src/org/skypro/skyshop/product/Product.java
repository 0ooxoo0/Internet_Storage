package org.skypro.skyshop.product;
import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String _name) {
        if (_name == null || _name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым или состоять только из пробелов");
        }

        this.name = Name;
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