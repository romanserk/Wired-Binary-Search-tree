package BinarySearchTree;

public class Node {

	private int number;
	
	private Node left;
	private Node right;	
	private Node parent;
	
	public Node(int number){
		this.number = number;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
	
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	

	
	
	
	
}
