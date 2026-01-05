package com.exercise.btree;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

class BinaryTreeTest {

    @Test
    void testAreSame() throws Exception {
	Node node = new Node(0);
	BinaryTree.generate(node, 100);
	Node clone = (Node) node.clone();
	assertTrue(BinaryTree.areSame(node, clone));

	// verify we have a deep-clone
	node.setRight(new Node(789));
	assertFalse(BinaryTree.areSame(node, clone));
    }

    @Test
    void testReverse() throws Exception {
	Node node = new Node(0);
	BinaryTree.generate(node, 100);
	Node clone = (Node) node.clone();
	assertTrue(BinaryTree.areSame(node, clone), "Node and clone should be same initially.");
	BinaryTree.reverse(node);
	assertFalse(BinaryTree.areSame(node, clone), "Reverse Node and clone should be different.");
	BinaryTree.reverse(node);
	assertTrue(BinaryTree.areSame(node, clone), "Twice-reversed Node and clone should be same again.");
    }

    @Test
    void testMany() {
	Node node = new Node(0);

	StopWatch stopwatch = new StopWatch();
	stopwatch.start();
	BinaryTree.generate(node, 4_000); // XXX: at >4k StackOverflow occurs; works on my machine {tm}
	stopwatch.stop();

	System.out.println("INFO> Time taken: " + stopwatch.getDuration().toMillis());
    }
}
