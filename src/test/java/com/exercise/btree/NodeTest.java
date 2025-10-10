package com.exercise.btree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NodeTest {

    @Test
    void testEquals() {
	Node n1 = new Node(123);
	Node n2 = new Node(123);
	n2.setLeft(new Node(456));
	Node n3 = new Node(789);

	assertNotEquals(n1, null, "null is not a Node");

	assertNotEquals(n1, 123, "int is not a Node");

	assertEquals(n1, n2); // only compares Node values, not children

	assertNotEquals(n1, n3);
    }

    @Test
    void testClone() throws Exception {
	Node node = new Node(123);
	node.setLeft(new Node(456));
	Node clone = (Node) node.clone();

	assertEquals(node, clone);

	assertTrue(BinaryTree.areSame(node, clone));

	node.setRight(new Node(789));

	assertEquals(node, clone); // only compares Node values, not children

	assertFalse(BinaryTree.areSame(node, clone)); // compares children too
    }

    @Test
    void testDeepClone() throws Exception {
	Node node = new Node(123);
	node.setLeft(new Node(456));
	Node clone = (Node) node.clone();

	assertEquals(456, node.getLeft().getValue());
	assertEquals(456, clone.getLeft().getValue());

	node.getLeft().setValue(666); // Modify only original Node?

	assertEquals(666, node.getLeft().getValue(), "Original Node should be modified");
	assertEquals(456, clone.getLeft().getValue(), "Clone Node should **not** be modified");
    }
}
