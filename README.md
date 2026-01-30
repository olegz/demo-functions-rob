# Getting Started

### Declared function exposed as REST endpoint automatically
- By definition/design  _spring-cloud-function-web_  exposes every function that is annotated as `@Bean` as REST endpoint. 
  In this case we created `uppercase` function. Name of the function becomes part of the PATH.
- Simply start the app and hit this URL http://localhost:8080/uppercase/rob. You will see the response of capitalized 'rob'.

### Routing Function
- Routing function (internally provided by the framework) provides several mechanisms to express which function needs to be invoked. One of those mechanisms is SpEL. Example:

```
curl -X POST http://localhost:8080/functionRouter  -H "spring.cloud.function.routing-expression: 'uppercase'" -d "rob"
```

- User claims that he can execute shell script. Indeed that was an issue that have resulted in valid CVE and was addressed https://spring.io/security/cve-2022-22963. So if you try to execute the code sample he has provided 

```
curl -X POST http://localhost:8080/functionRouter -H "spring.cloud.function.routing-expression: T(java.lang.Runtime).getRuntime().exec('touch /tmp/pwned')" -d "ping"
```

... you'll get 

```
org.springframework.expression.spel.SpelEvaluationException: EL1005E: Type cannot be found 'java.lang.Runtime'
	at org.springframework.expression.spel.support.SimpleEvaluationContext.lambda$static$0(SimpleEvaluationContext.java:104) ~[spring-expression-6.2.15.jar:6.2.15]
	at org.springframework.expression.spel.ExpressionState.findType(ExpressionState.java:182) ~[spring-expression-6.2.15.jar:6.2.15]
	at....
```

Aside from the fact that s-c-function was never designed to be front-end framework, our contention is that as a developer it is your responsibility to secure HTTP endpoints if such security is required. Just like with 'actuator' endpoints we do not provide any kind of default security with default filters as there is no sensible default. 

