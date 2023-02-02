package com.software.application.data.entity.enums;

public enum UM {
    BUG, KG, SET;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
