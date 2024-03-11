package com.apple.memory_store.model.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.apple.memory_store.model.enums.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ColorStoreTest {

    ColorStore store;

    @BeforeEach
    public void setup() {

        store = new ColorStore();
    }

    @Test
    public void testStore() {

        String range = "00-06";
        Color expectedColor = Color.RED;

        store.store(range, expectedColor);

        assertEquals(Color.RED, store.get(range));

    }

    @Test
    public void testStoreAndRetrieveDifferentColors() {
        ColorStore store = new ColorStore();

        String rangeRed = "00-06";
        Color expectedRed = Color.RED;
        store.store(rangeRed, expectedRed);

        String rangeBlue = "07-12";
        Color expectedBlue = Color.BLUE;
        store.store(rangeBlue, expectedBlue);

        assertEquals(expectedRed, store.get(rangeRed));

        assertEquals(expectedBlue, store.get(rangeBlue));
    }

    @Test
    public void testGet() {
        String range = "00-06";
        Color expectedColor = Color.RED;

        store.store(range, expectedColor);

        assertEquals(expectedColor, store.get(range));

    }

    @Test
    public void testGetWithNoValue() {
        String range = "00-06";

        assertThrows(NullPointerException.class, () -> store.get(range));
    }

}
