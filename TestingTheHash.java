import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

class TestingTheHash {

	@Test
	void testSophisticatedHash() {
		Scanner k = new Scanner(System.in);
		HashTable sophie = new HashTable(1000,HashFunction.SOPHISTICATED) ;
		Main.readFileAndCountWords(sophie);
		Main.viewWordCount(k, sophie);
		
	}
	@Test
	void testNaiveHash() {
		Scanner keyboard = new Scanner(System.in);
		
		HashTable naiveHash = new HashTable(1000,HashFunction.NAIVE);
		Main.readFileAndCountWords(naiveHash);
		Main.viewWordCount(keyboard, naiveHash);
	}
	@Test
    void testPutWithEmptyTable() {
        HashTable hashTable = new HashTable(10, HashFunction.NAIVE);
        assertNull(hashTable.get("key1"));
        hashTable.put("key1", 1);
        assertEquals(1, hashTable.size());
        assertEquals(1, hashTable.get("key1"));
    }

    @Test
    void testPutWithExistingKey() {
        HashTable hashTable = new HashTable(10, HashFunction.NAIVE);
        hashTable.put("key1", 1);
        assertEquals(1, hashTable.get("key1"));
        hashTable.put("key1", 2); // Overwrite existing value
        assertEquals(1, hashTable.size()); // Size should remain same
        assertEquals(2, hashTable.get("key1")); // Value should be updated
    }



    @Test
    void testPutWithNullKey() {
        HashTable hashTable = new HashTable(10, HashFunction.NAIVE);
        assertNull(hashTable.get(null));
        hashTable.put(null, 0);
        assertEquals(1, hashTable.size());
        assertEquals(0, hashTable.get(null));
    }
    @Test
    void testRemoveExistingKey() {
        HashTable hashTable = new HashTable(10, HashFunction.NAIVE);
        hashTable.put("key1", 1);
        assertEquals(1, hashTable.size());
        assertEquals(1, hashTable.get("key1"));
        assertEquals(1, hashTable.remove("key1"));
        assertNull(hashTable.get("key1"));
        assertEquals(0, hashTable.size());
    }

    @Test
    void testRemoveNonExistingKey() {
        HashTable hashTable = new HashTable(10, HashFunction.NAIVE);
        assertNull(hashTable.get("nonexistent"));
        assertNull(hashTable.remove("nonexistent"));
    }

    @Test
    void testRemoveNullKey() {
        HashTable hashTable = new HashTable(10, HashFunction.NAIVE);
        hashTable.put(null, 0);
        assertEquals(1, hashTable.size());
        assertEquals(0, hashTable.get(null));
        assertEquals(0, hashTable.remove(null));
        assertNull(hashTable.get(null));
        assertEquals(0, hashTable.size());
    }
}
