package com.software.application.data.entity.enums;

public enum StatusOrder {

    NEW, IN_PROCESS, COMPLETE;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
