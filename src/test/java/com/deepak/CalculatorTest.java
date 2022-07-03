package com.deepak;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CalculatorTest {
	
	private Calculator calculator;

	@BeforeEach
	void setUp() throws Exception {
		calculator = new Calculator();
	}

	@Test
	@DisplayName("Simple multiplication should work")
	void testMultiply() {
		assertEquals(20, calculator.multiply(4, 5), "Regular multiplication should work");
	}
	
	@Test
	@DisplayName("Ensure correct handling of zero")
	public void testMultiplyWithZero() {
		
//		testMultiplyWithZero() is skipped if executed on Linux
		Assumptions.assumeFalse(System.getProperty("os.name").contains("Linux"));
		
		assertEquals(0, calculator.multiply(5, 0), "Multiple with zero should be zero");
		assertEquals(0, calculator.multiply(0, 5), "Multiple with zero should be zero");
	}
	
	@Test
	@DisplayName("Simple division should work")
	public void testSimpleDivision() {
		assertEquals(4, calculator.divide(20, 5), "Reglar division should work");
	}
	
	@Test
	@DisplayName("Divide by 0 should throw exception")
	public void testDivideByZero() {
		Throwable exception = assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
		assertEquals(exception.getMessage(), "/ by zero");
	}
	
	@TestFactory
	public Stream<DynamicTest> testDifferentMultiplyOperations() {
		Calculator myCalci = new Calculator();
		int[][] myData = new int[][] { { 1, 2, 2 }, { 3, 5, 15 }, { 121, 4, 484 } };
		return Arrays.stream(myData).map(entry -> {
			int m1 = entry[0];
			int m2 = entry[1];
			int expected = entry[2];
			return dynamicTest(m1 + " * " + m2 + " = " + expected, () -> {
				assertEquals(expected, myCalci.multiply(m1, m2));
			});
		});
	}
	
	public static int[][] data() {
		return new int[][] { { 1, 2, 2 }, { 3, 5, 15 }, { 121, 4, 484 } };
	}
	
	@ParameterizedTest
	@MethodSource(value = "data")
	@DisplayName("Parameterized Test")
	public void testWithParameters(int[] data) {
		int m1 = data[0];
		int m2 = data[1];
		int expected = data[2];
		assertEquals(expected, calculator.multiply(m1, m2));
	}

}
