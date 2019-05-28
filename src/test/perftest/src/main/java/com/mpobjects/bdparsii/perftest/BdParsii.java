package com.mpobjects.bdparsii.perftest;

import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import com.mpobjects.bdparsii.eval.Expression;
import com.mpobjects.bdparsii.eval.Parser;
import com.mpobjects.bdparsii.eval.Scope;
import com.mpobjects.bdparsii.eval.Variable;

public class BdParsii {
	@State(org.openjdk.jmh.annotations.Scope.Benchmark)
	public static class MCPlan {
		static final Map<String, MathContext> MATH_CONTEXTS;

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

	@Benchmark
	public void run(Blackhole blackhole, TestPlan aPlan, MCPlan aMcPlan) throws Exception {
		Scope scope = new Scope();
		scope.setMathContext(aMcPlan.getMc());
		Variable var = scope.create("x");
		Expression expr = Parser.parse(TestPlan.FORMULA, scope);
		for (int x = 1_000_000 - aPlan.iterations; x < 1_000_000; ++x) {
			var.setValue(x);
			blackhole.consume(expr.evaluate());
		}
	}

}
