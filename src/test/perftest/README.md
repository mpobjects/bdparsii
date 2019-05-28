# Performance Test

A small project which performs a comparative performance test between the original parsii library, and the BigDecimal variant bdparsii.

The executed code is

```java
Scope scope = new Scope();
scope.setMathContext(mathcontext); // only for bdparsii
Variable var = scope.create("x");
Expression expr = Parser.parse("2 + (7 - 5) * 3.14159 * x^(12-10) + sin(-3.141)", scope);
for (int x = 1_000_000-iterations; x < 1_000_000; ++x) {
	var.setValue(x);
	expr.evaluate();
}
```

This will test both parsing/compilation of the expression as execution of the given expression. This test is based on the [original performance test](http://andreas.haufler.info/2013/12/how-to-write-one-of-fastest-expression.html) of the parsii library.

## Results

Tested on JDK 1.8.0_102, VM 25.102-b14

[![graph](src/site/jmh-benchmark.png)](http://jmh.morethan.io/?source=https://raw.githubusercontent.com/mpobjects/bdparsii/master/src/test/perftest/src/site/jmh-results.json)

```
Benchmark               (iterations)  (mathContext)   Mode  Cnt      Score      Error  Units
FullBenchmark.bdparsii             1      DECIMAL32  thrpt   50  67871.256 ± 1607.253  ops/s
FullBenchmark.bdparsii             1      DECIMAL64  thrpt   50  42743.208 ±  829.339  ops/s
FullBenchmark.bdparsii             1     DECIMAL128  thrpt   50  26159.110 ± 1098.821  ops/s
FullBenchmark.bdparsii             1      UNLIMITED  thrpt   50  41410.504 ± 1160.836  ops/s
FullBenchmark.bdparsii            10      DECIMAL32  thrpt   50  59741.777 ± 2221.047  ops/s
FullBenchmark.bdparsii            10      DECIMAL64  thrpt   50  37094.447 ± 1044.740  ops/s
FullBenchmark.bdparsii            10     DECIMAL128  thrpt   50  24675.890 ±  551.191  ops/s
FullBenchmark.bdparsii            10      UNLIMITED  thrpt   50  39770.924 ± 1180.890  ops/s
FullBenchmark.bdparsii           100      DECIMAL32  thrpt   50  31318.059 ±  834.014  ops/s
FullBenchmark.bdparsii           100      DECIMAL64  thrpt   50  17816.403 ±  543.140  ops/s
FullBenchmark.bdparsii           100     DECIMAL128  thrpt   50  13674.682 ±  368.600  ops/s
FullBenchmark.bdparsii           100      UNLIMITED  thrpt   50  27305.518 ±  935.835  ops/s
FullBenchmark.bdparsii          1000      DECIMAL32  thrpt   50   5831.176 ±  205.718  ops/s
FullBenchmark.bdparsii          1000      DECIMAL64  thrpt   50   2953.053 ±   82.041  ops/s
FullBenchmark.bdparsii          1000     DECIMAL128  thrpt   50   2525.055 ±   96.590  ops/s
FullBenchmark.bdparsii          1000      UNLIMITED  thrpt   50   6585.539 ±  260.105  ops/s
FullBenchmark.bdparsii         10000      DECIMAL32  thrpt   50    662.146 ±   25.492  ops/s
FullBenchmark.bdparsii         10000      DECIMAL64  thrpt   50    319.114 ±   11.614  ops/s
FullBenchmark.bdparsii         10000     DECIMAL128  thrpt   50    314.404 ±    2.512  ops/s
FullBenchmark.bdparsii         10000      UNLIMITED  thrpt   50    912.307 ±    8.908  ops/s
FullBenchmark.bdparsii        100000      DECIMAL32  thrpt   50     70.560 ±    0.949  ops/s
FullBenchmark.bdparsii        100000      DECIMAL64  thrpt   50     34.413 ±    0.656  ops/s
FullBenchmark.bdparsii        100000     DECIMAL128  thrpt   50     32.008 ±    0.330  ops/s
FullBenchmark.bdparsii        100000      UNLIMITED  thrpt   50     91.803 ±    0.911  ops/s
FullBenchmark.bdparsii       1000000      DECIMAL32  thrpt   50      6.766 ±    0.043  ops/s
FullBenchmark.bdparsii       1000000      DECIMAL64  thrpt   50      3.486 ±    0.020  ops/s
FullBenchmark.bdparsii       1000000     DECIMAL128  thrpt   50      3.166 ±    0.023  ops/s
FullBenchmark.bdparsii       1000000      UNLIMITED  thrpt   50      8.196 ±    0.065  ops/s
FullBenchmark.parsii               1            N/A  thrpt   50  82652.867 ±  668.539  ops/s
FullBenchmark.parsii              10            N/A  thrpt   50  82592.376 ±  378.592  ops/s
FullBenchmark.parsii             100            N/A  thrpt   50  77001.606 ±  340.141  ops/s
FullBenchmark.parsii            1000            N/A  thrpt   50  44898.930 ±  454.332  ops/s
FullBenchmark.parsii           10000            N/A  thrpt   50   9199.257 ±   84.282  ops/s
FullBenchmark.parsii          100000            N/A  thrpt   50   1065.391 ±   21.356  ops/s
FullBenchmark.parsii         1000000            N/A  thrpt   50    109.763 ±    0.982  ops/s
``` 

As these results show, performing BigDecimal calculations are slower than double based calculations.
The performance depends a lot on the chosen MathContext, and used mathematical functions. 
High precisions with complex math perform worse. 
If only basic math (`*/+-%`) is used the unlimited precision will actually perform better because no time is spend on rounding the results to the correct precision.

