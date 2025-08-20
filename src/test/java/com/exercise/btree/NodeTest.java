package com.exercise.btree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NodeTest {

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testEquals() {
	Node n1 = new Node(123);
	Node n2 = new Node(123);
	n2.setLeft(new Node(456));
	Node n3 = new Node(789);

	assertFalse(n1.equals(null), "null is not a Node");

	assertFalse(n1.equals(new String("123")), "String is not a Node");

	assertTrue(n1.equals(n2)); // only compares Node values, not children

	assertFalse(n1.equals(n3));
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
}
