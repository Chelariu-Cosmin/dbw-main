package com.software.application.data.entity.enums;

public enum StatusStock {

    NEW, IN_STOCK, PREMIUM, ISSUES;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
