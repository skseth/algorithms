#Data Structures

##Heaps

Heap Property : 
Key[X] <= keys of all X's children

* Relations
parent(i) = i/2 for i even
            floor(i/2) for i odd
left(i) = 2i
right(i) = 2i + 1

* Important Sub-procedures
Heapify - O(log n)
    correctly position a node whose child sub-trees are heaps
    like bubble down
Bubble-Up - O(log n) 
    bubble up a node which may be more than parent

* Creation
Build-Heap - O(n)

* Modification of Heap
Delete - O(log n)
    replace node with last node, reduce heap length by 1
    if last node smaller, bubble up
    if last node bigger, bubble down

Insertion - O(log n)
    add node at end, increase heap length by 1
    bubble up

Extract-Min or Extract-Max - O(log n) - but not both
    delete and return root node

Heap is often used as a Priority Queue - to ensure the highest priority item is at the head. 

Applications : 
Median Maintenance - maintain a list of medians for streaming incoming data, using 2 heaps.
Selection Sort - O(n log n) - heapify, then 
Djisktra's Shortest Path

Complexity of Build-Heap and Heapify

Heapify (assuming Extract-Min)

    heapify(i)
        minidx = min(idx, left(idx), right(idx))
        if (minidx != idx) 
            swap(minidx, idx)
            heapify(minidx)


Build-Heap

    for i = n/2; i >= 1; i--)
        heapify(i)

Complexity of Heapify : height of sub-tree = O(log n) in the worst case

Complexity of Build-Heap: O(n)

    This is surprising because one would expect n/2.log n worst case for Build-Heap.
    
    Assume perfect binary tree of height h+1 levels, numbered 0 to h. Define height of node as child levels below them. Leaves are then at height 0, root at height h.

    S = Sum 2^i (h - i)
        where i = 0 to h
      = h + 2(h-1) + 4(h-2)..+2^(h-1)
    2S = 2h + 4(h-1) + .. 2^h
       = h + (h + 4(h-2) + 8(h-3) + .. 2^(h-1)) + (4 + 8 + .. 2^h-1)
    2S = h + S + Sum_0..h-1 2^i - 3
    S  = h + (2^h - 1) - 3
    But h = log n, and 2^h = n (assuming n is a full tree)
    Hence S = O(n)
    => Build-Heap will do at most n swaps

##Sorted Array

Search O(log n)
Select O(1)
Min / Max O(1)
Pred / Succ O(1)
Rank - like search - O(log n) - how many are <= given key, ith order statistic.
Output in sorted order - O(n)

Insertions / Deletions - O(n) 


##Binary Search Tree

Like a sorted array, but log n performance ofr insertions and deletions.

Search O(log n)
Select O(log n)
Min / Max O(log n)
Pred / Succ O(log n)
Rank - like search - O(log n) - how many are <= given key, ith order statistic.
Output in sorted order - O(n)
Insertions / Deletions - O(log n)

Search Tree Property : All keys less than X, in left sub-tree, all keys greater in right sub-tree.

###Alternatives to BST
Heap also supports min/max, inserts, deletes, and is a better choice if only these are needed.

HashTables - really good at insertions, searches sometimes deletions. If min/max and ordering not important, then hashtable is better with constant-time lookups.

###Height of BST

Can be anywhere from log n to n. In fact, BST operations are really O(height) rather than O(log n). A BST can degenerate into a linked list.

###Operations
Search
    if = return
    if <, search left sub-tree
    if >, search right sub-tree

Pred - first left turn of parent. 
Min - left branch

Succ / Max - opposite of Pred / Min

In order traversal
    in-order traversal of left sub-tree
    process root
    in-order traversal of left substree

Deletions

    Delete(k)
    search for k
    no children?
        delete node
    1 child
        remove node, replace with child
    2 children
        remove node. compute l = pred(k) = max(left sub tree) = left child's, then all right pointers.
        Swap l and k
        delete k (k has no right child)

Select / Rank

    Store size of each sub-tree
    size(x) = size(l) + size(r) + 1

    Select ith order statistic
        if size(l) < i
            i = i - size(l)
            if i == 1
                return current
            Select i from r
        else
            Select i from l


##Red-Black Trees
###Invariants
* Each node red or black
* Root is black
* No 2 reds in a row - means parent of any red node has to be black
* Every root-null path has same no of black nodes - every unsuccessful search sees same no of black nodes

###Example
* A chain of length 3 cannot be a red-black tree 

    1 --> 2 --> 3 -- null
    |
    null

###Height Guarantee : Height of red-black tree <= 2 log n+1

If every root-null path has at least k nodes, tree is completely filled in upto k-1 nodes

Size n >= 2^k-1

=> k <= log (n + 1)

=> every root-null path has <= log (n + 1) black nodes (4th invariant)

=> every root-null path <= 2 log (n+1) total nodes (3rd invariant)
(assuming tree follows invariant of no two red nodes in succession)

###Rotation - TBD
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

##Hash Tables
###Operations

Insert - O(1)
Delete - O(1)
Lookup - O(1)

Not really a great dictionary - ordering is a weak point of Hash Tables.

###Applications

De-duplication

2-SUM problem - unsorted array A of n integers. Target sum t. Determine if 2 numbers exist where x + y = t.

    Naive : Theta(n^2)
    Sort then search : n log n
    Hash-Tables - repeated lookups. O(n)
        Insert in hash table
        for each x, find t-x. If found return x, t-x

Symbol Tables
Blocking network traffic
Search Algorithms - e.g. game tree exploration

###Implementations


###Hash Function

U = universe of values - very huge
S = evolving set of values needing to maintained out of U
        assumed to be about size n
n = |S|, roughly. 
A = array of size n, used for storing values
hashfunction: h:U -> (0..n-1)
Store x in A[h(x)]
What if h(x) = h(y) .. you get a collision.

###Handling Collisions

Birthday paradox : given 23 people in room, 50% chance of collision - same birthday. With 57, 99%.

Chaining : linked list for colliding elements
Open Addressing : probe sequence. Saves space. linear probings, double hashing. Deletion is harder.

###What makes a good hash function
Performance depends on hash function. 
m items, n buckets
Insert is theta(1) with chaining
O(listlength) can be anything from m/n - equal length lists, to m - all in one bucket.

Best hash function 
    - like a random function. Spread data evenly.
    - should be easy to store, fast to evaluate, in constant time - this is why completely random is not possible

Bad Hash Functions
    ex. phone numbers
        Bad : first 3 digits
        Mediocre : last 3 digits - subject to patterns.
    ex. memory locations
        bad : x mod 1000
        all odd buckets guaranteed to be empty

Quick and Dirty Hash Function
    hashCode() - object to integer
    compression - integer -> 0..n-1 ... like mod n

###How many buckets?
Heuristics :
Choose n to be a prime
Not too close to a power of 2
Not too close to a power of 10

##Universal Hashing
###Pathological Data Sets
Guarantees depend on :
    good hash function
    non-pathological data

###Load Factor - alpha
alpha = #of objects / #buckets

alpha = O(1) is necessary condition for operations to run in O(1) time.

alpha should be << 1 for open addressing

For good performance, need to control alpha.

###Pathological Data Sets
Every hash function has pathological data sets for which performance is bad.

* Pigeonhole principle : there exists bucket i, such that at least |u|/|n| elements map to i. If data is only from this, full collision.

=> Data independent guarantees not possible.

[Denial of Service via Algorithmic Complexity Attacks](http://www.cs.virginia.edu/~cs216/Fall2005/notes/CrosbyWallach_UsenixSec2003.pdf)

* Solution :

Cryptographic Hashing Function - infeasible to reverse engieer pathological data set.

Use family of hash functions. Choose one at random. This is the Universal Family of Hash Function.

###Universal Hash Functions - Definitions
H = { some f | f:U -> {0..n-1}}

H is universal iff Pr_h_in_H [h(x) = h(y)] <= 1/n

example     : H = {all f:U -> {0..n-1}}
non-example : H = {fi | i = 0 to n-1, fi(x)=i}

IP Address Hash Functions

    U = IP Addresses (x1,x2,x3,x4), 0 <= xi <= 255
    Let n = prime (small multiple of #of objects in HT)
    a = 4-tuple (a1,a2,a3,a4) 0 <= ai <= n-1
    ha(x) = a.x mod n (dot-product modulo size)

    H = {ha | a = (a1,a2,a3,a4),  0 <= ai <= n-1}

Claim :This family H is universal 

    Assume: x and y are 2 ip addresses differing in last component, x4 <> y4
    ha(x1,x2,x3,x4) = ha(y1,y2,y3,y4). Also choose a1, a2, a3
    a4(x4 - y4) mod n = sum ai.(yi-xi) mod n
    Claim : a4(x4 - y4) mod n is equally likely to be anything between 0 and n-1. Assume n > 255.
        n is prime

###Analysis of Chaining

Theorem : All operations run in O(1) - Carter-Wegman
Assume : universal hash family, |S| = O(n) i.e alpha = O(1)
        takes O(1) to calculate hash

What is the work done in unsuccessful lookup?
    consider x not in S

Running Time = hash fn calculation O(1) + O(length of linked list in A[h(x)])

L = length of linked list in A[h(x)] ... random variable

for y in S, define Z_y = 1 if h(y) = h(x), else 0

L = Sum(Z_y)

E L = Sum (E Z_y)
    <= |S|.1/n ... (universal hashing definition)
    = alpha = O(1)

=> Running Time = O(1)

###Analysis of Open Addressing

probe sequence.

Assume (completely invalid), all n! probe sequences equally likely

Insert : 1 / 1 - alpha

Lookup - better than insert

Proof:
    1-alpha of finding an empty slot
    Like flipping biased coin.
    E(emptyslot) = 1 + alpha.E(emptyslot)
    E(emptyslot) = 1 / 1 - alpha

Linear Probing:
    initial probe uniformly at random.
    Theorem: 1962 (Knuth)
        Under weaker heuristic assumption
            E(InsertionTime) = 1 / (1-alpha)^2
        50% full => 4 probes
        90% full => 100 probes on average

##Bloom Filters

Howard Bloom - 1970.

pros:
fast inserts and lookups
very little space
no deletions

cons:
lookup with possible false positive.

###Applications
SpellCheckers
List of forbidden passwords
Network Routers

###Operations
Array of n bits
k hash functions - h1, h2, hk
S objects

no of bits / object = n / |S|

Insert(x)

    for i = 1..k
        A[hi(x)] = 1

Lookup(x)

    for i = 1..k
        if A[hi(x)] == 0 return false
    return true

###Heuristic Analysis
Trade off between space efficiency vs chance of false positives

Assumption : all hi(x) uniformly random and independent across different is and xs.

What is the probability first bit has not been set?

total bits set = k|S|
Probability that each bit does not set bit 1 
    = (n-1/n) ^ k |S|

Probability that first bit has been set to 1 = 1-(1-1/n)^k|S|
(remember assumption)

P <= 1 - e^(-k|S|/n)

b = n / |S| - space per object 

P <= 1 - e^(-k/b)

k/b => trade off of bits per object  vs space per object

for x not in S,
    false positive probability epsilon = (1 - e^(-k/b))^k

How to set k?
Set k optimally - for fixed b, find k that minimizes epsilon.

    k = (log 2).b = 0.693 b

epsilon(opt) = (1/2)^(ln 2)b

    b = 1.44 log 1/epsilon

Example:

    b = 8
    k = 5 or 6
    probability only 2%

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


Sorted Array:
Search O(log n)
Select O(1)
Min / Max O(1)
Pred / Succ O(1)
Rank - like search - O(log n)
Output in sorted order - O(n)
Insertions / Deletions - O(n)








