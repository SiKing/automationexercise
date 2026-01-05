package com.exercise.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class KaprekarsConstantTest {

    @Test
    void testInvalid() {
	IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
	    KaprekarsConstant.getConstantFrom(10_000);
	});
	assertEquals("The number must have 4 digits!", exp.getMessage());

	exp = assertThrows(IllegalArgumentException.class, () -> {
	    KaprekarsConstant.getConstantFrom(1111);
	});
	assertEquals("At least 2 digits must be different!", exp.getMessage());
    }

    @Test
    void testConstants() {
	assertEquals(6174, KaprekarsConstant.getConstantFrom(6174)); // The constant itself
	assertEquals(6174, KaprekarsConstant.getConstantFrom(3698));
	assertEquals(6174, KaprekarsConstant.getConstantFrom(1)); // will get zero-padded to 0001
    }
}
