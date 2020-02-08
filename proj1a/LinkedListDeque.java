public class LinkedListDeque<T> {

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        private Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node dummy;
    private int size;

    public LinkedListDeque() {
        dummy = new Node(null, null, null);
        dummy.prev = dummy;
        dummy.next = dummy;
        size = 0;
    }

    public void addFirst(T item) {
        size += 1;
        Node p = new Node(item, dummy, dummy.next);
        dummy.next.prev = p;
        dummy.next = p;
    }

    public void addLast(T item) {
        size += 1;
        Node p = new Node(item, dummy.prev, dummy);
        dummy.prev.next = p;
        dummy.prev = p;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }
        Node p = dummy.next;
        while (p != dummy) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node p = dummy.next;
        p.next.prev = dummy;
        dummy.next = p.next;
        size -= 1;
        return p.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node p = dummy.prev;
        p.prev.next = dummy;
        dummy.prev = p.prev;
        size -= 1;
        return p.item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = dummy.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    private T getRecursive(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(p.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(dummy.next, index);
    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> L = new LinkedListDeque<>();
//        L.addLast(15);
//        L.addFirst(10);
//        L.addFirst(5);
//        L.addFirst(1);
//        L.addFirst(0);
//        System.out.println(L.removeFirst());
//        System.out.println(L.removeLast());
//        L.printDeque();
//        System.out.println(L.getRecursive(0));
//    }
}
