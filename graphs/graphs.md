#Kargers Min-Cut Algorithm


#BFS - Breadth First Search


#DFS - Depth First Search


#DAG
* has at least one sink vertex

#Topological Ordering
* for a DAG, topological ordering

#Strongly Connected Components
* kosarajus algo

#Family Tree Visualization
http://vis.berkeley.edu/courses/cs294-10-sp10/wiki/images/f/f2/Family_Tree_Visualization_-_Final_Paper.pdf

#Structure of Web
[Structure of the Web](http://www.cis.upenn.edu/~mkearns/teaching/NetworkedLife/broder.pdf)
Small World Property - Core exhibits this. Six degrees of separation. http://en.wikipedia.org/wiki/Small-world_experiment

Networks, Crowds and Markets - Easley & Kreinberg

#Djikstra Algorithm
* Assumes there is a path
* lengths are positive

#data structures
lists
stacks
queues
heaps
search trees
hash tables
bloom filters
union-find


List : 
add at head - O(1)
add at tail - O(n)
insert in middle - 

Stacks :
push - add at head - O(1)
pop - remove from head - O(1)

Heap:
Insertion - O(log n)
Extract-Min - O(log n)
Heapify - O(n) - linear 
Delete - O(log n) 

Objects are comparable, heap is a container
e.g Selection Sort - O(n) linear scans, O(n^2) - 

Priority Queue - heap with timestamp as keys
Djikstra 

Sorted Array:
Search O(log n)
Select O(1)
Min / Max O(1)
Pred / Succ O(1)
Rank - like search - O(log n)
Output in sorted order - O(n)
Insertions / Deletions - O(n)

Balanced Binary Search Tree: like sorted array, + insertions and deletions
Search O(log n)
Select O(log n)
Min / Max O(log n)
Pred / Succ O(log n)
Rank - like search - O(log n)
Insertions / Deletions - O(log n)
Output in sorted order - O(n)

alternatives :
HashTables - insertions / deletions, lookup
ith order statistic

EVL trees
red black trees:
   height <= 2 log n+1
   4 invariants : every node is red or black, root is black, no 2 consecutive nodes are red, path from root to NULL has same no of black nodes
rotations : left rotation : parent x and right child y

Left Rotation

        x
    A         y
           B      C

    A < x < B < y < C

            y
        x       C
    A       B

Right Rotation

            x
        y       C 
    A       B 

            y
        A       x           
            B       C


splay trees
B-trees, B+ trees

#Djikstra Algo Problem
The file contains an adjacency list representation of an undirected weighted graph with 200 vertices labeled 1 to 200. Each row consists of the node tuples that are adjacent to that particular vertex along with the length of that edge. For example, the 6th row has 6 as the first entry indicating that this row corresponds to the vertex labeled 6. The next entry of this row "141,8200" indicates that there is an edge between vertex 6 and vertex 141 that has length 8200. The rest of the pairs of this row indicate the other vertices adjacent to vertex 6 and the lengths of the corresponding edges.

Your task is to run Dijkstra's shortest-path algorithm on this graph, using 1 (the first vertex) as the source vertex, and to compute the shortest-path distances between 1 and every other vertex of the graph. If there is no path between a vertex v and vertex 1, we'll define the shortest-path distance between 1 and v to be 1000000. 

You should report the shortest-path distances to the following ten vertices, in order: 7,37,59,82,99,115,133,165,188,197. You should encode the distances as a comma-separated string of integers. So if you find that all ten of these vertices except 115 are at distance 1000 away from vertex 1 and 115 is 2000 distance away, then your answer should be 1000,1000,1000,1000,1000,2000,1000,1000,1000,1000. Remember the order of reporting DOES MATTER, and the string should be in the same order in which the above ten vertices are given. Please type your answer in the space provided.

IMPLEMENTATION NOTES: This graph is small enough that the straightforward O(mn) time implementation of Dijkstra's algorithm should work fine. OPTIONAL: For those of you seeking an additional challenge, try implementing the heap-based version. Note this requires a heap that supports deletions, and you'll probably need to maintain some kind of mapping between vertices and their positions in the heap.










