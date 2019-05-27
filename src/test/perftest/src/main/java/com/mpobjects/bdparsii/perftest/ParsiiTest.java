package com.mpobjects.bdparsii.perftest;

import org.openjdk.jmh.annotations.Benchmark;

import parsii.eval.Expression;
import parsii.eval.Parser;
import parsii.eval.Scope;
import parsii.eval.Variable;

public class ParsiiTest {
	@Benchmark
	public void run(TestPlan aPlan) throws Exception {
		Scope scope = new Scope();
		Variable var = scope.create("x");
		Expression expr = Parser.parse("2 + (7 - 5) * 3.14159 * x^(12-10) + sin(-3.141)", scope);
		for (int x = 0; x < aPlan.iterations; ++x) {
			var.setValue(x);
			expr.evaluate();
		}
	}
}
