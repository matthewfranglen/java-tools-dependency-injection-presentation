Overview
--------

This will cover how stuff works under the hood:
- Spring DI, reflection, annotation processing
- Spring AOP, proxy pattern, dynamic proxies in java
- JUnit, reflection, annotation processing
- code coverage, byte/source code injection, java agents
- sampling vs profiling

Spring Dependency Injection
---------------------------

### What problem does this solve?

This reduces coupling between classes.

### How is this achieved?

The application is split into individual components which each perform a single function.
Each component is provided with the other components it requires by a _dependency injection system_.
Each component implements an interface declaring its public api, and references other components using their interfaces.
This allows the implementation of a given component to be changed independent of the rest (in theory).

### How is this implemented?

It can be good to understand how Spring Dependency Injection changed over time.
This shows what the core problem is and how it was subsequently made easier to address.

#### XML

#### Java Configuration

#### Annotation Configuration
