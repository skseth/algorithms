
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



#Red-Black Trees
##Invariants
* Each node red or black
* Root is black
* No 2 reds in a row - means parent of any red node has to be black
* Every root-null path has same no of black nodes - every unsuccessful search sees same no of black nodes

##Example
* A chain of length 3 cannot be a red-black tree 

    1 --> 2 --> 3 -- null
    |
    null

##Height Guarantee : Height of red-black tree <= 2 log n

If every root-null path has at least k nodes, tree is completely filled in upto k-1 nodes

Size n of the tree must be at least 2^k-1

=> k <= log (n + 1)

=> every root-null path has <= log (n + 1) black nodes

=> every root-null path <= 2 log (n+1) total nodes
(assuming tree is balanced)

##Rotation



