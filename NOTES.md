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

The XML approach attacks the configuration problem.
The core problem in dependency injection is the separation of the application wiring from the individual components.

[Show lego pieces and instruction booklet]

The XML was verbose and specific.

##### Dependencies

You would describe each bean and the dependencies for that bean.
The dependencies were references to other beans, or hard-coded values.

[Show lego wall with dependencies from row 3 to row 2 etc]

This forms a directed graph of dependencies.
The creation of the beans must be ordered to create dependencies before dependents.
This is called topological sorting.

[Show simple dependency graph, go through sorting it]

##### Reflection

Once the order of creation is known you must then create the instances themselves.
This uses reflection which is how you do meta-programming in Java.

[Show hand drawing itself Escher]

Programming is the act of writing programs which operate on data.
Meta-programming is the act of writing programs which operate on code as data.

A meta program can take the name of a class and create an instance of that class.
It can take the name of a method and then call it, and so on.
The idea is that the individual actions and objects in the program become selectable and combinable blocks of the meta-program.

There is no meta-meta-programming.
A meta-program can operate over all parts of itself, including the higher level parts.

[Some image about java reflection?]

With reflection in Java you can perform meta-programming and implement dependency injection.
 * As we saw in the last presentation, the Java Class object is loaded by the Classloader
   This takes the name of the class and returns the class
 * The Class object contains a lot of methods of interest
   It can give you each constructor, method or field on that class
 * The objects returned by the Class object are useable
   If the object is a Method or Constructor then it can be invoked
   Arguments can be passed, returned values can be received
   Fields can be set or read

With all this the XML configuration can be turned into a running program.

#### Java Configuration

The XML configuration provided clear separation between the components of the program and the configuration of the program.

The XML configuration was limited in the expressitivity available.
It also took significant time to learn and write and maintain.
It also didn't do any type checking, so invalid configurations were easy to create.
Everyone already knew Java so configuring the application in Java was the next logical step.

A configuration class can be defined and provided to the Spring Dependency Injection system.
It can then inspect this class to find every method in it.
Each method is a bean creating method, and the dependencies are the arguments to the method.

This shows the power of meta-programming.
The ability to deeply inspect the units of code permits complex decisions to have general solutions.
This has translated the dependency tree from XML into the methods and their types.

This uses the Java type system to indicate the dependencies of each method.
This means that the interface based approach becomes a means of implementing the system itself.

#### Annotation Configuration

The Java configuration was far more natural for Java programmers.
It is still in use today (e.g. creating beans of a library class).

The location of wiring (configuration) became the next problem.
Having wiring separate to implementation means that wiring can be viewed as action at a distance (bad).
However the entire aim of this is to separate the individual components.

By using the type system even more, it is possible for a class to specify its dependents.
However just specifying dependencies is not sufficient.

You need to be able to distinguish between alternatives.
There are different bean lifecycles available.
Blah blah blah.
Basically additional metadata is required that goes beyond what plain Java classes communicate.

Annotations can provide this metadata.

##### What is a Java Annotation?

An annotation is a special kind of class that you can create.
It can carry read only values.

#### Spring Boot and Auto Configuration
