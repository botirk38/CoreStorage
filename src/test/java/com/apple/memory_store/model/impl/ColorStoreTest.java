package com.apple.memory_store.model.impl;

import com.apple.memory_store.model.enums.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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

	public void testGet() {
		
		

	}








}
