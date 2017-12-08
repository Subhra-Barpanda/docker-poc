package com.newtechnology.poc.model.constants.book;

public enum BookSubCategory {
    JAVA("java"),
    SQL("sql"),
    CRICKET("cricket"),
    FOOTBALL("football"),
    INDIAN("indian"),
    BRITISH("british"),
    AUTO("auto"),
    SPIRITUAL("spiritual");

    private String value;

    BookSubCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BookSubCategory{" +
                "value='" + value + '\'' +
                '}';
    }
}
