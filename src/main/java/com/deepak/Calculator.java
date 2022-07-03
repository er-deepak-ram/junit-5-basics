package com.deepak;

public class Calculator {

	public int multiply(int a, int b) {
		return a * b;
	}
	
	public int divide(int a, int b) {
		return a / b;
	}
	
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		int result = calculator.divide(10, 0);
		System.out.println("result: " + result);
	}
}