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

## Results

```
Benchmark         (iterations)   Mode  Cnt    Score   Error  Units
BdParsiiTest.run       1000000  thrpt  200   12.399 ± 0.062  ops/s
ParsiiTest.run         1000000  thrpt  200  105.888 ± 0.598  ops/s
``` 
