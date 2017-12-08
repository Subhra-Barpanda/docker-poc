package com.newtechnology.poc.model.constants.user;

public enum UserRole {
    STUDENT("student"),
    ADMIN("admin");

    private String value;

    public String getValue() {
        return value;
    }

    UserRole(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "value='" + value + '\'' +
                '}';
    }
}
