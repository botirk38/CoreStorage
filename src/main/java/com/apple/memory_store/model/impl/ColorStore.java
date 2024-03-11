package com.apple.memory_store.model.impl;

import com.apple.memory_store.model.interfaces.MemoryStore;
import com.apple.memory_store.exception.InvalidRangeException;
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
		int[] bounds = parseAndValidateRange(range);

		String key = determineKeyForColor(bounds, range);

		store.put(key, color);
	}

	@Override
	public Color get(String entry) {
		int entryValue = Integer.parseInt(entry);
		Color color = getColorInRange(entryValue);
		if (color != null) {
			return color;
		}

		return Color.GREY;
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

	private String determineKeyForColor(int[] bounds, String range) {
		String key = range;
		for (Map.Entry<String, Color> entry : store.entrySet()) {
			int[] existingBounds = parseAndValidateRange(entry.getKey());
			if (bounds[0] <= existingBounds[1] && bounds[1] >= existingBounds[0]) {
				if (bounds[0] >= existingBounds[0] && bounds[1] <= existingBounds[1]) {
					return key;
				}
				key = entry.getKey();
				break;
			}
		}
		return key;
	}

}
