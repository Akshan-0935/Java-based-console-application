# Data Structure & Graph Performance Analyzer

CIT300 Data Structures and Algorithms - Graded Practical Assignment 2 (Week 12)

## Project Description
A Java console application that lets the user work with an Array, Stack, Queue, Linked List,
Searching algorithms (Linear and Binary) and a Graph (BFS and DFS). A performance comparison
screen shows the number of steps and the execution time so we can explain the results using
algorithmic complexity.

## Team Members

> Replace every line marked FILL IN with the real details before submitting.

### Member 1
- Student Name: FILL IN
- Student ID: FILL IN
- Assigned Responsibility: Array and Searching implementation
- Individual Contribution:
  - Implemented the ArrayOps class (insert, delete, search, display)
  - Implemented the Searching class (linear search and binary search with step counting)
  - Tested array and searching features
  - Integrated them with the main menu

### Member 2
- Student Name: FILL IN
- Student ID: FILL IN
- Assigned Responsibility: Stack and Queue implementation
- Individual Contribution:
  - Implemented the StackDS class (push, pop, peek, display, overflow/underflow handling)
  - Implemented the QueueDS class (circular queue: enqueue, dequeue, peek, display)
  - Tested empty and full conditions
  - Integrated them with the main menu

### Member 3
- Student Name: FILL IN
- Student ID: FILL IN
- Assigned Responsibility: Linked List implementation
- Individual Contribution:
  - Implemented the LinkedListDS class (insert at head/tail, delete, search, display)
  - Tested empty list and not-found cases
  - Integrated it with the main menu

### Member 4
- Student Name: FILL IN
- Student ID: FILL IN
- Assigned Responsibility: Graph implementation, performance comparison, main menu and integration
- Individual Contribution:
  - Implemented the Graph class (adjacency list, add vertex, add edge, display, BFS, DFS, path check)
  - Implemented PerformanceAnalyzer (steps and time comparison tables)
  - Built Main (menus), InputHelper (input validation) and ResultsLog
  - Integration and final testing

## Technologies Used
- Java (JDK 17 or newer)
- Git and GitHub
- Console (command line) interface

## Main System Features
- Array: insert at a position, delete, search, display
- Stack: push, pop, peek, display, with overflow/underflow handling
- Queue: enqueue, dequeue, peek, display, with overflow/underflow handling
- Linked List: insert at head/tail, delete, search, display
- Searching: linear vs binary search on a sorted dataset, with steps and time
- Graph: add vertex, add edge, display, BFS, DFS, path check, sample graph
- Performance Comparison: search, insert-at-front and graph traversal tables
- Display All Results: shows everything recorded during the session
- Input validation on every menu and number input

## Project Structure
```
src/
  Main.java                 main menu and integration
  InputHelper.java          input validation
  ResultsLog.java           stores results for option 8
  ArrayOps.java             array component
  StackDS.java              stack component
  QueueDS.java              queue component
  LinkedListDS.java         linked list component
  Searching.java            linear and binary search
  Graph.java                graph, BFS, DFS
  PerformanceAnalyzer.java  performance comparison
```

## How to Run
1. Install JDK 17 or newer.
2. Open a terminal in the project folder.
3. Compile:
   ```
   javac -d out src/*.java
   ```
4. Run:
   ```
   java -cp out Main
   ```

## Complexity Summary
| Operation | Complexity |
|---|---|
| Linear search | O(n) |
| Binary search (sorted data) | O(log n) |
| Array insert/delete (shifting) | O(n) |
| Linked list insert at head/tail | O(1) |
| Linked list search/delete | O(n) |
| Stack push/pop/peek | O(1) |
| Queue enqueue/dequeue/peek | O(1) |
| BFS / DFS | O(V + E) |

