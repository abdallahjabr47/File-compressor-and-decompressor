
public class Node implements Comparable<Node> {
	int frequency;
	byte value;
	boolean leaf;
	Node left;
	Node right;

	// readTree
	Node(byte ch, int frequency, Node left, Node right, boolean leaf) {
		this.value = ch;
		this.frequency = frequency;
		this.left = left;
		this.right = right;
		this.leaf = leaf;
	}

	// readTree
	public Node(Node left, Node right) {
		this.left = left;
		this.right = right;
		this.leaf = false;
	}

	// StageOfCompression
	public Node(int frequency) {
		this.frequency = frequency;
	}

	// StageOfCompression
	public Node(int frequency, byte val, boolean leaf) {
		super();
		this.frequency = frequency;
		this.value = (byte) (val - 128);
		this.leaf = leaf;
	}

	// StageOfCompression
	public void addtoLeft(Node left) {
		this.frequency += left.frequency;
		this.left = left;
	}

	// StageOfCompression
	public void addtoRight(Node right) {
		this.frequency += right.frequency;
		this.right = right;
	}

	// StageOfCompression
	public void print() {
		if (this.leaf)
			System.out.println(this.value);
		if (this.left != null)
			this.left.print();

		if (this.right != null)
			this.right.print();
	}

	// addCode & StageOfDeCompression
	boolean isLeaf() {
		assert ((left == null) && (right == null)) || ((left != null) && (right != null));
		return (left == null) && (right == null);
	}

	@Override
	public int compareTo(Node a) {
		if (a.frequency > this.frequency)
			return -1;
		else if (a.frequency < this.frequency)
			return 1;
		else
			return 0;
	}
}
