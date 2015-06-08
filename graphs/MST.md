##Minimum Spanning Tree
Given Graph G = (V,E) --- undirected, connected

Proof will assume edge costs are distinct

##Properties of Graphs (useful for MSTs)

###Empty Cut Lemma
    Graph is not connected if and only if there is a cut of size 0 - there exists cut (A,B) with no crossing edges.
    <= proof : if cut A has no crossing edges, choose u in A, v in B. no path => not connected.
    => proof : graph is not connected. Then there exist u,v with no path. Choose A = {vertices reachable from u}. B = V - A. No edges crossing.

###Double-Crossing Lemma
    If a cycle has a crossing edge in cut (A,B) it must have at least one other crossing edge.
    In fact, every cycle cross a cut even no of times, which may be 0.

###Lonely-Cut Corollary
    If e is the only edge crossing a cut then it is not in any cycle.

###Cut Property
Let e be an edge of a Graph G. Further, assume there exists a cut of (A,B) of G, such that e is the cheapest edge crossing the cut.

Then e is in the MST. This will be proved later.



##Prim's Minimum Spanning Tree Algorithm

A greedy algorithm.

##MST and Applications
Connect a bunch of points together as cheaply as possible.

Clustering
Networking

##Greedy Algorithms
Prim : 1957, Djikstra : 1959, Jarnik : 1930
Kruskal's : 1956

O(m log n) - almost linear

##Problem Statement
MST - only undirected.
Input : G = (V,E), ce = cost of edge. Can be +ve or -ve.
Output : Minimum-cost spanning tree (
                        cost = sum of edges, 
                        span = connected
                        tree = no cycles)

For directed, these are called optimal branching problem.

Assumptions: input graph g is connected. can check in DFS, BFS before starting.
             This leads minimum spanning forest - not covered here.
             edge costs are distinct.
             When distinct assumption is removed, multiple MSTs may exist.

No correctness proof for duplicate edge costs.

###Prim's MST

initialize

    X = [s]
    T = 0
    Invariant : X = vertices spanned by T

loop

    while X <> V:
      let e = (u,v) = min length edge of G with u in X, v in V-X
      add e to T
      add v to X

correctness
    I. T is a spanning tree
    II. T is a minimum spanning tree

    I. Prim's MST outputs an Spanning Tree
        Algorithm maintains invariant : 
            base case X = {} - true
            Xi+1 = Xi U v, and Ti+1 = Ti + (u,v)
        Can't get stuck with X <> V
            If we did, then (X, V-X) is an empty cut. By Empty Cut Lemma, that can't happen
        Never creates cycles in T
            T has no cycles.
            By induction. 
                Base case - true.
                Before an iteration, T only edges for vertices in X, and has no edge to V-X. When e is added to T, it is the only edge from X to V-X in T, because u is in X, and v is in V-X. Lonely Cut => creates no cycle in T. 

    II. Prim's Algorithm Outputs a Minimum Spanning Tree


        Cut-Property - Statement:
            Given a cut (A,B) such that e is the cheapest edge of G that crosses it.
            Then e belongs to the MST of G.

        Consider output of algorithm to be T*
            At each iteration, an edge with minimum cost crossing (X, V-X) is chosen. So every edge of T* belongs in MST.

    III. Cut-Property is true
        Assume there is edge e crossing (A,B), which is cheapest for that cut, but is not part of MST T*.
        When we add e, it must create a cycle in T* in which e participates. By Double crossing lemma, this cycle must contain an edge e' of T* which cross A,B. Since e is less than e', we swap. This new T' is a spanning tree. And T' costs less than T*. QED.

implementation

    Use heap. O(m log n)
    like dijkstra, but store cheapest edge, not dijkstra's greedy criterion.
    maintaining invariants:


##Kruskal's MST
###Assumptions



###Pseudo-Code



###Proof of Correctness

T* = output of Kruskal's algo on input graph G

1. Clearly T* has no cycles
2. T* is connected. Why?

First edge selected, crossing a cut cannot create a cycle (Lonely-Cut property). Hence a spanning tree.

Every edge selected satisfies cut-property. Proof :

Consider where edge (u,v) is added to current set T. T U (u,v) has no cycle. 

If u and v are connected in T, then it would be a cycle. So u and v must be in disconnected parts of T (think of a graph containing all vertices of G, but only edges of T). Now consider a A, B where the components of T containing u and v are on opposite sides. We can always form such sets by taking the component of T containing v on one side, and all the other components of T on the other. Of all the edges crossing this cut, (u,v) is the cheapest one, and hence belongs to the MST by the cut property.

###Implementation using Union-Find

Maintain a partition

Find(x) -- name of group

Union(ci, cj) - fuse group

Maintain a leader for each vertex. Same leader for all connected vertices.
Maintain count of no of nodes for a leader.

Every time a node is merged, the population it belongs to doubles. Thus a node may be merged at most log2 n times. 

O(m log n) for sorting
O(m) for cycle checks
O(n log n) for leader pointer updates

O(m log n) total

###State of the Art in  MST Algorithms

Q : Can we do better than O(m log n)

O(m) if randomized [ Kargan-Klein-Tarjan JACM 1995]

O(m invAckerman(n)) if deterministic

invAckermann much slower log*(n) - no of times 

Wierdest of All :
[Pettie/Ramchandran Jach 2002] : 

MST Verification problem : 

further reading : Eisner 97

##Clustering

max-spaced k-cluster

Kruskal's algorithm, stop with k clusters

This approach is called Single-link clustering 

Proof of correctness :
    Assume another clustering exists which is different from output of greedy algorithm.

    There must be p,q in same cluster in greedy algo, which are separated in alternative cluster. Let S = spacing of greedy algo.

    If p,q are directly compared and added to same cluster, then d(p,q) <= S. But that means alternative clustering is worse.

    If p,q are indirectly added to same cluster.



###Question 1

Suppose we are given a directed graph G=(V,E) in which every edge has a distinct positive edge weight. A directed graph is acyclic if it has no directed cycle. Suppose that we want to compute the maximum-weight acyclic subgraph of G (where the weight of a subgraph is the sum of its edges' weights). Assume that G is weakly connected, meaning that there is no cut with no edges crossing it in either direction.
Here is an analog of Prim's algorithm for directed graphs. Start from an arbitrary vertex s, initialize S={s} and F=∅. While S≠V, find the maximum-weight edge (u,v) with one endpoint in S and one endpoint in V−S. Add this edge to F, and add the appropriate endpoint to S.

Here is an analog of Kruskal's algorithm. Sort the edges from highest to lowest weight. Initialize F=∅. Scan through the edges; at each iteration, add the current edge i to F if and only if it does not create a directed cycle. 

a <-- b ----> c
\     |      /
 \--> d <---/








