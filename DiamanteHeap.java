import java.util.ArrayList;

class DiamanteHeap {
    private ArrayList<Integer> heap;
    private boolean isMinHeap;

    public DiamanteHeap(boolean isMinHeap) {
        this.heap = new ArrayList<>();
        this.isMinHeap = isMinHeap;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private boolean compare(int a, int b) {
        return isMinHeap ? a < b : a > b;
    }

    public void insert(int value) {
        heap.add(value);
        int current = heap.size() - 1;

        while (current > 0 && compare(heap.get(current), heap.get(parent(current)))) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public int removeRoot() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int root = heap.get(0);
        int last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return root;
    }

    private void heapifyDown(int index) {
        int smallestOrLargest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < heap.size() && compare(heap.get(left), heap.get(smallestOrLargest))) {
            smallestOrLargest = left;
        }

        if (right < heap.size() && compare(heap.get(right), heap.get(smallestOrLargest))) {
            smallestOrLargest = right;
        }

        if (smallestOrLargest != index) {
            swap(index, smallestOrLargest);
            heapifyDown(smallestOrLargest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void printHeap() {
        System.out.println(heap);
    }

    public static void main(String[] args) {
        DiamanteHeap minHeap = new DiamanteHeap(true);
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(20);
        minHeap.insert(2);

        System.out.println("Min-Heap:");
        minHeap.printHeap();

        System.out.println("Removed root: " + minHeap.removeRoot());
        minHeap.printHeap();

        DiamanteHeap maxHeap = new DiamanteHeap(false);
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(20);
        maxHeap.insert(2);

        System.out.println("Max-Heap:");
        maxHeap.printHeap();

        System.out.println("Removed root: " + maxHeap.removeRoot());
        maxHeap.printHeap();
    }
}
