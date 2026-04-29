# Graphs - DSA Practice Problems

A collection of practice problems and solutions covering fundamental **Graph** concepts in Data Structures and Algorithms.

---

## 📁 Folder Structure

```
Graphs_DSA/
├── README.md
├── Problem1/
│   ├── question.txt      # Social Network Connection
│   └── solution.txt
├── Problem2/
│   ├── question.txt      # Course Prerequisite System
│   └── solution.txt
├── Problem3/
│   ├── question.txt      # City Road Network
│   └── solution.txt
├── Problem4/
│   ├── question.txt      # Island Counter (2D Grid)
│   └── solution.txt
└── Problem5/
    ├── question.txt      # Network Packet Routing
    └── solution.txt
```

---

## 📚 Topics Covered

| Problem | Topic                       | Key Concepts                                         |
|---------|-----------------------------|------------------------------------------------------|
| 1       | Social Network Connection   | Adjacency List, BFS shortest path, degree of separation |
| 2       | Course Prerequisite System  | Directed Graph, Cycle Detection, Topological Sort    |
| 3       | City Road Network           | Directed Weighted Graph, DFS reachability, BFS hops  |
| 4       | Island Counter (2D Grid)    | Graph modeling, DFS/BFS connected components         |
| 5       | Network Packet Routing      | Matrix vs List, connectivity check, BFS min hops     |

---

## 🧠 Key Takeaways

- **Adjacency List** is preferred for sparse graphs (most real-world graphs)
- **Adjacency Matrix** is preferred for dense graphs and O(1) edge lookup
- **DFS** uses a stack/recursion — good for cycle detection, topological sort, path existence
- **BFS** uses a queue — guarantees shortest path in unweighted graphs
- Both DFS and BFS have **O(V + E)** time complexity
- A **bridge** is a critical edge whose removal disconnects the graph

---

## 🚀 How to Use

Each problem folder contains:
- `question.txt` — Problem statement with scenario, diagram, and tasks
- `solution.txt` — Detailed step-by-step solution with traces and diagrams

---

*Based on DSA lecture content on Graphs: Terminologies, Representations, DFS, and BFS.*
