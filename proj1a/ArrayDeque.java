public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(double factor) {
        int newLength = (int) (items.length * factor);
        T[] tmp = (T[]) new Object[newLength];
        for (int i = 0; i < size; i++) {
            tmp[i] = get(i);
        }
        items = tmp;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(2);
        }
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        T item;
        if (size == 0) {
            return null;
        }
        if (nextFirst == items.length - 1) {
            item = items[0];
            items[0] = null;
            nextFirst = 0;
        } else {
            item = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst += 1;
        }
        size -= 1;
        if ((size * 4) < items.length && items.length >= 16) {
            resize(0.5);
        }
        return item;
    }

    public T removeLast() {
        T item;
        if (size == 0) {
            return null;
        }
        if (nextLast == 0) {
            item = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
        } else {
            item = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast -= 1;
        }
        size -= 1;
        if ((size * 4) < items.length && items.length >= 16) {
            resize(0.5);
        }
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (nextFirst + 1 + index < items.length) {
            return items[nextFirst + 1 + index];
        }
        return items[nextFirst + 1 + index - items.length];
    }
}
