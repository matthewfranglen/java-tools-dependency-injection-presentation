Overview
--------

This will cover how stuff works under the hood:
- Spring Dependency Injection
    - Reflection
    - Annotation processing
    - Generated proxies

We will then talk about JUnit to show how the same techniques can facilitate testing.

Spring Dependency Injection
---------------------------

[Show what problem slide]

### What problem does this solve?

This reduces coupling between classes.

#### How is this achieved?

Classes define what they require.
A dependency injection system provides their requirements.
The implementation of a requirement can change without the dependent being aware.

---

[Show dependency 1 slide]

### Dependencies

You would describe each bean and the dependencies for that bean.
The dependencies were references to other beans, or hard-coded values.

---

[Show dependency 2 slide]

This forms a directed graph of dependencies.
The creation of the beans must be ordered to create dependencies before dependents.

---

[Show topological sort slide, go through sorting it]

This is called topological sorting.

---

[Show cycle slide]

You cannot perform a topological sort over a graph that has a cycle in it.
A cycle is any series of dependencies which lead back to the original bean.

---

[Show implementation details slide]

### How is this implemented?

It can be good to understand how Spring Dependency Injection changed over time.
This shows what the core problem is and how it was subsequently made easier to address.

---

[Show XML config slide]

#### XML

Used exclusively in the first versions of Spring.

The XML approach attacks the configuration problem.
The core problem in dependency injection is the separation of the application wiring from the individual components.

[Show lego pieces and instruction booklet]

The XML was verbose and specific.

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

Used @Configuration @Autowired and @Bean annotations.

This shows the power of meta-programming.
The ability to deeply inspect the units of code permits complex decisions to have general solutions.
This has translated the dependency tree from XML into the methods and their types.

This uses the Java type system to indicate the dependencies of each method.
This means that the interface based approach becomes a means of implementing the system itself.

#### Extended Annotations

The Java configuration was far more natural for Java programmers.
It is still in use today (e.g. creating beans of a library class).

The location of configuration became the next problem.
Having configuration separate to implementation means that configuration can be viewed as action at a distance (bad).
However the entire aim of this is to separate the individual components.

By using the type system even more, it is possible for a class to specify its dependents.
However just specifying dependencies is not sufficient.

You need to be able to distinguish between alternatives.
There are different bean lifecycles available.
Blah blah blah.
Basically additional metadata is required that goes beyond what plain Java classes communicate.

Annotations can provide this metadata.

##### What is a Java Annotation?

An annotation is a special kind of class that you can apply to classes, fields, methods... (basically anything).
It can carry read only values.

It can be inspected at compile time to instruct the Java compiler.
This can be used to enhance the compile time checks performed, adding safety.
E.G. @Override will warn if the method is not overriding another.

It can be (optionally) retained to run time to inform the running application.

##### How are they used?

You can read them from the Class, Method or Field object directly.
Spring reads them from the classes to determine how to create and use them.

#### Spring Boot and Auto Configuration
