package module8;

public class MinHeap<Key extends Comparable<Key>> extends BinaryHeap<Key> {
    @SuppressWarnings("unchecked")
    public MinHeap(int initialCapacity) {
        this.keys = (Key[]) new Comparable[1 + initialCapacity];
        this.keys[0] = null;
        this.size = 0;
        this.capacity = initialCapacity;
    }

    @SuppressWarnings("unchecked")
    public MinHeap(Key[] keys) {
        if (keys == null || keys.length == 0) {
            throw new IllegalArgumentException("argument passing to MinPQ is null");
        }

        this.keys = (Key[]) new Comparable[keys.length + 1];
        this.keys[0] = null;
        this.size = 0;
        this.capacity = keys.length;

        for (Key key : keys) {
            insert(key);
        }
    }

    @Override
    void swim(int i) {
        while (i > 1) {
            final int parent = i / 2;

            if (keys[i].compareTo(keys[parent]) < 0) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    @Override
    void sink(int i) {
        final int m = size() + 1;

        while (2 * i < m) {
            final int L = 2 * i;
            final int R = 2 * i + 1;

            int min = L;

            if (R < m && keys[R].compareTo(keys[min]) < 0) {
                min = R;
            }

            if (keys[i].compareTo(keys[min]) > 0) {
                swap(i, min);
                i = min;
            } else {
                break;
            }
        }
    }
}