package com.software.application.data.entity.enums;

public enum UM {
    BUG ("Buc"), KG ("Kg"), SET ("Set");

    private final String name;

    UM(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
