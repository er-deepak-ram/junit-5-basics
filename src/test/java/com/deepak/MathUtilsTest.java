package com.deepak;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(Lifecycle.PER_CLASS)
class MathUtilsTest {

	static MathUtils mathUtils;
	
	@BeforeAll
	static void init() {
		mathUtils = new MathUtils();
	}
	@Test
	@Order(1)
	public void testAdd() {
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual);
		System.out.println("current object 1: "+this);
	}
	
	@Test
	@Order(2)
	public void testSubtract() {
		assertEquals(5, mathUtils.subtract(10, 5));
		System.out.println("current object 2: "+this);
	}
	
	@Test
	public void testMultiply() {
//		assertEquals(18, mathUtils.multiply(3, 6));
		assertAll(
				() -> assertEquals(18, mathUtils.multiply(3, 6)),
				() -> assertEquals(0, mathUtils.multiply(3, 0)),
				() -> assertEquals(-18, mathUtils.multiply(-3, 6))
				);
		System.out.println("current object 3: "+this);
	}
	
	@Test
	@DisplayName("division function exception test")
	public void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "Divide by 0 should throw");
		System.out.println("current object 4: "+this);
	}

	@RepeatedTest(2)
	public void testComputeCircleArea() {
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "Should return right circle area");
		System.out.println("current object 5: "+this);
	}
	
	@ParameterizedTest
	@CsvSource({"a,1", "b,2", "foo,3"})
	public void testParameters(String name, int value) {
		System.out.println("csv data "+name+" value "+value);
	}
	
	@ParameterizedTest
	@MethodSource("provideParameters")
	public void testParametersFromMethods(String name, int value) {
		System.out.println("method data "+name+" value "+value);
	}
	
	private static Stream<Arguments> provideParameters() {
		return Stream.of(Arguments.of("Apple", 1), Arguments.of("Orange", 2), Arguments.of("Kiwi", 3));
	}
}
