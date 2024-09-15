package com.yago.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
    BACKEND("back-end"),
    FRONTEND("front-end"),
    FULLSTACK("fullstack");

    private String value;

    private Category(String value)
    {
        this.value = value;
    }

    @JsonValue
    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
