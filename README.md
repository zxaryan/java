# Java Programming

Complete Java notes for learning and interview preparation: core concepts, code examples, comparison tables, and commonly asked questions.

## Contents

1. [Collection Framework](#1-collection-framework)
2. [Object-Oriented Programming](#2-object-oriented-programming)
3. [Java Basics](#3-java-basics)
4. [Exception Handling](#4-exception-handling)
5. [Multithreading and Concurrency](#5-multithreading-and-concurrency)
6. [Generics](#6-generics)
7. [Java 8+ Features](#7-java-8-features)
8. [JVM, Memory, and Garbage Collection](#8-jvm-memory-and-garbage-collection)
9. [Top Interview Questions](#9-top-interview-questions)
10. [Practice Plan](#10-practice-plan)

---

## 1. Collection Framework

The Java Collection Framework is a unified architecture of interfaces, classes, and algorithms for storing and manipulating groups of objects. It provides reusable data structures and methods to handle collections of data efficiently.

The framework has two main parts:

1. `java.util.Collection` stores groups of individual objects.
2. `java.util.Map` stores key-value pairs. `Map` is part of the framework but does **not** extend `Collection`.

### 1.1 Collection Interface Hierarchy

```
Iterable
  └── Collection
        ├── List
        │     ├── ArrayList
        │     ├── LinkedList
        │     └── Vector (legacy, synchronized)
        │           └── Stack (legacy)
        │
        ├── Set
        │     ├── HashSet
        │     │     └── LinkedHashSet
        │     └── SortedSet
        │           └── NavigableSet
        │                 └── TreeSet
        │
        └── Queue
              ├── PriorityQueue
              └── Deque
                    ├── ArrayDeque
                    └── LinkedList
```

`LinkedList` implements both `List` and `Deque`, which is why it appears twice.

### 1.2 Map Interface Hierarchy

```
Map
  ├── HashMap
  │     └── LinkedHashMap
  ├── SortedMap
  │     └── NavigableMap
  │           └── TreeMap
  ├── Hashtable (legacy, synchronized)
  └── ConcurrentMap
        └── ConcurrentHashMap (thread-safe)
```

Other maps: `EnumMap` (enum keys), `WeakHashMap` (weak keys), `IdentityHashMap` (reference equality).

### 1.3 List Implementations

| Feature | ArrayList | LinkedList | Vector |
|---|---|---|---|
| Internal structure | Dynamic array | Doubly linked list | Dynamic array |
| Get by index | O(1) | O(n) | O(1) |
| Add at end | O(1) amortized | O(1) | O(1) amortized |
| Insert or remove in middle | O(n) | O(1) once the node is found, O(n) to find it | O(n) |
| Thread-safe | No | No | Yes (synchronized) |
| Growth | 1.5x | Not applicable | 2x |
| Use when | Fast random access, mostly reads | Frequent insert or remove at ends, used as queue or deque | Legacy code only |

`Stack` extends `Vector` and is legacy. Use `ArrayDeque` as a stack instead.

### 1.4 Set Implementations

| Feature | HashSet | LinkedHashSet | TreeSet |
|---|---|---|---|
| Backed by | HashMap | LinkedHashMap | TreeMap (red-black tree) |
| Ordering | None | Insertion order | Sorted (natural or Comparator) |
| add / remove / contains | O(1) average | O(1) average | O(log n) |
| Null allowed | One null | One null | No null (with natural ordering) |
| Use when | Fast uniqueness check | Unique plus predictable order | Sorted unique elements, range queries |

### 1.5 Queue and Deque Implementations

| Class | Ordering | Complexity | Notes |
|---|---|---|---|
| PriorityQueue | Min-heap by default | offer and poll O(log n), peek O(1) | No nulls, not thread-safe, iteration order is not sorted |
| ArrayDeque | FIFO or LIFO | O(1) at both ends | No nulls, faster than Stack and LinkedList for stack and queue use |
| LinkedList | FIFO or LIFO | O(1) at both ends | Allows nulls, more memory per element |

### 1.6 Map Implementations

| Feature | HashMap | LinkedHashMap | TreeMap | Hashtable | ConcurrentHashMap |
|---|---|---|---|---|---|
| Ordering | None | Insertion or access order | Sorted by key | None | None |
| get / put | O(1) average | O(1) average | O(log n) | O(1) average | O(1) average |
| Null key | One | One | No (natural ordering) | No | No |
| Null values | Yes | Yes | Yes | No | No |
| Thread-safe | No | No | No | Yes (whole-table lock) | Yes (fine-grained locking and CAS) |
| Use when | Default choice | LRU cache, predictable order | Sorted keys, range queries | Legacy only | Concurrent access |

### 1.7 How HashMap Works Internally

- Data is stored in an array of buckets (`Node[]`). Default capacity is 16 and default load factor is 0.75.
- The bucket index is computed from the key's `hashCode()`: `hash = h ^ (h >>> 16)`, then `index = (n - 1) & hash`.
- Keys that land in the same bucket (collision) are chained as a linked list. When a bucket reaches 8 nodes and the table capacity is at least 64, the list converts to a red-black tree (worst case O(log n)). It converts back when the bucket shrinks to 6.
- When the number of entries exceeds `capacity * loadFactor`, the table doubles in size and entries are redistributed (rehashing).
- Lookup uses `hashCode()` to find the bucket, then `equals()` to find the key. If you override `equals()`, you must override `hashCode()` consistently. Equal objects must have equal hash codes.
- Keys should be immutable. A key whose hash changes after insertion becomes unreachable.

### 1.8 Fail-Fast vs Fail-Safe Iterators

| Type | Behavior | Examples |
|---|---|---|
| Fail-fast | Throws `ConcurrentModificationException` if the collection is structurally modified during iteration (tracked with `modCount`) | ArrayList, HashMap, HashSet |
| Fail-safe or weakly consistent | Never throws; iterates over a snapshot or tolerates concurrent changes | CopyOnWriteArrayList, ConcurrentHashMap |

To remove elements while iterating, use `Iterator.remove()` or `removeIf()`:

```java
List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5));
nums.removeIf(n -> n % 2 == 0);   // [1, 3, 5]
```

### 1.9 Comparable vs Comparator

| Comparable | Comparator |
|---|---|
| `java.lang`, method `compareTo(T o)` | `java.util`, method `compare(T a, T b)` |
| Natural ordering, defined inside the class | Custom ordering, defined outside the class |
| One ordering per class | Many orderings possible |

```java
record Employee(String name, int age, double salary) {}

List<Employee> list = new ArrayList<>();
list.sort(Comparator.comparingInt(Employee::age)
                    .thenComparing(Employee::name));
```

### 1.10 Code Examples

```java
// List
List<String> names = new ArrayList<>();
names.add("Aryan");
names.add("Rahul");
System.out.println(names.get(0));

// Set removes duplicates
Set<Integer> unique = new HashSet<>(List.of(1, 2, 2, 3));   // [1, 2, 3]

// Map
Map<String, Integer> marks = new HashMap<>();
marks.put("Java", 90);
marks.merge("Java", 5, Integer::sum);                       // Java -> 95
marks.getOrDefault("Python", 0);                            // 0
marks.computeIfAbsent("SQL", k -> 80);

for (Map.Entry<String, Integer> e : marks.entrySet()) {
    System.out.println(e.getKey() + " = " + e.getValue());
}

// Queue and Deque
Queue<Integer> queue = new LinkedList<>();
queue.offer(1); queue.poll();

Deque<Integer> stack = new ArrayDeque<>();
stack.push(1); stack.pop();

// Min-heap and max-heap
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// Immutable collections (Java 9+)
List<Integer> fixed = List.of(1, 2, 3);
Map<String, Integer> fixedMap = Map.of("a", 1, "b", 2);
```

### 1.11 Collections Utility Class

`Collections.sort()`, `reverse()`, `shuffle()`, `max()`, `min()`, `frequency()`, `swap()`, `binarySearch()`, `unmodifiableList()`, `synchronizedList()`, `emptyList()`.

### 1.12 Which Collection Should I Use?

| Need | Use |
|---|---|
| Ordered list with fast index access | `ArrayList` |
| Unique elements, fastest lookup | `HashSet` |
| Unique elements, insertion order | `LinkedHashSet` |
| Unique elements, sorted | `TreeSet` |
| Key-value lookup | `HashMap` |
| Key-value with insertion or access order (LRU cache) | `LinkedHashMap` |
| Sorted key-value | `TreeMap` |
| Stack | `ArrayDeque` |
| Queue | `ArrayDeque` or `LinkedList` |
| Priority-based processing | `PriorityQueue` |
| Concurrent key-value access | `ConcurrentHashMap` |
| Read-heavy concurrent list | `CopyOnWriteArrayList` |

### 1.13 Collection Framework Interview Questions

1. How does `HashMap` work internally? What happens on a collision?
2. What is the `equals()` and `hashCode()` contract? What breaks if you violate it?
3. Difference between `HashMap`, `Hashtable`, and `ConcurrentHashMap`?
4. Difference between `ArrayList` and `LinkedList`? Which is faster for what?
5. Why is `HashMap` not thread-safe? What problems occur under concurrent writes?
6. What is a fail-fast iterator? How do you avoid `ConcurrentModificationException`?
7. `Comparable` vs `Comparator`?
8. How does `TreeMap` keep keys sorted? What is its time complexity?
9. `HashSet` vs `TreeSet` vs `LinkedHashSet`?
10. How would you implement an LRU cache using `LinkedHashMap`?
11. Why should keys in a `HashMap` be immutable?
12. What changed in `HashMap` in Java 8?

---

## 2. Object-Oriented Programming

### 2.1 Four Pillars

| Pillar | Meaning | Java mechanism |
|---|---|---|
| Encapsulation | Hide data, expose behavior | `private` fields with getters and setters |
| Inheritance | Reuse and extend a parent class | `extends`, `implements` |
| Polymorphism | One interface, many forms | Method overloading (compile time), overriding (runtime) |
| Abstraction | Expose what an object does, hide how | `abstract` classes, interfaces |

### 2.2 Abstract Class vs Interface

| Feature | Abstract class | Interface |
|---|---|---|
| Methods | Abstract and concrete | Abstract, `default`, `static`, `private` (Java 9+) |
| Fields | Any kind | `public static final` constants only |
| Constructors | Yes | No |
| Inheritance | Single (`extends`) | Multiple (`implements`) |
| Use when | Classes share state and code | Defining a capability or contract |

### 2.3 Overloading vs Overriding

| Overloading | Overriding |
|---|---|
| Same name, different parameters | Same signature in a subclass |
| Resolved at compile time | Resolved at runtime (dynamic dispatch) |
| Same class or subclass | Parent-child relationship |
| Return type alone cannot differentiate | Return type must be same or covariant; cannot reduce visibility |

`static`, `private`, and `final` methods cannot be overridden.

### 2.4 Access Modifiers

| Modifier | Class | Package | Subclass | World |
|---|---|---|---|---|
| `private` | Yes | No | No | No |
| default | Yes | Yes | No | No |
| `protected` | Yes | Yes | Yes | No |
| `public` | Yes | Yes | Yes | Yes |

### 2.5 Key Keywords

- `static`: belongs to the class, not to instances. Static blocks run once at class loading.
- `final`: a variable cannot be reassigned, a method cannot be overridden, a class cannot be extended.
- `this`: reference to the current object. `super`: reference to the parent class.
- Constructors are not inherited. `this()` and `super()` must be the first statement in a constructor.

### 2.6 Creating an Immutable Class

1. Declare the class `final`.
2. Make all fields `private final`.
3. Provide no setters.
4. Initialize all fields in the constructor.
5. Return defensive copies of mutable fields.

```java
public final class Money {
    private final long amount;
    private final String currency;

    public Money(long amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    public long getAmount() { return amount; }
    public String getCurrency() { return currency; }
}
```

### 2.7 Object Class Methods

`equals()`, `hashCode()`, `toString()`, `clone()`, `finalize()` (deprecated), `getClass()`, `wait()`, `notify()`, `notifyAll()`.

### 2.8 SOLID Principles

| Principle | Idea |
|---|---|
| Single Responsibility | A class has one reason to change |
| Open/Closed | Open for extension, closed for modification |
| Liskov Substitution | Subclasses can replace their parent without breaking behavior |
| Interface Segregation | Prefer small, specific interfaces |
| Dependency Inversion | Depend on abstractions, not concrete classes |

### 2.9 Common Design Patterns

| Type | Patterns |
|---|---|
| Creational | Singleton, Factory, Abstract Factory, Builder, Prototype |
| Structural | Adapter, Decorator, Proxy, Facade |
| Behavioral | Strategy, Observer, Template Method, Command |

Thread-safe Singleton using an enum:

```java
public enum AppConfig {
    INSTANCE;
    public String getEnv() { return "prod"; }
}
```

---

## 3. Java Basics

### 3.1 Data Types

| Type | Size | Default |
|---|---|---|
| `byte` | 1 byte | 0 |
| `short` | 2 bytes | 0 |
| `int` | 4 bytes | 0 |
| `long` | 8 bytes | 0L |
| `float` | 4 bytes | 0.0f |
| `double` | 8 bytes | 0.0d |
| `char` | 2 bytes (UTF-16) | '\u0000' |
| `boolean` | JVM dependent | false |

Reference types (classes, interfaces, arrays) default to `null`.

### 3.2 Pass by Value

Java is always pass by value. For objects, the value passed is a copy of the reference. A method can change the object's state, but cannot make the caller's variable point to a different object.

### 3.3 String, StringBuilder, StringBuffer

| Feature | String | StringBuilder | StringBuffer |
|---|---|---|---|
| Mutable | No | Yes | Yes |
| Thread-safe | Yes (immutable) | No | Yes (synchronized) |
| Performance | Slow for repeated concatenation | Fastest | Slower than StringBuilder |

- **String pool:** string literals are stored in a pool inside the heap and reused. `new String("a")` always creates a new object. `intern()` returns the pooled instance.
- `==` compares references. `equals()` compares content.
- Strings are immutable for security, thread safety, caching of hash codes, and string pool safety.

```java
String a = "java";
String b = "java";
String c = new String("java");
a == b;          // true  (same pooled object)
a == c;          // false (different objects)
a.equals(c);     // true
```

### 3.4 Wrapper Classes and Boxing

- Each primitive has a wrapper (`int` and `Integer`, `char` and `Character`, and so on).
- Autoboxing converts a primitive to a wrapper. Unboxing does the reverse. Unboxing a `null` throws `NullPointerException`.
- `Integer` caches values from -128 to 127, so `Integer a = 127, b = 127; a == b` is `true`, while the same with 128 is `false`. Always compare wrappers with `equals()`.

### 3.5 Arrays and Common Utilities

```java
int[] arr = {5, 2, 9};
Arrays.sort(arr);
Arrays.toString(arr);
Arrays.fill(arr, 0);
int[] copy = Arrays.copyOf(arr, 5);
```

### 3.6 Stack vs Heap

| Stack | Heap |
|---|---|
| Per thread | Shared by all threads |
| Stores method frames, local variables, references | Stores objects and arrays |
| Freed automatically when a method returns | Freed by garbage collection |
| Fast, small | Larger, slower |

---

## 4. Exception Handling

### 4.1 Hierarchy

```
Throwable
  ├── Error (OutOfMemoryError, StackOverflowError)  -> do not catch
  └── Exception
        ├── Checked (IOException, SQLException)     -> must handle or declare
        └── RuntimeException (unchecked)
              ├── NullPointerException
              ├── IllegalArgumentException
              ├── ArrayIndexOutOfBoundsException
              └── ClassCastException
```

### 4.2 Key Points

| Concept | Detail |
|---|---|
| Checked exception | Checked at compile time; must be caught or declared with `throws` |
| Unchecked exception | Subclass of `RuntimeException`; not enforced by the compiler |
| `throw` | Throws an exception instance inside a method |
| `throws` | Declares exceptions a method may throw |
| `finally` | Runs whether or not an exception occurs (except `System.exit()` or JVM crash) |
| try-with-resources | Auto-closes resources implementing `AutoCloseable` |

```java
try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
    System.out.println(br.readLine());
} catch (IOException e) {
    throw new IllegalStateException("Could not read file", e);
}
```

### 4.3 Custom Exception

```java
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
```

### 4.4 Best Practices

- Catch specific exceptions, not `Exception` or `Throwable`.
- Never swallow exceptions silently. Log or rethrow with the cause.
- Do not use exceptions for normal control flow.
- Clean up with try-with-resources.
- In multi-catch, order from most specific to most general.

---

## 5. Multithreading and Concurrency

### 5.1 Creating Threads

```java
// 1. Runnable (preferred over extending Thread)
Thread t = new Thread(() -> System.out.println("Running"));
t.start();

// 2. ExecutorService with Callable and Future
ExecutorService pool = Executors.newFixedThreadPool(4);
Future<Integer> result = pool.submit(() -> 42);
System.out.println(result.get());
pool.shutdown();

// 3. CompletableFuture
CompletableFuture.supplyAsync(() -> "data")
                 .thenApply(String::toUpperCase)
                 .thenAccept(System.out::println);
```

### 5.2 Thread Lifecycle

`NEW` -> `RUNNABLE` -> (`BLOCKED` | `WAITING` | `TIMED_WAITING`) -> `TERMINATED`

### 5.3 Synchronization Tools

| Tool | Purpose |
|---|---|
| `synchronized` | Mutual exclusion on a method or block using an object monitor |
| `volatile` | Guarantees visibility and ordering; does not give atomicity |
| `ReentrantLock` | Explicit lock with `tryLock()`, timeouts, and fairness |
| `AtomicInteger` and other atomics | Lock-free atomic operations using CAS |
| `ReadWriteLock` | Many readers or one writer |
| `CountDownLatch` | Wait for N tasks to finish |
| `CyclicBarrier` | Threads wait for each other at a barrier |
| `Semaphore` | Limit concurrent access to a resource |
| `BlockingQueue` | Producer-consumer coordination |

### 5.4 wait, notify, sleep

| `wait()` | `sleep()` |
|---|---|
| `Object` method | `Thread` static method |
| Releases the monitor lock | Keeps the lock |
| Must be called inside `synchronized` | Can be called anywhere |
| Woken by `notify()` / `notifyAll()` | Wakes after the timeout |

### 5.5 Thread Pools

| Factory | Behavior |
|---|---|
| `newFixedThreadPool(n)` | Fixed number of threads |
| `newCachedThreadPool()` | Grows as needed, reuses idle threads |
| `newSingleThreadExecutor()` | One thread, tasks run in order |
| `newScheduledThreadPool(n)` | Delayed and periodic tasks |
| `newVirtualThreadPerTaskExecutor()` | Virtual threads (Java 21) |

In production, prefer constructing `ThreadPoolExecutor` directly with a bounded queue.

### 5.6 Deadlock

Four conditions must all hold: mutual exclusion, hold and wait, no preemption, circular wait. Prevent it by acquiring locks in a consistent global order, using `tryLock()` with a timeout, and keeping critical sections small.

### 5.7 Producer-Consumer Example

```java
BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

Thread producer = new Thread(() -> {
    try { for (int i = 0; i < 5; i++) queue.put(i); }
    catch (InterruptedException e) { Thread.currentThread().interrupt(); }
});

Thread consumer = new Thread(() -> {
    try { while (true) System.out.println(queue.take()); }
    catch (InterruptedException e) { Thread.currentThread().interrupt(); }
});
```

---

## 6. Generics

Generics provide compile-time type safety and remove the need for casts.

```java
class Box<T> {
    private T value;
    void set(T value) { this.value = value; }
    T get() { return value; }
}

static <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) >= 0 ? a : b;
}
```

| Wildcard | Meaning | Use |
|---|---|---|
| `List<?>` | Unknown type | Read-only access to any list |
| `List<? extends Number>` | Number or a subtype | Read (producer) |
| `List<? super Integer>` | Integer or a supertype | Write (consumer) |

**PECS:** Producer Extends, Consumer Super.

**Type erasure:** generic type information is removed at compile time. Consequences: you cannot create `new T()`, `new T[]`, or use `instanceof List<String>`, and `List<String>` and `List<Integer>` are the same class at runtime.

---

## 7. Java 8+ Features

### 7.1 Lambdas and Functional Interfaces

A functional interface has exactly one abstract method.

| Interface | Method | Use |
|---|---|---|
| `Predicate<T>` | `boolean test(T)` | Filter conditions |
| `Function<T,R>` | `R apply(T)` | Transform |
| `Consumer<T>` | `void accept(T)` | Side effects |
| `Supplier<T>` | `T get()` | Lazy creation |
| `BiFunction<T,U,R>` | `R apply(T,U)` | Two inputs |
| `UnaryOperator<T>` | `T apply(T)` | Same-type transform |

### 7.2 Streams

```java
List<String> result = employees.stream()
    .filter(e -> e.salary() > 50000)
    .sorted(Comparator.comparing(Employee::name))
    .map(Employee::name)
    .collect(Collectors.toList());

// Grouping and counting
Map<String, Long> countByDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::dept, Collectors.counting()));

// Reduce
int sum = List.of(1, 2, 3, 4).stream().reduce(0, Integer::sum);

// flatMap
List<Integer> flat = List.of(List.of(1, 2), List.of(3)).stream()
    .flatMap(List::stream)
    .toList();
```

- **Intermediate operations** (lazy): `filter`, `map`, `flatMap`, `sorted`, `distinct`, `limit`, `skip`, `peek`.
- **Terminal operations** (trigger execution): `collect`, `forEach`, `reduce`, `count`, `anyMatch`, `findFirst`, `toList`.
- A stream cannot be reused after a terminal operation.
- `map` transforms one to one. `flatMap` transforms one to many and flattens.
- Use parallel streams only for large, CPU-bound, stateless operations.

### 7.3 Optional

```java
Optional<String> name = Optional.ofNullable(getName());
String value = name.map(String::trim).orElse("unknown");
name.ifPresent(System.out::println);
```

Use `Optional` as a return type. Do not use it for fields or method parameters, and do not call `get()` without checking.

### 7.4 Newer Language Features

| Version | Feature |
|---|---|
| Java 8 | Lambdas, Streams, Optional, default methods, `java.time` API, method references |
| Java 9 | Modules, `List.of`, `Map.of`, private interface methods |
| Java 10 | `var` for local variables |
| Java 14+ | Switch expressions |
| Java 15 | Text blocks |
| Java 16 | Records, pattern matching for `instanceof` |
| Java 17 | Sealed classes (LTS) |
| Java 21 | Virtual threads, record patterns, sequenced collections (LTS) |

```java
record Point(int x, int y) {}                       // immutable data carrier

String size = switch (n) {                          // switch expression
    case 1, 2 -> "small";
    case 3 -> "medium";
    default -> "large";
};

if (obj instanceof String s && !s.isEmpty()) { }    // pattern matching
```

---

## 8. JVM, Memory, and Garbage Collection

### 8.1 JDK, JRE, JVM

- **JVM** runs bytecode. **JRE** is the JVM plus core libraries. **JDK** is the JRE plus development tools (`javac`, `jar`, `jdb`).

### 8.2 Execution Flow

`.java` -> `javac` -> `.class` bytecode -> Class Loader -> Bytecode Verifier -> Interpreter and JIT compiler -> Native code

### 8.3 Runtime Memory Areas

| Area | Contents |
|---|---|
| Heap | Objects and arrays (Young generation: Eden and Survivor spaces; Old generation) |
| Metaspace | Class metadata (replaced PermGen in Java 8; uses native memory) |
| Stack | Per thread: frames, local variables |
| PC register | Per thread: address of the current instruction |
| Native method stack | Native method calls |

### 8.4 Garbage Collection

- An object becomes eligible for GC when it is no longer reachable from any GC root (thread stacks, static fields, JNI references).
- Minor GC cleans the Young generation. Major or Full GC cleans the Old generation.
- Objects that survive several minor GCs are promoted to the Old generation.

| Collector | Notes |
|---|---|
| Serial | Single thread, small applications |
| Parallel | Throughput focused |
| G1 | Default since Java 9, region based, balanced pauses |
| ZGC / Shenandoah | Very low pause times for large heaps |

### 8.5 Memory Leaks in Java

Common causes: static collections that keep growing, unclosed resources, listeners that are never removed, `ThreadLocal` values not removed in thread pools, and mutable keys in hash-based collections.

### 8.6 Class Loading

Bootstrap -> Platform (Extension) -> Application class loader, following the parent delegation model: a loader asks its parent first before loading a class itself.

### 8.7 Common Errors

| Error | Cause |
|---|---|
| `OutOfMemoryError: Java heap space` | Heap exhausted |
| `StackOverflowError` | Infinite or very deep recursion |
| `OutOfMemoryError: Metaspace` | Too many classes loaded |
| `NoClassDefFoundError` | Class present at compile time, missing at runtime |

---

## 9. Top Interview Questions

**Core Java**
1. Why is `String` immutable? What is the String pool?
2. `==` vs `equals()`? What is the `hashCode()` contract?
3. Abstract class vs interface, and when do you choose each?
4. Is Java pass by value or pass by reference?
5. Difference between `final`, `finally`, and `finalize`?
6. What are checked and unchecked exceptions?
7. How do you create an immutable class?
8. What is the difference between `static` and instance members?

**Collections and Generics**
9. Internal working of `HashMap`, collisions, resizing, treeification.
10. `ConcurrentHashMap` vs `Hashtable` vs `synchronizedMap`.
11. `ArrayList` vs `LinkedList`, `HashSet` vs `TreeSet`.
12. What is type erasure? Explain PECS.

**Concurrency**
13. `synchronized` vs `ReentrantLock`, and what does `volatile` guarantee?
14. How do you avoid and detect deadlock?
15. `wait()` vs `sleep()`, `Runnable` vs `Callable`.
16. How does a thread pool work? How do you size it?
17. Explain `CompletableFuture` and when to use it.

**Java 8+ and JVM**
18. Stream intermediate vs terminal operations, `map` vs `flatMap`.
19. What are functional interfaces? Name five built-in ones.
20. How does garbage collection work? Which collectors exist?
21. Explain heap, stack, and Metaspace. What causes `OutOfMemoryError`?
22. What are records, sealed classes, and virtual threads?

**Coding Problems to Practice**
- Find duplicates in an array using a `HashSet`
- Count word frequency using a `HashMap` and streams
- Sort employees by salary then name using `Comparator`
- LRU cache with `LinkedHashMap`
- Producer-consumer with `BlockingQueue`
- Reverse a string, check palindrome, find the first non-repeated character
- Implement a thread-safe Singleton
- Group a list of objects by a field using streams

---

## 10. Practice Plan

| Week | Focus | Deliverable on GitLab |
|---|---|---|
| 1 | OOP, Strings, Exceptions | Small programs for each concept |
| 2 | Collections (List, Set, Map) | Word counter, LRU cache, custom `HashMap` |
| 3 | Concurrency basics | Producer-consumer, thread pool demo |
| 4 | Streams, Optional, Java 8+ | 20 stream exercises |
| 5 | Generics, JVM, GC | Notes plus a memory-leak demo |
| 6 | Mixed revision and mock questions | Answer all questions in section 9 aloud |

**Daily habit:** read one topic, write one runnable example, push it to GitLab, and explain the concept aloud in 60 seconds.

---

**Author:** Aryan Patel | [LinkedIn](https://linkedin.com/in/codexaryan) | [Portfolio](https://codewithpatel.in)