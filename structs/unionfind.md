###Lazy Unions

###Union Rank

Max no of hops to get from leaf to root

Merge shallow into deep tree

Rank goes up only if 2 equal trees are merged

Properties :

Max rank = worst-case of find = O(log n)

Only rank of roots go up. Once you are a child, you never become a root. 

Ranks strictly increase along path to root

Rank lemma : Consider an arbitrary sequence of union ops. For every non-negative integer r, there are at most n/2^r objects with rank r

Any 2 nodes of same rank have disjoint subtrees : because if not, they are on the path of some node to its root and one is ancestor of another.

Any node of rank r has 2^r children : Induction : base case 0. Assume true for r. When another item of rank r is merged, its rank goes up. But each of these have 2.2^r children = 2^r+1. QED.

=> find takes - O(log n)

###Path Compression
After find(x), update parent pointers.

Rank manipulation - leave unchanged.

###Hopcroft-Ullman Theorem, then Tarjan : lazy, union-by-rank, path compression

O(m log* m) - inverse of tower

Amortized.

Progress measure : rank[parent(x)] - rank[x]

Rank Blocks : 0, 1, {2,3,4}, {5..16}, {17..2^16},...{n}. No of rank blocks : 
O(log*n). Object x is good if x or x's parent is root OR rank[parent[x]] is in higher rank block than rank[x].

Every find visits only O(log*(n)) good nodes. A node of rank to become good is <= 2^k, when its parent is sure to enter next block.

Total no of objects with x with final rank in this rank block is Sum(i=k+1 to 2k) n/2^i <= n / 2^k

###Tarjan '75 Analysis

O(m a(n)) guarantee. a(n) = inverse ackermann function.

Grows slower than inverse Ackermann function.

For all integers k >= 0 and r >= 1

Base : A_0(r) = r + 1 for all r >= 1
In general : for k,r >= 1
A_k(r) = Apply A_k-1 r times to r
Grows fast (!)

Inverse : alpha(n) = min value of k such that A_k(2) >= n

a(n) = 1 for n = 4
a(n) = 2 for n = 5..8
a(n) = 3 for n = 9..2048
a(n) = 4 for upto a tower of 2048 2's


Analysis :

Define delta(x) for non-root object = 
                max k such that rank[parent[x]] >= A_k[rank[x]]

A1 = 2r
A2 = r2^r
For all objects with rank[x] >= 2, delta(x) <= alpha(n)

Bad object : not root, not direct child of root, rank(x) >= 2, x has 



Design and Analysis of Algorithms - Kozen








