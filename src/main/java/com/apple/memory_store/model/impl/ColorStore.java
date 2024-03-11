package com.apple.memory_store.model.impl;

import com.apple.memory_store.model.interfaces.MemoryStore;
import com.apple.memory_store.exception.InvalidRangeException;
import com.apple.memory_store.exception.RangeNotFoundException;
import com.apple.memory_store.model.enums.Color;
import java.util.HashMap;
import java.util.Map;

public class ColorStore implements MemoryStore<String, Color> {

	private Map<String, Color> store;

	public ColorStore() {
		store = new HashMap<>();

	}

	@Override
	public void store(String range, Color color) {
		parseAndValidateRange(range);
		store.put(range, color);
	}

	@Override
	public Color get(String entry) {
		int entryValue = Integer.parseInt(entry);
		Color color = getColorInRange(entryValue);
		if (color != null) {
			return color;
		}

		throw new RangeNotFoundException(entry + " not found in store");
	}

	private Color getColorInRange(int entryValue) {
		for (Map.Entry<String, Color> rangeColorEntry : store.entrySet()) {
			int[] bounds = parseAndValidateRange(rangeColorEntry.getKey());
			int lowerBound = bounds[0];
			int upperBound = bounds[1];
			if (entryValue >= lowerBound && entryValue <= upperBound) {
				return rangeColorEntry.getValue();
			}
		}
		return null;
	}

	private int[] parseAndValidateRange(String range) {
		String[] bounds = range.split("-");
		int lowerBound = Integer.parseInt(bounds[0]);
		int upperBound = Integer.parseInt(bounds[1]);
		if (lowerBound > upperBound) {
			throw new InvalidRangeException("Invalid range: " + range);
		}
		return new int[] { lowerBound, upperBound };
	}

}
