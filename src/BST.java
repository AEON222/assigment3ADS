import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left;
        private Node right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public static class Entry<K, V> {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public void put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }

        return node;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        }

        return null;
    }

    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            size--;

            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }

            Node replacement = min(node.right);
            replacement.right = deleteMin(node.right);
            replacement.left = node.left;
            return replacement;
        }

        return node;
    }

    private Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    public int size() {
        return size;
    }

    public Iterable<K> iteratorKeys() {
        List<K> keys = new ArrayList<>();
        inorderKeys(root, keys);
        return keys;
    }

    private void inorderKeys(Node node, List<K> keys) {
        if (node == null) {
            return;
        }
        inorderKeys(node.left, keys);
        keys.add(node.key);
        inorderKeys(node.right, keys);
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        List<Entry<K, V>> entries = new ArrayList<>();
        inorder(root, entries);
        return entries.iterator();
    }

    private void inorder(Node node, List<Entry<K, V>> entries) {
        if (node == null) {
            return;
        }
        inorder(node.left, entries);
        entries.add(new Entry<>(node.key, node.val));
        inorder(node.right, entries);
    }
}