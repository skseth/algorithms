##WIS In Path Graph
A[0] = 0
A[1] = w1

for i = 2 to n
    A[i] = max (A[i-1], A[i-2] + wi)

Time : O(n)

##Knapsack Problem
A[0,0] ... A[0,W] = 0

for i = 1 to n
    for x = 1 to W
        A[i,x] = max(A[i-1,x], A[i-1,x-wi] + vi)

Final answer A[n,W]

Runtime : O(nW)

Reconstruction : 

##Sequence Alignment
Needleman-Wunsch score
penalty = no of mismatches + differences

X and Y are 2 sequences. We have three cases for last character in optimal solution :

i) xm and yn ii) xm and gap iii) gap and yn

Let X' = X - xm, and Y' = Y - yn

Then, we have 
i) opt(X',Y') + alpha(xm,yn) ii) opt(X',Y) + 1 iii) opt(X,Y') + 1

Proof : that induced subalignments are themselves optimal can be proved by contradiction.

A(i,0) = A(0,i) = i*alpha(gap), for i >= 0

for i = 1 to m
    for j = 1 to n
        A[i,j] = min (A[i-1,j-1] + alpha(xi,yj), A[i-1,j] + alpha(gap) + A[i,j-1] + alpha(gap))

Running time : O(mn)
Reconstruction : O(m+n) time

##Optimal Binary Search Tree

preserve search tree property

frequency of search p1,p2..pn , items 1..n

C(T) = Sum wi*(depth of i + 1)

Example : Red-black tree, pi are 1, then C(T) = log n

Huffman code were prefix free, this is not the case here.

Let r be root

T1 (left subtree) is optimal for 1..r-1, T2 (right sub-tree) is optimal for r+1..n

Proof : Assume you replace T1 with T1* such that C(T1*) < C(T1). We can show that 
C(T) = Sum pi + C(T1) + C(T2)

Thus by replacing T1 by T1*, we get C(T*) < C(T) violating optimality of T.

for every 1 <= i <=j <= n
    Cij = Sum(k=i to j) Pk  + min (r = i to j) { Ci,r-1 + Cr+1,j}

Final answer is is C1n

Algo :
A = 2-D Array

For s = 0 to (n-1)
    for i = 1 to n
        A[i,i+s] = min (r = i to i+s) { SumPk + A[i,r-1] + A[r+1,i+s]}

Running time : theta(n^3)

Knuth, Yao showed optimized version allowing O(n^2) running time.

##Bellman-Ford

Allows negative edge-lengths. No negative cycles (NP-Hard problem)

Key insight : We grow paths by length, extending by one at a time

For v in V, i in { 1...n-1}, let
    P = shortest s-v path with at most i edges

Case 1 : P has <= i-1 edges, then P is shortest such path
Case 2 : P has exactly i edges with P = P' + (w,v), then P' is shortest (s,w) path with <= i-1 edges

Algorithm

A[0,s] = 0, A[0,v] = inf for v <> s
for i = 1 .. n-1
    for each v in V
        A[i,v] = min { A[i-1,v], min A[i-1,w] + Cwv}, where w is over all incoming edges to v

Running Time : Theta(mn)

Detecting Negative Cost Cycle : Do one more iteration. If A[n,v] = A[n-1,v] for all vertices, then there is no negative cost cycle.

##APSP - All Pairs Shortest Path

###Non-Negative Edge Lengths
APSP - non-negative : n.Dijkstra = O(nmlogn) 

Sparse graphs - n.Dijkstra good = O(n^2 log n)

Dense graphs - n.Dijkstra = O(n^3 log n)

###Negative Edge Lengths

Bellman-Ford n times - with dense graphs, this is O(n^4)

###Floyd-Warshall Algo

O(n^3)

Optimal Substructure : Restrict no of vertices. 

Order V. Vk = {1, 2, ... k} of the vertices

Let P = shortest i-j path with all internal nodes in Vk. Note that i,j may not be in Vk.

Case 1 : If k is not in P, then P is shortest i-j path in Vk-1
Case 2 : If k is in P, then P = P1 + P2, where P1 = shortest i-k path in Vk-1, and P2 = shortest k-j path in Vk-1, cycle free.

Proof : 
Case 1 : Only way to make this shorter would be to have k in P. Since this is not the case, P remains same as for Vk-1.

Case 2 : In Vk-1, P1 is shortest path from i to k (by definition) and P2 from k to j. If an i-j path containing k P* = P1* + P2* exists such that l(P*) < l(P1) + l(P2), then either l(P1*) < l(P1), or l(P2*) < l(P2), or both. But this would imply there is a shorter i-k, or k-j path in Vk-1, contradicting our assumption.

Algo :

A[i,j,0] = 0 of i = j, cij, when edge (i,j) exists, and inf, if no such edge exists.

For k = 1 to n
    for i = 1 to n
        for j = 1 to n
            A[i,j,k] = min { A[i,j,k-1], A[i,k,k-1] + A[k,j,k-1]}

Running Time : Theta (n^3)

Negative Cost Cycle : If any diagonal A[i,i] < 0

Answer : A[i,j,n] is final answer if no negative cycle

Reconstruction : B[i,j] = largest numbered node on shortest path between i and j

###Johnson's Algorithm

Reduces APSP to 1 invocation of Bellman-Ford, then n invocations of Dijkstra.

O(mn) + O(nmlogn) = O(n m log n)

Reweighting 

let p(v) = weight of vertex v. For an edge e = (u,v), the new weight :

    c' = c + p(u) - p(v)

For a path s-t,

    l' = l + p(s) - p(t)

reweighting changes all paths change by exactly same path.

Algo :

    Add vertex s, connected via path length 0 to all vertices.
    Run bellman-ford
    set p(v) = shortest s-v path calculated by bellman-ford

    c' = c + p(u) - p(v) is always >= 0 i.e. c + p(u) >= p(v). Otherwise , giving a new shortest path to v, or we have a negative cost cycle.

    Now, run Dijkstra's n times, to get d'(u,v)
    final answer d(u,v) = d'(u,v) + p(u) - p(v)

Running Time :
    O(n) (form G') +
    O(nm) (Bellman Ford) +
    O(m) (reweighting) +
    O(n m log n) (dijkstra n times) + 
    O (n ^ 2) - for each u,v pair, recalculate lengths

    = O(n m log n)

Better than Floyd-Warshall for sparse graphs
