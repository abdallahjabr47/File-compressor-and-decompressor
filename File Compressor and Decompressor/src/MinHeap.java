
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinHeap {
	int capacity;
	int size = 0;
	Node[] heap;

	//StageOfCompression
	MinHeap(int capacity) {
		this.capacity = capacity;
		this.heap = new Node[capacity];
	}

	private int getLeftChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}

	private int getRightChildIndex(int parentIndex) {
		return 2 * parentIndex + 2;
	}

	private int getParentIndex(int childIndex) {
		return (childIndex - 1) / 2;
	}

	private boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) < size;
	}

	private boolean hasRightChild(int index) {
		return getRightChildIndex(index) < size;
	}

	private boolean hasParent(int index) {
		return getParentIndex(index) >= 0;
	}

	private Node leftChild(int parentIndex) {
		return heap[getLeftChildIndex(parentIndex)];
	}

	private Node rightChild(int parentIndex) {
		return heap[getRightChildIndex(parentIndex)];
	}

	private Node parent(int childIndex) {
		return heap[getParentIndex(childIndex)];
	}
	
	private void swap(int index1, int index2) {
		Node element = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = element;
	}

	// Poll
	private void heapifyDown() {
		int index = 0;

		while (hasLeftChild(index)) {
			int smallestChildIndex = getLeftChildIndex(index);

			if (hasRightChild(index) && rightChild(index).frequency < leftChild(index).frequency) {
				smallestChildIndex = getRightChildIndex(index);
			}

			if (heap[index].frequency < heap[smallestChildIndex].frequency) {
				break;
			} else {
				swap(index, smallestChildIndex);
			}
			index = smallestChildIndex;
		}
	}
	
	// add
	private void heapifyUp() {
		int index = size - 1;
		while (hasParent(index) && parent(index).frequency > heap[index].frequency) {
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
		}
	}
	
	// add
	private void checkCapacity() {
		if (size == capacity) {
			heap = Arrays.copyOf(heap, capacity * 2);
			capacity = capacity * 2;
		}
	}

	// Time Complexity : O(1)
	// StageOfCompression
	Node peek() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return heap[0];
	}

	// Time Complexity : O( Log n)
	// StageOfCompression
	Node poll() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node element = heap[0];
		heap[0] = heap[size - 1];
		size--;
		heapifyDown();
		return element;
	}
	
	// Time Complexity : O( Log n)
	// StageOfCompression
	public void add(Node item) {
		checkCapacity();
		heap[size] = item;
		size++;
		heapifyUp();
	}

	void printHeap() {
		for (int i = 0; i < size; i++) {
			System.out.print(heap[i].frequency + "    " + heap[i].value + " ");
		}
	}
}
