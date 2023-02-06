package com.software.application.data.entity;

import com.vaadin.flow.shared.util.SharedUtil;

import java.util.Locale;

public enum OrderState {

    NEW, CONFIRMED, READY, DELIVERED, PROBLEM, CANCELLED;

    public String getDisplayName() {
        return SharedUtil.capitalize (name ().toLowerCase (Locale.ENGLISH));
    }
}
