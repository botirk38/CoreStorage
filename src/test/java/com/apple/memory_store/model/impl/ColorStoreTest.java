package com.apple.memory_store.model.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.apple.memory_store.exception.InvalidRangeException;
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

        assertEquals(Color.RED, store.get("03"));

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

        assertEquals(expectedRed, store.get("02"));

        assertEquals(expectedBlue, store.get("07"));
    }

    @Test
    public void testStoreWithInvalidRange() {
        String range = "06-00";
        Color expectedColor = Color.RED;

        assertThrows(InvalidRangeException.class, () -> store.store(range, expectedColor));
    }

    @Test
    public void testStoreInvalidRange() {

        assertThrows(InvalidRangeException.class, () -> store.store("06-00", Color.RED));
    }

    @Test
    public void testStoreOneColorMultipleRanges() {
        String range1 = "00-06";
        Color expectedColor = Color.RED;
        store.store(range1, expectedColor);

        String range2 = "07-12";
        store.store(range2, expectedColor);

        assertEquals(Color.RED, store.get("03"));
        assertEquals(Color.RED, store.get("10"));
    }

    @Test
    public void testOverlappingRanges() {
        store.store("00-13", Color.RED);

        store.store("00-10", Color.YELLOW);

        assertEquals(Color.YELLOW, store.get("05"));
        assertEquals(Color.RED, store.get("11"));

    }

    @Test
    public void testOverlappingRanges2() {
        store.store("00-11", Color.RED);

        store.store("05-10", Color.YELLOW);

        assertEquals(Color.RED, store.get("11"));
        assertEquals(Color.YELLOW, store.get("10"));

    }

    @Test
    public void testGetValid() {
        String entry = "03";

        store.store("00-06", Color.RED);

        assertEquals(Color.RED, store.get(entry));
    }

    @Test
    public void testGetRangeNotStored() {
        String entry = "03";

        assertEquals(Color.GREY, store.get(entry));
    }

}
