package com.apple.memory_store.model.impl;

import com.apple.memory_store.model.interfaces.MemoryStore;
import com.apple.memory_store.model.enums.Color;
import java.lang.UnsupportedOperationException;
import java.util.HashMap;
import java.util.Map;

public class ColorStore implements MemoryStore<String,Color> {


	private Map<String, Color> store;

	public ColorStore() {
		store = new HashMap<>();

	}


	@Override
	public void store(String range, Color color) {} 


	@Override
	public Color get(String range) {
		return Color.RED;
	} 




}
