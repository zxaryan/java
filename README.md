Java Programming 
1) Collection Framework :

The Java Collection Framework is a unified architecture (set of interfaces, classes, and algorithms) for storing and manipulating groups of objects. It provides reusable data structures and methods to efficiently handle collections of data.


Hierarchy of Java Collection Framework
The framework is divided into two main parts:

1) java.util.Collection (for storing groups of objects)

2) java.util.Map (for storing key-value pairs)

1. Collection Interface Hierarchy

Collection (Interface)
├── List (Interface)  
│   ├── ArrayList  
│   ├── LinkedList  
│   ├── Vector (Legacy, thread-safe)  
│   └── Stack (Legacy)  
│  
├── Set (Interface)  
│   ├── HashSet  
│   ├── LinkedHashSet  
│   └── TreeSet  
│  
└── Queue (Interface)  
    ├── PriorityQueue  
    └── Deque (Interface)  
        ├── ArrayDeque  
        └── LinkedList  


2. Map Interface Hierarchy

Map (Interface)  
├── HashMap  
├── LinkedHashMap  
├── TreeMap  
├── Hashtable (Legacy, thread-safe)  
└── ConcurrentHashMap (Thread-safe)  




