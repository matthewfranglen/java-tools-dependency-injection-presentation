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

---

[Show lego booklet slide]

The XML separates the wiring of the application from the individual classes.
This is like the instruction booklet which comes with lego - it tells you how to compose the application from the parts.
This separation of configuration and implementation allows the implementor to be completely unaware of spring.

---

[Show xml example slide]

This XML creates two beans.

It is verbose and requires learning a new language (the XML Domain Specific Language of Spring Dependency Injection).
It's fair to say that a lot of people did not like this.

---

[Show transformation slide]

Now that we have the XML we need to be able to create an application from it.

---

[Show meta-programming slide]

#### Meta Programming

Programming is the act of writing programs which operate on data.
Meta-programming is the act of writing programs which operate on code as data.

There is no meta-meta-programming.
A meta-program can operate over all parts of itself, including the higher level parts.

---

[Show reflection slide]

Reflection is one of the ways that meta programming is done in java.
The reflection classes provide ways for your code to look at itself.

---

[Show String to Class slide]

We need to be able to create a class from a string.
We saw that a class loader can do this.

---

[Show Create Class slide]

The class is not enough - we need to be able to create an instance of it.

This can require providing dependencies.
When satisfying these dependencies we need a store for them.
The application context is that store.

---

[Show field setting and methods slide]

Can call methods and directly set fields.
Depending on the security manager this can completely ignore access controls (e.g. private, final).

With all this the XML configuration can be turned into a running program.

---

[Show annotations slide]

(it's from David Copperfield by Charles Dickens)

#### Java Configuration

The XML configuration provided clear separation between the components of the program and the configuration of the program.

The XML configuration was limited in the expressitivity available.
It also took significant time to learn and write and maintain.
It also didn't do any type checking, so invalid configurations were easy to create.
Everyone already knew Java so configuring the application in Java was the next logical step.

Using annotations and Java code for configuration was far more natural.
We have all used spring annotations so I'm not going to go deeply into using them.
The important thing to realise is that annotations are there to provide meta data.
You use this meta data to direct your meta programs.

This shows the power of meta-programming.
The ability to deeply inspect the units of code permits complex decisions to have general solutions.
This has translated the dependency tree from XML into the methods and their types.

This approach also uses the Java type system to indicate the dependencies of each method.
This means that the interface based approach becomes a means of implementing the system itself.

---

[Show creating annotations slide]

##### What is a Java Annotation?

An annotation is a special kind of class that you can apply to classes, fields, methods... (basically anything).
It can carry read only values.

It can be inspected at compile time to instruct the Java compiler.
This can be used to enhance the compile time checks performed, adding safety.
E.G. @Override will warn if the method is not overriding another.

It can be (optionally) retained to run time to inform the running application.

---

[Show reading annotations slide]

##### How are they used?

You can read them from the Class, Method or Field object directly.
Spring reads them from the classes to determine how to create and use them.

---

[Show scope slide]

The final thing to mention for this is bean scope.
The scope determines when the bean will be created.

In some scopes the bean to use varies based on the current request being processed.
These can be used from beans with a more restricted scope.
This means that a single object must be able to act like a lot of different objects.

---

[Show aliens slide]

#### Dynamic Code Generation

Given all of this information available about the expectations of the program it can be possible to generate the code that will satisfy those requirements.
A class loader can be created that can load the class you can generate.

---

[Show dynamic proxy slide]

This allows the generation of a class which behaves and appears to be the original and yet acts as an intermediary.

With the ability to generate new code meta programming in java is complete.
This is something we will explore further in the next presentation as AOP uses it extensively.
