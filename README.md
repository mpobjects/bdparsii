[![Build Status](https://travis-ci.org/mpobjects/bdparsii.svg?branch=master)](https://travis-ci.org/mpobjects/bdparsii)
[![Maven Central](https://img.shields.io/maven-central/v/com.mpobjects/bdparsii.svg)](http://www.maven.org/#search%7Cga%7C1%7Cg%3A%22com.mpobjects%22%20AND%20a%3A%22bdparsii%22)
![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/https/oss.sonatype.org/com.mpobjects/bdparsii.svg)

# bdparsii 

A BigDecimal port of [Scireum's Parsii](https://github.com/scireum/parsii) library. 

Using it is as simple as:

```java
Scope scope = Scope.create();   
Variable a = scope.getVariable("a");   
Expression expr = Parser.parse("3 + a * 4", scope);   
a.setValue(4);   
System.out.println(expr.evaluate());   
a.setValue(5);   
System.out.println(expr.evaluate());
```

## Performance

This version is roughly 10.5 times slower than Parsii executing the following test (9ms vs 97ms).

```java
Scope scope = new Scope();
Variable var = scope.create("x");
Expression expr = Parser.parse("2 + (7 - 5) * 3.14159 * x^(12-10) + sin(-3.141)", scope);
for (int x = 0; x < 1000000; ++x) {
	var.setValue(x);
	expr.evaluate();
}
```


## Maven

bdparsii is available under:

```xml
<dependency>
	<groupId>com.mpobjects</groupId>
	<artifactId>bdparsii</artifactId>
	<version>0.1.0</version>
</dependency>
```
