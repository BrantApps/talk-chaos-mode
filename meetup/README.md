### Chaos Mode talk companion repo
This repository complements the ["Chaos Mode: A foray into fuzz" talk](https://goo.gl/GzC7Xh) given at SWMobile's November 2018 meet-up (video [here](https://youtu.be/ES-852Dr4ZY))

If you'd like to get it up and running add the following libraries as external dependencies:

1. assertj-core-3.11.1
2. byte-buddy-1.9.3
3. byte-buddy-agent-1.9.3
4. commons-lang3-3.8.1
5. JUnit4
6. mockito-core-2.23.0
7. mockito-inline-2.23.0
8. objenesis-3.0.1

These can be found on Maven Central. Apologies, I stripped everything back for demo clarity so no Gradle/Maven/Ivy dependency management!

#### Pre-requisites
* Java 1.8
* KotlinJavaRuntime
* IntelliJ IDEA CE