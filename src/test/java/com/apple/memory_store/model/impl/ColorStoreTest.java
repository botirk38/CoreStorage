package com.apple.memory_store.model.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.apple.memory_store.exception.RangeNotFoundException;
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
    public void testGetValid(){
        String entry = "03";


        store.store("00-06", Color.RED);

        assertEquals(Color.RED, store.get(entry));
    }

    
    


}
