package com.mpobjects.bdparsii.perftest;

import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

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

	@State(org.openjdk.jmh.annotations.Scope.Benchmark)
	public static class MCPlan {
		static Map<String, MathContext> MATH_CONTEXTS;

		static {
			MATH_CONTEXTS = new HashMap<>();
			MATH_CONTEXTS.put("DECIMAL128", MathContext.DECIMAL128);
			MATH_CONTEXTS.put("DECIMAL64", MathContext.DECIMAL64);
			MATH_CONTEXTS.put("DECIMAL32", MathContext.DECIMAL32);
			MATH_CONTEXTS.put("UNLIMITED", MathContext.UNLIMITED);
		}

		@Param({ "DECIMAL32", "DECIMAL64", "DECIMAL128", "UNLIMITED" })
		public String mathContext;

		public MathContext getMc() {
			return MATH_CONTEXTS.get(mathContext);
		}
	}

	private static final String FORMULA = "2 + (7 - 5) * 3.14159 * x^(12-10) + sin(-3.141)";

	@Benchmark
	public void bdparsii(TestPlan aPlan, MCPlan aMxPlan) throws Exception {
		Scope scope = new Scope();
		scope.setMathContext(aMxPlan.getMc());
		Variable var = scope.create("x");
		Expression expr = Parser.parse(FORMULA, scope);
		for (int x = 1_000_000 - aPlan.iterations; x < 1_000_000; ++x) {
			var.setValue(x);
			expr.evaluate();
		}
	}

	@Benchmark
	public void parsii(TestPlan aPlan) throws Exception {
		parsii.eval.Scope scope = new parsii.eval.Scope();
		parsii.eval.Variable var = scope.create("x");
		parsii.eval.Expression expr = parsii.eval.Parser.parse(FORMULA, scope);
		for (int x = 1_000_000 - aPlan.iterations; x < 1_000_000; ++x) {
			var.setValue(x);
			expr.evaluate();
		}
	}
}
