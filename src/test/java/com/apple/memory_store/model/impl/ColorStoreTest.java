package com.apple.memory_store.model.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.apple.memory_store.exception.InvalidRangeException;
import com.apple.memory_store.model.enums.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testPriorityWithOverlappingRanges() {
        store.store("00-10", Color.BLUE); // Lower priority
        store.store("05-15", Color.YELLOW); // Higher priority

        assertEquals(Color.YELLOW, store.get("07"),
                "YELLOW should be returned as it has higher priority in the overlapping range.");
        assertEquals(Color.BLUE, store.get("02"), "BLUE should be returned as it is outside the overlapping range.");
    }

    @Test
    public void testEdgeCaseRangeOverlap() {
        store.store("00-05", Color.RED);
        store.store("05-10", Color.YELLOW);

        assertEquals(Color.YELLOW, store.get("05"), "YELLOW should be returned for the edge case overlap.");
    }

    @Test
    public void testCompletelyEncompassingRange() {
        store.store("00-10", Color.GREEN);
        store.store("03-07", Color.RED); // Completely inside the GREEN range

        assertEquals(Color.RED, store.get("05"),
                "RED should be returned as it is the highest priority in the encompassed range.");
        assertEquals(Color.GREEN, store.get("00"), "GREEN should be returned outside of the RED range.");
    }

    @Test
    public void testSequentialRangesWithoutOverlap() {
        store.store("00-05", Color.RED);
        store.store("06-10", Color.YELLOW);

        assertEquals(Color.RED, store.get("05"), "RED should be returned as it is exactly at the end of its range.");
        assertEquals(Color.YELLOW, store.get("06"), "YELLOW should be returned as it begins immediately after RED.");
    }

    @Test
    public void testInvalidRangeInput() {
        assertThrows(InvalidRangeException.class, () -> store.store("invalid-range", Color.GREEN),
                "Exception should be thrown for invalid range format.");
    }

    @Test
    public void testNonNumericInputForGet() {
        store.store("00-05", Color.BLUE);
        Exception exception = assertThrows(NumberFormatException.class, () -> store.get("abc"),
                "Expect NumberFormatException for non-numeric input.");
        assertTrue(exception instanceof NumberFormatException,
                "NumberFormatException should be thrown for non-numeric input.");
    }

    @Test
    public void testInvalidRangeFormatOnStore() {
        assertThrows(InvalidRangeException.class, () -> store.store("05", Color.RED),
                "InvalidRangeException should be thrown for missing hyphen in range.");
    }

    @Test
    public void testOverlappingRangesWithSameColor() {
        store.store("00-10", Color.BLUE);
        store.store("05-15", Color.BLUE);

        assertEquals(Color.BLUE, store.get("05"),
                "BLUE should be returned for overlapping ranges with the same color.");
    }

    @Test
    public void testNoStoredRanges() {
        assertEquals(Color.GREY, store.get("00"), "GREY should be returned when no ranges are stored.");
    }

}
