package com.apple.memory_store.model.enums;
/**
 * Enum representing colors with associated values.
 * 
 * @author Botir Khaltaev
 */

 public enum Color {

    YELLOW(1),
    RED(2),
    GREEN(3),
    BLUE(4),
    GREY(5);

    private int priority;

    /**
     * Constructor for the Color enum.
     *
     * @param priority the value associated with the color
     */
    Color(int priority) {
        this.priority = priority;
    }

    /**
     * Gets the priority associated with the color.
     *
     * @return the priority of the color
     */
    public int getPriority() {
        return priority;
    }
}