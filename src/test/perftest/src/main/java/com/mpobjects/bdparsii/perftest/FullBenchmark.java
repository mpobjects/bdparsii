package com.mpobjects.bdparsii.perftest;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.State;

import com.mpobjects.bdparsii.eval.Expression;
import com.mpobjects.bdparsii.eval.Parser;
import com.mpobjects.bdparsii.eval.Scope;
import com.mpobjects.bdparsii.eval.Variable;

public class FullBenchmark {
	@State(org.openjdk.jmh.annotations.Scope.Benchmark)
	public static class TestPlan {
		@Param({ "1", "10", "100", "1000", "10000", "100000", "1000000" })
		public int iterations;
	}

	private static final String FORMULA = "2 + (7 - 5) * 3.14159 * x^(12-10) + sin(-3.141)";

	@Benchmark
	public void bdparsii(TestPlan aPlan) throws Exception {
		Scope scope = new Scope();
		Variable var = scope.create("x");
		Expression expr = Parser.parse(FORMULA, scope);
		for (int x = 1_000_000-aPlan.iterations; x < 1_000_000; ++x) {
			var.setValue(x);
			expr.evaluate();
		}
	}

	@Benchmark
	public void parsii(TestPlan aPlan) throws Exception {
		parsii.eval.Scope scope = new parsii.eval.Scope();
		parsii.eval.Variable var = scope.create("x");
		parsii.eval.Expression expr = parsii.eval.Parser.parse(FORMULA, scope);
		for (int x = 1_000_000-aPlan.iterations; x < 1_000_000; ++x) {
			var.setValue(x);
			expr.evaluate();
		}
	}
}
