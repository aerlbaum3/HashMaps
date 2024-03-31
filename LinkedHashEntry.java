public class LinkedHashEntry<K,V> {

	K key;

	V value;

	LinkedHashEntry<String,Integer> next;

	public LinkedHashEntry(K key, V value) {

		this.key = key;

		this.value = value;

		this.next = null;

	}

	public K getKey() {

		return key;

	}

	public void setKey(K key) {

		this.key =  key;

	}

	public int getValue() {

		return (int) value;

	}

	public void setValue(V value) {

		this.value = value;

	}

	public LinkedHashEntry<String,Integer> getNext() {

		return next;

	}

	public void setNext(LinkedHashEntry<String, Integer> next) {

		this.next = next;

	}

}