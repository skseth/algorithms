#Graph
Pairwise dependencies between objects.

Vertices / Nodes (V) - set of vertices.
Edges / Arcs - directed / undirected, head/tail

#Examples
Road Networks, the web, social networks (which are directed, undirected?), precedence constraints

Network bottlenecks
community detection in social network
image segmentation - input graph of pixels.

Connected graphs : Max no of edges nC2. Min : n-1
n - # of vertices, m - no of edges

#Minimum Cut of a Graph
MinCut
Allow parallel edges

How many cuts does a graph with n vertices have? 2^n - 2

#No of Edges 
For a connected graph of n vertices, there are a minimum of n-1, and no more than nC2 (assuming no parallel arcs)

In a directed graph with no parallel arcs in same direction, you could have 2.nC2 

#Spare vs Dense Graphs

n = # of nodes, m = # of edges

sparse graph, m is O(n) or close to it
dense graph, m is closer to theta(n^2)

#Graph Representations
##Adjacency Matrix
Adjacency matrix - A_ij : 1 => edge exists
Needs n^2 space. Good for dense graph.

##Adjacency List
Adjacency list - edgx`

Array (or list of vertices)
Array (or list of edges)
Each edge points to its endpoints
Each vertex points to edges incident on it.

    Space usage = Theta(m + n)

Linear in size of the graph

Web graph : 10^10 nodes, 10^11 edges

#Kargers Randomized Contraction Algorithm for Min-Cut Problem

##Algorithm
While there are more than 2 vertices
    pick a remaining vertex at random
    merge, or contract, vertices u and v into single vertex
    remove self-loops
return cut represented by final 2 vertices

##Algorithm Success

Karger's algorithm can fail. What is the probability of success?

Assume that (A,B) is a minimum cut of a graph, where A and B are sets of vertices. Note that this is only one of many possible minimum cuts. 

Let F be set of edges from A to B.

k = #F - no of edges in the minimum cut

Question: What is the probability that we discover the minimum cut (A,B)?

Algorithm fails if we collapse an edge of F. So success => going through the entire algorithm without collapsing one of the k edges in F.

###Probability of finding specific min-cut Ai, Bi
m = no of edges
n = no of vertices
k = no of crossing edges, crossing Ai to Bi
F = {crossing edges}
Pr[Output is Ai,Bi] = Pr[never eliminate an edge of F]


Key observation : deg(vertex) >= k for each vertex 
or in-degree(vertex) + out-degree(vertex) >= k for each vertex (directed)
Sum(deg(vertex)) = 2m (for undirected)
Sum(in-degree(vertex) + out-degree(vertex)) = 2m (for directed)
Sum(deg(vertex)) >= kn for undirected, 
Sum(in+outdegree(vertex)) >= kn for directed
Hence, 2m >= kn, for all graphs
m >= kn/2 


Let Si = event that edge of F is contracted in iteration i
P = success probability = Pr[!S1 n !S2 n !S3 ... !Sn]
Pr(S1) = k/m
Pr(!S1) = 1-k/m

Since m >= kn/2
 
Pr(S1) = k/m <= 2/n  

Pr(!S1 n !S2) = Pr(!S2|!S1).Pr(!S1)

Second general observations:
Any contracted node in the graph is still a sort of cut, and has at least k edges.
Hence, all degrees are <= k.
Pr(!S2|!S1) = k/m' <= 1-2/n-1 (1-1/n-1) 
Pr(!S1 n !S2) = (1-2/n-1).(1-2/n) 

P = Pr[!S1 n !S2 n !S3 ... !Sn-2]
  = Pr[!S1].Pr[!S2 n !S3 ... !Sn-2|!S1]
  = Pr[!S1].Pr[!S2|!S1].Pr[!S3..!Sn-2|!S1 n !S2]
  ...

P = (1-2/n)(1-2/n-1)(1-2/n-2)...(1-2/n-(n-4)).(1-2/n-(n-3))
  = (n-2/n)(n-3/n-1)(n-4/n-2)...(2/4)(1/3)
  = 1.2/n(n-1) = 1/nC2 >= 1/n^2

This is the probability is of finding a given min cut in the one trial, which is >= 1/n^2.

Now, Let T_i = event that the cut (A,B) is found on ith try.
By definition trials are independent.
Pr[All n trials fails] = Pr[!Ti n !T2...n !TN]
                       = Pr[!T1]*Pr[!T2]..
                       <= (1-1/n^2)^N

For all real numbers, 1 + x <= e^x

Let N = n^2. 
Pr[All Fail] <= (e-1/n^2)^n^2 = e^-1 = 1/e = nearly 30%

If we do n^2.log n trials, then :
Pr[All Fail] = (1/e)^log n = 1/n

###Running Time
Brute force = 2^n (number of cuts)

Running Time = polynomial in n and m, but slow = O(n^2.m)

More optimizations possible to improve performance.


graph may have more than 1 min cut.
e.g. trees have n-1 cuts, one per edge.

What is the largest no of min cuts : ? No between : n-1 to 2^n
Ans : nC2

n-cycle: remove pair, one cut. nC2.

#Graph Search
Find all vertices reachable from given start vertex
Dont explore anythin twice (O(m+n) time)

##Generic Algorithm
initially s explored, everything else unexplored
while possible:
    choose edge (u,v) with u explored, v unexplored
    mark v explored

##Proof of Correctness
Statement : 
All reachable nodes from s are explored.

Proof by contradiction:
Assume Path P exists from s to v, but v is not explored.

s--> x --> x ---> x --- v

s - explored, v - unexplored

Let (u,w) be an edge in P, where u is explored, and w not explored
=> our algorithm terminated without (u,w) being unexplored
=> contradiction!

##BFS - Breadth First Search 

BFS - explores in layers
Uses Queues
Useful for shortest path algorithms

queue.add(s)
while !queue.empty
    u = queue.next
    mark u explored
    for each edge (u,w)
        if !explored(w) queue.add(w)

O(m+n)

Application of BFS : dist(v) - shortest distance from s to v

    dist(all nodes) = infinity
    dist(s) = 0
    queue.add(s)
    while !queue.empty
        u = queue.next
        mark u explored
        for each edge (u,w)
            if !explored(w) 
                queue.add(w)
                dist(w) = dist(v) + 1

Application : Undirected Connectivity
Compute all connected components in undirected graph
Formal definition : 
equivalence classes of relation u ~ v <=> there exists path from u to v

    For i = 1 to n
      if i not explored
        BFS(G,i)

O(m+n)

##DFS - Depth First Search
DFS - aggressive. Only backtrack on failure 
Uses Stacks
Creates a topological ordering of a DAG - directed acyclic graph
Strongly Connected Components in directed graph
O(m+n)

DFS-loop(G,s)

    mark s as explored
    for each edge (s,v)
        if !explored(v) DFS-loop(s)

###Topological Ordering

An labelling f of nodes of a directed graph G, such that: 
i) f(v)'s are the set {1,2,...n}
ii) (u,v) in G implies f(u) < f(v)

O(m+n)

Motivation:
sequence tasks respecting all precedence constraints. E.g. an ordering of courses such that directed edge represents prerequisites.

Graph must be acyclic - otherwise topological ordering not possible.
Every acyclic graph has a sink vertex - a DAG has a vertex with no outgoing arcs. This can be proved by contradiction - assume every vertex has outgoing arc - keep following arcs till we get a repeat (pigeonhole principle)

Only candidates for final vertex, must be a sink vertex.

DFS(graph G, startvertex S)
   mark s as explored
   for each edge (s,v)
      if !explored(v) DFS-loop(G,v)
   f(v) = current_label
   current_label--

DFS-loop(G)
  mark all nodes
  current_label = n
  for each vertex v in G
    DFS(G,v)


Proof of Correctness:
If (u,v) is an edge, then f(u) < f(v)

Case 1 : Assume u is visited first:
v's recursive call finishes before u, hence f(v) > f(u)
Case 2 : Assume v is visited first
    Graph has no cycle. Hence there is no path from v to u.
    recursive call of v finishes and returns to DFS-loop before u.
    f(v) < f(u)

#SCC - Kosaraju's Algorithm

Strongly Connected Components of a directed graph G are the equivalance classes of the relation u ~ v, where :

u ~ v <=> there exists path u --> v, and v --> u, in G

transitive - becauses if u ~ t, and t ~ v, then u ~ v
reflexive - because u-->u always exists
symmetric - by definition


DFS-Loop

    t = 0 // finishing time
    s = null // leader
    for i = n to 1
        if node i is not explored
            DFS(G,i)

DFS(G,n)

    leader(n) = s // only important for second pass
    mark s as explored
    for each edge (s,v)
      if !explored(v) DFS-loop(G,v)
    t++
    finishing(v) = t
    

Kosaraju

    Grev = G with edges reversed
    DFS-Loop(Grev)
    Grelabeled = G with nodes renumbered by finishing times
    DFS-Loop(Grelabeled)
    // Now SCC for node v = leader(v)

Running Time = 2. O(m+n)

##Correctness of Kosaraju Algorithm

Claim : The SCCs form a DAG, an acyclic meta-graph. 
Meta-nodes = SCCs, C1, C2, Ck of graph G.
Meta-Arcs : C1 --> C2 iff there exists some i in C1, and j in C2, 
                                such that i --> j

Why Acyclic? If C1 --> C2, and C2 --> C3, and C3-->C1 then they become one SCC.

Claim : Reversing graph does not change the SCCs
Follows from definition of DAG.

Lemma : Consider two adjacent SCCs C1 and C2 in G, with C1 --> C2
Then, the finishing times : max f1(v) for C1 < max f1(v) for C2

    In reverse graph, C2 --> C1
    Consider when 1st time a node from C1 or C2 is explored.

    Case 1 : 1st node is in C1
    DFS will result in all nodes in C1, as there is no path starting from v in C1, and reaches C2, since Grev is a DAG.
    Finishing times of all nodes in C1 will be less than any node in C2.

    Case 2 : 1st node is in C2
    Then, there is an edge i --> j, with i in C2, and j in C1. 
    DFS(Grev, v) will not return until all nodes in C1 and C2 are fully explored.
    f(v) > any nodes in C1 and C2 
    => f(v) > max f(u), when u is any node in C1.

Corollary of Lemma : Maximum finishing time in entire graph has to be in a sink
Proof-by-contradiction: Assume largest finishing time is not sink. Follow outgoing arc. Contradiction.

    By this corollary, in second pass, we always start with the sink. Then next pass will be on the residual graph, which again will contain a sink. Reverse topological order w.r.t. DAG of meta-SCC.


#Djikstra Algorithm - Single Source Shortest Path

Directed Graph G = (V,E)
m = |E|, n = |V|. Each edge has length le

Assume : 
1) There is a directed path from s to every v (convenient, but no big deal)
2) Non-negative lengths - very important

Q Why not do BFS with replacing le by unit length edges. 
A Will blow up size of graph, being much slower

Q Why not convert negative to positive by adding a large constant
A different paths have different lengths - changes which paths may be shortest

Algorithm

    X = {S}
    A[s] = 0 //estimate of shortest path distance
    B[s] = ()
    while X <> V
      Djikstra's Greedy Criterion : 
      among all edges (v,w) in E with v in X, w in V-X
        pick the one with smallest score of A[v] + l(v,w)
      Let this be (v*,w*)
      Add w* to X
      A[w*] = A[v] + l(v*,w*)
      B[w*] = A[w*] U (v*,w*)


Correctness

    Theorem [Djikstra] - For every directed graph G with non-negative lengths, the algorithm calculates shortest distance from given source node s.

    i.e. A[v] = L(v) for all v in V.

    Proof (By induction)
    Base Case : 
    A[s] = 0, B[s] = empty path (correct assuming non-negative edge lengths)
    P(1) = true

    Inductive Step:
    for all v in X, A[v] = L(v) and B(s) is true shortest-path
    We pick (v*,w*) following Djikstra's Greedy Criterion

    X = X U {w*}
    A[w*] = A[v*] + l(v*,w*) = L(v*,w*) + l(v*,w*)
    B[w*] = B[v*] U l(v*,w*)

    Let P = any (s,w*) path. Then path must have at least one node in X, and one in V-X. P = s ---> y ---> z ---> w*, where (y,z) is first edge where y is in X, and z is in V-X. Note y may be s, z may be w*.

    Assuming path length > 0
    l(P) = l(s,y) + l(y,z) + l(z,w*) 
         >= L[y] + l(y,z) + l(z,w*) 
         >= A[y] + l(y,z)
    
    y in X, z in V-X
    By Djikstra's Greedy Criterion,
    A[w*] = A[v*] + l(v*,w*) <= A[y] + l(y,z)
    => A[w*] <= length of any other path P from s to w*
    => A[w*] is the shortest path from s to w*

Djikstra - Implementation Using Heap

    Naive implementation : O(m.n)
    n-1 iterations of while loop
    m edges - linear scan

    Heap : extract-min, insert - O(log n)
    heap property : key <= children's keys
    insert via bubbling up

    Invariant#1 : Elements in heap = vertices of V - X
    Invariant#2 : Element at top of heap = element with lowest estimate

    For maintaining Invariant#2, we need to be clever:
    key[v] = smallest djikstra greedy score of any edge (u,v) in E with v in X
           = infinity if no such edge exists
    Searching for lowest score is broken into steps

    w = extract-min 
    for each edge (w,v)
        if A[v] > A[w] + l(w,v)  // this also ensures v is not in X
            delete(v)
            A[v] = A[w] + l(w,v)
            B[v] = B[w] U (w,v)
            insert(v)

Running time :

    n-1 extract-min (log n)
    for each edge (m in all)
        delete-insert combo 
    #of heap operations = O(n + m) = O(m) for weakly connected graph
    running time = O(m log n)









































* Assumes there is a path
* lengths are positive

#Structure of Web