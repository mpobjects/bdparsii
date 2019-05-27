package com.mpobjects.bdparsii.perftest;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class TestPlan {
	@Param({ "1000000" })
	public int iterations;
}
