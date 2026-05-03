import java.util.Random;

public class Main {
    public static void main(String[] args) {
        testHashTable();
        System.out.println();
        testBST();
    }

    private static void testHashTable() {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(11);
        Random random = new Random();

        for (int i = 0; i < 10_000; i++) {
            int id = random.nextInt(1_000_000);
            MyTestingClass key = new MyTestingClass(id, "code" + i);
            Student value = new Student("Student " + i, 18 + random.nextInt(10));
            table.put(key, value);
        }

        System.out.println("Hash table size: " + table.size());
        table.printBucketSizes();
    }

    private static void testBST() {
        BST<Integer, String> tree = new BST<>();

        tree.put(5, "five");
        tree.put(2, "two");
        tree.put(8, "eight");
        tree.put(1, "one");
        tree.put(3, "three");
        tree.put(2, "TWO");

        System.out.println("BST size: " + tree.size());
        System.out.println("Value for key 2: " + tree.get(2));

        tree.delete(5);
        System.out.println("BST size after deleting key 5: " + tree.size());

        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}