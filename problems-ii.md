##Problem Set I

###We are given as input a set of n requests (e.g., for the use of an auditorium), with a known start time si and finish time ti for each request i. Assume that all start and finish times are distinct. Two requests conflict if they overlap in time --- if one of them starts between the start and finish times of the other. Our goal is to select a maximum-size subset of the given requests that contains no conflicts. (For example, given three requests consuming the intervals [0,3], [2,5], and [4,7], we want to return the first and third requests.) We aim to design a greedy algorithm for this problem with the following form: At each iteration we select a new request i, including it in the solution-so-far and deleting from future consideration all requests that conflict with i. Which of the following greedy rules is guaranteed to always compute an optimal solution?

Solution 1 : Choose request with fewest number of conflicts, breaking ties arbitrarily

Counter-Example:

    __3__  __4__  __4__  __3__
     __4__  __2__  __4__
     __4__              __4__
     __4__              __4__

     Example from Vukasinovic.    

Solution: Choose request with earliest finishing time.

Proof :

fi = finishing time of i, si = start time of i

Suppose we choose a with minimum finishing time. Assume this eliminates b, conflicting with a. We know fb > fa. Can fa eliminate any task which fb does not eliminate? If c is any task eliminated by a, then fc > fa, but sc < fa => c is running at time fa. But b must also be running at fa, because of the same reason => b eliminates c. Thus choosing b over a would resulting in eliminating at least as many tasks as b, and possibly more. So a is best choice out of all its conflicting tasks.


###We are given as input a set of n jobs, where job j has a processing time pj and a deadline dj. Recall the definition of completion times Cj from the video lectures. Given a schedule (i.e., an ordering of the jobs), we define the lateness lj of job j as the amount of time Cj−dj after its deadline that the job completes, or as 0 if Cj≤dj. Our goal is to minimize the maximum lateness, maxjlj. Which of the following greedy rules produces an ordering that minimizes the maximum lateness? You can assume that all processing times and deadlines are distinct.

Solution : 

Let p1 be task with minimum deadline dmin, and p2 be any other other task with deadline d2 > dmin. If we choose p1, then delay1 = max(p1 - dmin, p1 + p2 - d2). If we choose p2, then delay2 = max(p1 + p2 - dmin, p2 - d2)

p1 + p2 - d2 <= p1 + p2 - dmin, because dmin < d2
p1 - dmin <= p1 + p2 - dmin 
p2 - d2 <= p1 + p2 - dmin, because dmin < d2, and p1 > 0

Thus, delay1 <= delay 2

###Consider an undirected graph G=(V,E) where edge e∈E has cost ce. A minimum bottleneck spanning tree T is a spanning tree that minimizes the maximum edge cost maxe∈Tce. Which of the following statements is true? Assume that the edge costs are distinct.

Both are Spanning trees. But min-bottleneck edge may not be minimum of a cut. So every MST is a min-bottleneck tree, but not vice versa.

#Theory Problems

[Posted March 16, 2015.] Consider a connected undirected graph G with not necessarily distinct edge costs. Consider two different minimum-cost spanning trees of G, T and T′. Is there necessarily a sequence of minimum-cost spanning trees T=T0,T1,T2,…,Tr=T′ with the property that each consecutive pair Ti,Ti+1 of MSTs differ by only a single edge swap? Prove the statement or exhibit a counterexample.

Consider a cut A,B where T and T' have different edges (there must be one, else they are same). Let edge of T = (u,v), and edge of T' = (u',v'). Now these must form a cycle by the Double-Crossing theorm. So if we create a tree T'' by removing edge (u',v') from T', and replace with (u,v), T'' will be an MST differing from T in one less edge than T'. By doing this successively, we can get to T.

[Posted March 16, 2015.] Consider the following algorithm. The input is a connected undirected graph with edge costs (distinct, if you prefer). The algorithm proceeds in iterations. If the current graph is a spanning tree, then the algorithm halts. Otherwise, it picks an arbitrary cycle of the current graph and deletes the most expensive edge on the cycle. Is this algorithm guaranteed to compute a minimum-cost spanning tree? Prove it or exhibit a counterexample.


[Posted March 16, 2015.] Consider the following algorithm. The input is a connected undirected graph with edge costs (distinct, if you prefer). The algorithm proceeds in phases. Each phase adds some edges to a tree-so-far and reduces the number of vertices in the graph (when there is only 1 vertex left, the MST is just the empty set). In a phase, we identify the cheapest edge ev incident on each vertex v of the current graph. Let F={ev} be the collection of all such edges in the current phase. Obtain a new (smaller) graph by contracting all of the edges in F --- so that each connected component of F becomes a single vertex in the new graph --- discarding any self-loops that result.
Let T denote the union of all edges that ever get contracted in a phase of this algorithm. Is T guaranteed to be a minimum-cost spanning tree? Prove it or exhibit a counterexample.

[Posted March 16, 2015.] Recall the definition of a minimum bottleneck spanning tree from Problem Set #1. Give a linear-time (i.e., O(m)) algorithm for computing a minimum bottleneck spanning tree of a connected undirected graph. [Hint: make use of a non-trivial linear-time algorithm discussed in Part 1.]

best-path-from-src() = ()
min-bottleneck() = () 
visited = ()

dfs-loop(n)
   visited(n) = true

   for each edge (n,w)
         if !visited(w) && 
            min-bottleneck(w) < max(min-bottleneck(n), len(n,w)) {
             min-bottleneck(w) = 
             dfs-loop(w) 
        }

         if  min-bottleneck-to-dest(w) < min-bottleneck-to-dest(n)  
              min-bottleneck-to-dest(n) = max(length(n,w), min-bottleneck-to-dest(w))
              best-path-to-dest(n) = n::best-path-to-dest(w)            
         end
   end 
end 

// s = startnode - can be any node
find-min-bottleneck(s) 
    for all nodes, set min-bottleneck(node) = very large number
    min-bottleneck(s) = 0

    dfs-loop(s)

end

##Problem Set 4
###Question 1
Consider a directed graph with real-valued edge lengths and no negative-cost cycles. Let s be a source vertex. Assume that there is a unique shortest path from s to every other vertex. What can you say about the subgraph of G that you get by taking the union of these shortest paths? [Pick the strongest statement that is guaranteed to be true.]

Ans. It is a tree, with all edges directed away from s.

###Question 2
  Consider the following optimization to the Bellman-Ford algorithm. Given a graph G=(V,E) with real-valued edge lengths, we label the vertices V={1,2,3,…,n}. The source vertex s should be labeled "1", but the rest of the labeling can be arbitrary. Call an edge (u,v)∈E forward if u<v and backward if u>v. In every odd iteration of the outer loop (i.e., when i=1,3,5,...), we visit the vertices in the order from 1 to n. In every even iteration of the outer loop (when i=2,4,6,...), we visit the vertices in the order from n to 1. In every odd iteration, we update the value of A[i,v] using only the forward edges of the form (w,v), using the most recent subproblem value for w (that from the current iteration rather than the previous one). That is, we compute A[i,v]=min{A[i−1,v],min(w,v)A[i,w]+cwv}, where the inner minimum ranges only over forward edges sticking into v (i.e., with w<v). Note that all relevant subproblems from the current round (A[i,w] for all w<v with (w,v)∈E) are available for constant-time lookup. In even iterations, we compute this same recurrence using only the backward edges (again, all relevant subproblems from the current round are available for constant-time lookup). Which of the following is true about this modified Bellman-Ford algorithm?
  
    for i = 1 .. n-1
      if i even
        for v = n ... 1
          A[i,v] = min { A[i-1, v], min (A[i,w] + cwv) }, w > v 
      if i odd
        for v = 1 ... n
          A[i,v] = min { A[i-1, v], min (A[i,w] + cwv) }, w < v




It correctly computes shortest paths if and only if the input graph has no negative edges.

This algorithm has an asymptotically superior running time to the original Bellman-Ford algorithm.

It correctly computes shortest paths if and only if the input graph has no negative-cost cycle. ... ???

It correctly computes shortest paths if and only if the input graph is a directed acyclic graph.



Question 3
Consider a directed graph in which every edge has length 1. Suppose we run the Floyd-Warshall algorithm with the following modification: instead of using the recurrence A[i,j,k] = min{A[i,j,k-1], A[i,k,k-1] + A[k,j,k-1]}, we use the recurrence A[i,j,k] = A[i,j,k-1] + A[i,k,k-1] * A[k,j,k-1]. For the base case, set A[i,j,0] = 1 if (i,j) is an edge and 0 otherwise. What does this modified algorithm compute -- specificially, what is A[i,j,n] at the conclusion of the algorithm?

Ans: The number of simple (i.e., cycle-free) paths from i to j.

The length of a longest path from i to j.
The number of shortest paths from i to j.
None of the other answers are correct.



Question 4
Suppose we run the Floyd-Warshall algorithm on a directed graph G=(V,E) in which every edge's length is either -1, 0, or 1. Suppose further that G is strongly connected, with at least one u-v path for every pair u,v of vertices. The graph G may or may not have a negative-cost cycle. How large can the final entries A[i,j,n] be, in absolute value? Choose the smallest number that is guaranteed to be a valid upper bound. (As usual, n denotes |V|.) [WARNING: for this question, make sure you refer to the implementation of the Floyd-Wardshall algorithm given in lecture, rather than to some alternative source.]

n−1


n2
2n
+∞


Question 5
Which of the following events cannot possibly occur during the reweighting step of Johnson's algorithm for the all-pairs shortest-paths problem? (Assume that the input graph has no negative-cost cycles.)

e = (u,v)
c' = c + pu - pv
d' = d + pv - pu



Ans : In a directed graph with at least one cycle, reweighting causes the length of every path to strictly increase.

