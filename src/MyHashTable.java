import java.util.Objects;

public class MyHashTable<K, V> {
    private static class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = createChainArray(M);
    }

    public MyHashTable(int M) {
        if (M <= 0) {
            throw new IllegalArgumentException("Number of chains must be positive");
        }
        this.M = M;
        chainArray = createChainArray(M);
    }

    @SuppressWarnings("unchecked")
    private HashNode<K, V>[] createChainArray(int length) {
        return (HashNode<K, V>[]) new HashNode[length];
    }

    private int hash(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        return Math.floorMod(key.hashCode(), M);
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> previous = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (previous == null) {
                    chainArray[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }

            previous = current;
            current = current.next;
        }

        return null;
    }

    public boolean contains(V value) {
        for (HashNode<K, V> head : chainArray) {
            HashNode<K, V> current = head;
            while (current != null) {
                if (Objects.equals(current.value, value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (HashNode<K, V> head : chainArray) {
            HashNode<K, V> current = head;
            while (current != null) {
                if (Objects.equals(current.value, value)) {
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public int bucketCount(int index) {
        if (index < 0 || index >= M) {
            throw new IndexOutOfBoundsException("Bucket index out of range");
        }

        int count = 0;
        HashNode<K, V> current = chainArray[index];
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void printBucketSizes() {
        for (int i = 0; i < M; i++) {
            System.out.println("Bucket " + i + ": " + bucketCount(i));
        }
    }
}
