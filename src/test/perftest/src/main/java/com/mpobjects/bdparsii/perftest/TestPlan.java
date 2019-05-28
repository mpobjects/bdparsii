package com.mpobjects.bdparsii.perftest;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.State;

@State(org.openjdk.jmh.annotations.Scope.Benchmark)
public class TestPlan {
	public static final String FORMULA = "2 + (7 - 5) * 3.14159 * x^(12-10) + sin(-3.141)";

	@Param({ "1", "10", "100", "1000", "10000", "100000", "1000000" })
	public int iterations;
}