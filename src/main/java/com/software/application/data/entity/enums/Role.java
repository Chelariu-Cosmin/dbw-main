package com.software.application.data.entity.enums;

public enum Role {
    ADMIN ("ADMIN"), USER ("USER");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
