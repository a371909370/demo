package com.learn.demo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


public class UnitTest {

	@Test
	public void test() {
		BigDecimal bigDecimal = new BigDecimal("0.02");
		BigDecimal bigDecimal2 = new BigDecimal("0.03");
		Object o = new Object();
		double a = 0.25;
		double b = 0.5;
		double c = b - a;
		double x = 100.0d;
		System.out.println(c);
	}

}
