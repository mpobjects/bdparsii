package com.mpobjects.bdparsii.perftest;

import org.openjdk.jmh.annotations.Benchmark;

import com.mpobjects.bdparsii.eval.Expression;
import com.mpobjects.bdparsii.eval.Parser;
import com.mpobjects.bdparsii.eval.Scope;
import com.mpobjects.bdparsii.eval.Variable;

public class BdParsiiTest {
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
