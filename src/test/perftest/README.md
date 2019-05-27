# Performance Test

A small project which performs a comparative performance test between the original parsii library, and the BigDecimal variant bdparsii.

The executed code is

```java
Scope scope = new Scope();
Variable var = scope.create("x");
Expression expr = Parser.parse("2 + (7 - 5) * 3.14159 * x^(12-10) + sin(-3.141)", scope);
for (int x = 0; x < iterations; ++x) {
	var.setValue(x);
	expr.evaluate();
}
```

This will test both parsing/compilation of the expression as execution of the given expression. 

## Results

Tested on JDK 1.8.0_102, VM 25.102-b14

[![graph](src/site/jmh-benchmark.png)](http://jmh.morethan.io/?gist=8e6607067d9d6cc75d050d683280a447)

```
Benchmark               (iterations)   Mode  Cnt       Score     Error  Units
FullBenchmark.bdparsii             1  thrpt   50  102023.490 ± 736.752  ops/s
FullBenchmark.bdparsii            10  thrpt   50   94516.004 ± 684.536  ops/s
FullBenchmark.bdparsii           100  thrpt   50   69617.673 ± 465.573  ops/s
FullBenchmark.bdparsii          1000  thrpt   50   19331.023 ±  73.306  ops/s
FullBenchmark.bdparsii         10000  thrpt   50    2466.331 ±  54.402  ops/s
FullBenchmark.bdparsii        100000  thrpt   50     201.945 ±   1.739  ops/s
FullBenchmark.bdparsii       1000000  thrpt   50      12.502 ±   0.081  ops/s
FullBenchmark.parsii               1  thrpt   50   82877.077 ± 447.833  ops/s
FullBenchmark.parsii              10  thrpt   50   82458.088 ± 478.550  ops/s
FullBenchmark.parsii             100  thrpt   50   77250.321 ± 550.409  ops/s
FullBenchmark.parsii            1000  thrpt   50   44542.966 ± 341.756  ops/s
FullBenchmark.parsii           10000  thrpt   50    9317.160 ±  77.967  ops/s
FullBenchmark.parsii          100000  thrpt   50    1040.265 ±  13.200  ops/s
FullBenchmark.parsii         1000000  thrpt   50     107.291 ±   0.920  ops/s
``` 
