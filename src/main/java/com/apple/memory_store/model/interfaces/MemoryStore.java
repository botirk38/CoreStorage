package com.apple.memory_store.model.interfaces;

/**
 * The {@code MemoryStore} interface defines the basic operations for storing and
 * retrieving objects in memory based on key-value pairs. It is a generic interface
 * that can be implemented by any class aiming to provide in-memory storage capabilities.
 *
 * @param <K> the type of keys maintained by this memory store
 * @param <V> the type of mapped values
 * 
 * @author Botir Khaltaev
 */
public interface MemoryStore<K, V> {

    /**
     * Stores the specified value with the specified key in the memory store. If the memory store
     * previously contained a mapping for the key, the old value is replaced by the specified value.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    void store(K key, V value);

    /**
     * Returns the value to which the specified key is mapped, or {@code null} if this memory store
     * contains no mapping for the key.
     *
     * <p>A return value of {@code null} does not necessarily indicate that the memory store
     * contains no mapping for the key; it's also possible that the memory store explicitly maps
     * the key to {@code null}. The {@code containsKey} method may be used to distinguish these
     * two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or {@code null} if this memory store
     * contains no mapping for the key
     */
    V get(K key);
}
