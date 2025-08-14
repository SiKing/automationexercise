package com.exercise.btree;

public class BinaryTree {

    private BinaryTree() {
	// Do not instantiate this class
    }

    public static boolean compareTrees(Node a, Node b) {
	if (a == null && b == null)
	    return true; // Both Nodes are empty - equal

	if (a == null || b == null)
	    return false; // One Node is empty, the other is not - different

	if (!a.equals(b))
	    return false; // Values of the Nodes are different

	// Continue comparing left and right subtrees
	return compareTrees(a.getLeft(), b.getLeft()) && compareTrees(a.getRight(), b.getRight());
    }

    public static void reverseTree(Node node) {
	if (node == null)
	    return; // If Node is null, do nothing

	// Swap Nodes
	Node temp = node.getLeft();
	node.setLeft(node.getRight());
	node.setRight(temp);

	// Recursively reverse both sides of tree
	reverseTree(node.getLeft());
	reverseTree(node.getRight());
    }
}
