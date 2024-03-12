package com.apple.memory_store.model.impl;

import com.apple.memory_store.model.interfaces.MemoryStore;
import com.apple.memory_store.exception.InvalidRangeException;
import com.apple.memory_store.model.enums.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code ColorStore} class implements the {@code MemoryStore} interface,
 * providing a way to store and retrieve {@code Color} objects associated with
 * specific string ranges. The class supports storing colors for given ranges
 * and
 * retrieving the color for a specific entry within those ranges.
 * 
 * @author Botir Khaltaev
 * 
 */

public class ColorStore implements MemoryStore<String, Color> {

	private Map<String, Color> store;

	/**
	 * Constructs a new {@code ColorStore} instance with an empty internal store.
	 */
	public ColorStore() {
		store = new HashMap<>();

	}

	/**
	 * Stores a {@code Color} object associated with a specified range in the memory
	 * store.
	 * The range is a string representing numeric bounds (e.g., "10-20").
	 * If the range is invalid or overlaps with an existing range, an
	 * {@code InvalidRangeException}
	 * may be thrown.
	 *
	 * @param range the string representing the range the color is associated with
	 * @param color the {@code Color} object to be stored
	 * @throws InvalidRangeException if the range is invalid or overlaps with an
	 *                               existing range
	 */

	@Override
	public void store(String range, Color color) {
		int[] bounds = parseAndValidateRange(range);

		String key = determineKeyForColor(bounds, range);

		store.put(key, color);
	}

	/**
	 * Retrieves the {@code Color} associated with a specific entry. The entry is a
	 * string
	 * representing a numeric value. If the entry falls within the bounds of a
	 * stored range,
	 * the associated color is returned. If the entry does not fall within any
	 * stored ranges,
	 * {@code Color.GREY} is returned by default.
	 *
	 * @param entry the entry for which to retrieve the associated color
	 * @return the {@code Color} associated with the specified entry or
	 *         {@code Color.GREY} if
	 *         the entry does not fall within any stored ranges
	 */

	@Override
	public Color get(String entry) {
		int entryValue = Integer.parseInt(entry);
		Color color = getColorInRange(entryValue);
		if (color != null) {
			return color;
		}

		return Color.GREY;
	}

	/**
	 * Retrieves the color associated with a specific numeric entry value.
	 *
	 * @param entryValue the numeric value of the entry
	 * @return the {@code Color} associated with the specified entry value or
	 *         {@code null} if
	 *         the entry does not fall within any stored ranges
	 */

	 private Color getColorInRange(int entryValue) {
		Color highestPriorityColor = null;
		for (Map.Entry<String, Color> rangeColorEntry : store.entrySet()) {
			int[] bounds = parseAndValidateRange(rangeColorEntry.getKey());
			int lowerBound = bounds[0];
			int upperBound = bounds[1];
			if (entryValue >= lowerBound && entryValue <= upperBound) {
				Color currentColor = rangeColorEntry.getValue();
				if (highestPriorityColor == null || currentColor.ordinal() < highestPriorityColor.ordinal()) {
					highestPriorityColor = currentColor;
				}
			}
		}
		return highestPriorityColor;
	}
	

	/**
	 * Parses and validates a range string, returning the numeric bounds of the
	 * range.
	 * Throws an {@code InvalidRangeException} if the range is invalid.
	 *
	 * @param range the range string to parse and validate
	 * @return an array of two integers representing the lower and upper bounds of
	 *         the range
	 * @throws InvalidRangeException if the range is invalid
	 */

	private int[] parseAndValidateRange(String range) {
		String[] bounds = range.split("-");
		int lowerBound = Integer.parseInt(bounds[0]);
		int upperBound = Integer.parseInt(bounds[1]);
		if (lowerBound > upperBound) {
			throw new InvalidRangeException("Invalid range: Please put a valid range ex. 03-06 " + range);
		}
		return new int[] { lowerBound, upperBound };
	}

	/**
	 * Determines the key for storing a color based on the provided bounds and
	 * range.
	 * This method ensures that colors are stored without overlapping ranges.
	 *
	 * @param bounds an array of two integers representing the lower and upper
	 *               bounds of the range
	 * @param range  the range string to use as the key if no overlaps are found
	 * @return the key under which the color should be stored
	 */

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
