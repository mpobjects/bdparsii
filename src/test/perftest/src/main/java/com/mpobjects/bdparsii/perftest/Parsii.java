package com.mpobjects.bdparsii.perftest;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

import parsii.eval.Expression;
import parsii.eval.Parser;
import parsii.eval.Scope;
import parsii.eval.Variable;

public class Parsii {

	@Benchmark
	public void run(Blackhole blackhole, TestPlan aPlan) throws Exception {
		Scope scope = new Scope();
		Variable var = scope.create("x");
		Expression expr = Parser.parse(TestPlan.FORMULA, scope);
		for (int x = 1_000_000 - aPlan.iterations; x < 1_000_000; ++x) {
			var.setValue(x);
			blackhole.consume(expr.evaluate());
		}
	}
}
