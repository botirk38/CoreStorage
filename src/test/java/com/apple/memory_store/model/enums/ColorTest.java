package com.apple.memory_store.model.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class ColorTest {
	
    @Test
    public void testYellowValue() {
        assertEquals(1, Color.YELLOW.getPriority(), "The value for YELLOW should be 1.");
    }

    @Test
    public void testRedValue() {
        assertEquals(2, Color.RED.getPriority(), "The value for RED should be 2.");
    }

    @Test
    public void testGreenValue() {
        assertEquals(3, Color.GREEN.getPriority(), "The value for GREEN should be 3.");
    }

    @Test
    public void testBlueValue() {
        assertEquals(4, Color.BLUE.getPriority(), "The value for BLUE should be 4.");
    }

    @Test
    public void testGreyValue() {
        assertEquals(5, Color.GREY.getPriority(), "The value for GREY should be 5.");
    }



}
