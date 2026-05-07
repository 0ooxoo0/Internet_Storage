package org.skypro.skyshop.search;

public interface Searchable {

    // 1. Возвращает поисковый запрос (search term)
    String getSearchTerm();

    // 2. Возвращает тип контента (PRODUCT, ARTICLE)
    String getContentType();

    // 3. Возвращает имя объекта
    String getName();

    // 4. default-метод, дающий строковое представление
    default String getStringRepresentation() {
        return getName() + " — " + getContentType();
    }
}
