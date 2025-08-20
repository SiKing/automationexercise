package com.exercise.btree;

public class Node implements Cloneable {

    private int value;
    private Node left, right; // children

    public Node(int value, Node left, Node right) {
	this.value = value;
	this.left = left;
	this.right = right;
    }

    public Node(int value) {
	this.value = value;
	// Explicitly set the children to null, because tree traversal depends on this!
	this.left = null;
	this.right = null;
    }

    public int getValue() {
	return value;
    }

    public void setValue(int value) {
	this.value = value;
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

    /**
     * Check only the {@code value} for equality.
     */
    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof Node))
	    return false; // Not a Node

	Node that = (Node) obj;
	if (this.getValue() == that.getValue())
	    return true; // Nodes have the same value

	return false; // Nodes must have different values
    }

    /**
     * Deep-clone this Node.
     * 
     * @throws CloneNotSupportedException
     */
    @Override
    public Node clone() throws CloneNotSupportedException {
	Node n = (Node) super.clone();
	// Apparently this does a deep-clone! :o
	return n;
    }
}
