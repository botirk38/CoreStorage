package com.apple.memory_store.model.impl;

import com.apple.memory_store.model.interfaces.MemoryStore;
import com.apple.memory_store.model.enums.Color;
import java.lang.UnsupportedOperationException;

public class ColorStore implements MemoryStore<String,Color> {


	@Override
	public void store(String range, Color color) {} 


	@Override
	public Color get(String range) {
		throw new UnsupportedOperation("Not supported yet");
	} 




}
