package com.exercise.btree;

import org.apache.commons.lang3.RandomUtils;

public class BinaryTree {

    private BinaryTree() {
	// Do not instantiate this class
    }

    public static boolean areSame(Node a, Node b) {
	if (a == null && b == null)
	    return true; // Both Nodes are empty - equal

	if (a == null || b == null)
	    return false; // One Node is empty, the other is not - different

	if (!a.equals(b))
	    return false; // Values of the Nodes are different

	// Continue comparing left and right subtrees
	return areSame(a.getLeft(), b.getLeft()) && areSame(a.getRight(), b.getRight());
    }

    /**
     * Another popular interview question.
     * 
     * @param node
     */
    public static void reverse(Node node) {
	if (node == null)
	    return; // Node is null - do nothing

	// Swap Nodes
	Node temp = node.getLeft();
	node.setLeft(node.getRight());
	node.setRight(temp);

	// Recursively reverse both sides of tree
	reverse(node.getLeft());
	reverse(node.getRight());
    }

    /**
     * Generate a random binary tree, creating given number of new Nodes. Mostly
     * useful for generating test data.
     * 
     * @param node  - starting Node
     * @param count - number of new Nodes to create
     */
    public static void generate(Node node, int count) {
	if (count < 1)
	    return;

	if (RandomUtils.insecure().randomBoolean()) {
	    // work on left side
	    if (node.getLeft() == null) {
		node.setLeft(new Node(RandomUtils.insecure().randomInt()));
		// pick a new path
		generate(node, count - 1);
	    } else {
		// follow same path
		generate(node.getLeft(), count);
	    }
	} else {
	    // work on right side
	    if (node.getRight() == null) {
		node.setRight(new Node(RandomUtils.insecure().randomInt()));
		// pick a new path
		generate(node, count - 1);
	    } else {
		// follow same path
		generate(node.getRight(), count);
	    }
	}
    }
}
