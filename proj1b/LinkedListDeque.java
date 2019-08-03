public class LinkedListDeque<T> implements Deque<T>{

    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    /*
    public LinkedListDeque(T i) {
        sentinel = new Node(null, null, null);
        Node p = new Node(i, sentinel, sentinel);
        sentinel.next = p;
        sentinel.prev = p;
        size = 1;
    }*/

    /* Creates an empty Deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T i) {
        Node p = new Node(i, sentinel, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size += 1;
    }

    @Override
    public void addLast(T i) {
        Node p = new Node(i, sentinel.prev, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int i = 0;
        Node p = sentinel.next;
        while (i < size()) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
            i += 1;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = sentinel.next;
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
        if (index < 0 || index >= size()) {
            return null;
        }
        if (index == 0) {
            return sentinel.next.item;
        }
        return getRecursive(sentinel.next.next, index - 1);
    }

    /*
    public static void main(String[] args) {
        LinkedListDeque<String> L = new LinkedListDeque<>("back");
        L.addFirst("is");
        L.addFirst("king");
        L.addFirst("Monkey");
        L.addLast("!");
        L.printDeque();

        System.out.println(L.get(4));
        System.out.println(L.getRecursive(4));

        System.out.println(L.size());
        System.out.println(L.isEmpty());
        System.out.println(L.removeFirst());
        System.out.println(L.removeLast());

        LinkedListDeque<Integer> LL = new LinkedListDeque<>();
        System.out.println(LL.size());
        System.out.println(LL.isEmpty());
    }*/
}