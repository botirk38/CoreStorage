

public interface MemoryStore <K,V> {
	

	public void store(K key, V value);

	public V get(K key);



}
