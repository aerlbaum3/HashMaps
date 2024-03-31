public class MapEntry<K, V> {
	
	protected K key;
	protected V value;
	private MapEntry<K, V> next;

	MapEntry(K k, V v) {
		key = k;
		value = v;
		next = null;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V v) {

		value = v;

	}

	@Override
	public String toString()
	// Returns a string representing this MapEntry.
	{
		return "Key  : " + key + "\nValue: " + value;
	}
}