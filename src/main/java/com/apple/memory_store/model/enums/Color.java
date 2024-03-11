package com.apple.memory_store.model.enums;


public enum Color {

    YELLOW(1),
    RED(2),
    GREEN(3),
    BLUE(4),
    GREY(5);


    private int value;

    Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
