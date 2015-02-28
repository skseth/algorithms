# algorithms

Based mostly on the excellent lectures on [Algorithms: Design and Analysis, Part 1](https://class.coursera.org/algo-007/) from Coursera.

#Purely functional data structures
[Purely functional data structures](http://www.cs.cmu.edu/~rwh/theses/okasaki.pdf)

https://www.youtube.com/watch?v=pNhBQJN44YQ

http://www.slideshare.net/vkostyukov/purely-functional-data-structures-in-scala-26175521

https://reprog.wordpress.com/2010/05/25/how-slow-are-functional-implementations-of-quicksort/

http://www.informit.com/articles/printerfriendly/1407357

http://arxiv.org/pdf/1312.4666.pdf



Theory Problems :

#6. [Posted January 31st] Prove that the worst-case expected running time of every randomized comparison-based sorting algorithm is Ω(nlogn). (Here the worst-case is over inputs, and the expectation is over the random coin flips made by the algorithm.)


#7. [Posted January 31st] Suppose we modify the deterministic linear-time selection algorithm by grouping the elements into groups of 7, rather than groups of 5. (Use the "median-of-medians" as the pivot, as before.) Does the algorithm still run in O(n) time? What if we use groups of 3?

For the case of 3 i have the following line of argument :

First, as a general point, T(n) requires a minimum of n comparisons, because each element has to be compared at least once. 

Thus T(n) >= n, i.e. T(n) = Omega( n )

Assume that when we partition the array into 3 elements each, then T(n) = O(n). Then we are actually making a slightly stronger claim : T(n) = theta(n), since we already know T(n) = omega(n).

Thus there is some a', and some a, such that for all n > some N, 

a'. n <= T(n) <= a.n 

Note : a' >= 1, because we have a minimum of n comparisons, and also, this implies a' < a.

Now we come to our inequality.

T(n) = c.n + T(n/3) + T(2n/3)

Note that c.n is the overhead of partitioning and choosing medians. No matter what algorithm we use, this is a minimum of n comparisons, because again, each element has to be visited once. Thus c >= 1. Thus, we see that :

T(n) = c.n + T(n/3) + T(2n/3)
       >= 1.n + a'(n/3) + a'2n/3,   for all n > 3N (the lower bound a' is assumed to be true for all n > N).
        = (1+a') n

i.e. even when T(n/3) is the minimum (i.e. a' n/3), T(n) is above that minimum by a constant factor of at least 1. 

Thus :

T(3n) >= (1 + a')^2 (3n)
T(9n) >= (1 + a')^3 (9n)
and so on.

Since a, a' and are constants, while (1 + a')^x increases unboundedly as x -> infinity, there will be a point, for some n, when:

T(n) = (1 + a)^x (n) > a.n.

This violates our assumption that a is an upper bound i.e. T(n) < a(n) for all n > N. Hence T(n) is not O(n)

#8.[Posted January 31st] Given an array of n distinct (but unsorted) elements x1,x2,…,xn with positive weights w1,w2,…,wn such that ∑ni=1wi=W, a weighted median is an element xk for which the total weight of all elements with value less than xk (i.e., ∑xi<xkwi) is at most W/2, and also the total weight of elements with value larger than xk (i.e., ∑xi>xkwi) is at most W/2. Observe that there are at most two weighted medians. Show how to compute all weighted medians in O(n) worst-case time.

Ans:
Calculate W, W/2 (O(n))

in loop :
    DSelect - choose median O(n)
    calculate weight on either side : O(n)
    if one side > W/2, recurse on that side.
    Else we have a weighted median.

If one side == W/2 - there may be another median.
       repead algorithm with tha side to find other median - O(n)

#9. [Posted January 31st] We showed in an optional video lecture that every undirected graph has only polynomially (in the number n of vertices) different minimum cuts. Is this also true for directed graphs? Prove it or give a counterexample.

Probability of finding specific min-cut Ai, Bi
m = no of edges
n = no of vertices
k = no of crossing edges, crossing Ai to Bi
Key observation : deg(vertex) >= k for each vertex (undirected)
in-degree(vertex) + out-degree(vertex) >= k for each vertex (directed)
Sum(deg(vertex)) = 2m (for undirected)
Sum(in-degree(vertex) + out-degree(vertex)) = 2m

F = {crossing edges}
Pr[Output is Ai,Bi] = Pr[never eliminate an edge of F]

Si = event that edge of F is contracted in iteration i
P = Pr[!S1 n !S2 n !S3 ... !Sn]
Pr(S1) = k/m
Pr(!S1) = 1-k/m

Sum(deg(vertex)) >= kn for undirected, 
Sum(in+outdegree(vertex)) >= kn for directed
Hence, 2m >= kn, for all graphs
m >= kn/2 

Hence, 
Pr(S1) = k/m <= 2/n  

Pr(!S1 n !S2) = Pr(!S2|!S1).Pr(!S1)

Second general observations:
Any contracted node in the graph is still a sort of cut, and has at least k edges.
Hence, all degrees are <= k.
Pr(!S2|!S1) = k/m' <= 1-2/n-1 (1-1/n-1) 
Pr(!S1 n !S2) = (1-2/n-1).(1-2/n) = (n-3/n-1).(n-2/n)

P = (1-2/n)(1-2/n-1)(1-2/n-2)...(1-2/n-(n-4)).(1-2/n-(n-3))
  = (n-2/n)(n-3/n-1)(n-4/n-2)...(2/4)(1/3)
  = 1.2/n(n-1) = 1/nC2

graph may have more than 1 min cut.
e.g. trees have n-1 cuts, one per edge.

What is the largest no of min cuts : ? No between : n-1 to 2^n
Ans : nC2

n-cycle: remove pair, one cut. nC2.

By Contraction Algo, we proved that :

Pr[output=(Ai,Bi)] >= 1/nC2 
Since (Ai,Bi) are disjoint, probabilities add. Hence total no of mincuts < nC2.

#10. [Posted January 31st] For a parameter α≥1, an α-minimum cut is one for which the number of crossing edges is at most α times that of a minimum cut. How many α-minimum cuts can an undirected graph have, as a function of α and the number n of vertices? Prove the best upper bound that you can.

Following logic above :
Pr(S1) = α.k/m 
m >= kn/2
Pr(S1) <= 2α / n
Pr(!S1) >= 1 - 2α / n

P = (1-2α/n)(1-2α/n-1)(1-2α/n-2)..(1-2α/4)(1-2α/3)

Hence, no of α-min cuts = n.(n-1)(n-2)..4.3 / (n-2α)(n-2α-1)(n-2-2α)..(4-2α)(3-2α)

#11. [Posted February 9th] In the 2SAT problem, you are given a set of clauses, where each clause is the disjunction of two literals (a literal is a Boolean variable or the negation of a Boolean variable). You are looking for a way to assign a value "true" or "false" to each of the variables so that all clauses are satisfied --- that is, there is at least one true literal in each clause. For this problem, design an algorithm that determines whether or not a given 2SAT instance has a satisfying assignment. (Your algorithm does not need to exhibit a satisfying assignment, just decide whether or not one exists.) Your algorithm should run in O(m+n) time, where m and n are the number of clauses and variables, respectively. [Hint: strongly connected components.]

Solution suggested by another student:

Let A -> B in our graph represent logical implication : A => B
For every literal, have two nodes : A and ~A
For every clause : 
a V b : draw two edges : a -> ~b, and b -> ~a
contradiction if x => ~x i.e. there is a cycle which leads from x to ~x 

Generate SCCs. If for any SCC, for any literal x, x and ~x are in same scc, then no solution exists. 

#12. [Posted February 9th] In lecture we define the length of a path to be the sum of the lengths of its edges. Define the bottleneck of a path to be the maximum length of one of its edges. A mininum-bottleneck path between two vertices s and t is a path with bottleneck no larger than that of any other s-t path. Show how to modify Dijkstra's algorithm to compute a minimum-bottleneck path between two given vertices. The running time should be O(mlogn), as in lecture.

key[v] = smallest dijkstra score of edge (u,v) in E, with v in X.
djikstra score of an edge = max(A[v],l(v,w))

Stop when you extract t. 

#13. [Posted February 9th] We can do better. Suppose now that the graph is undirected. Give a linear-time (O(m)) algorithm to compute a minimum-bottleneck path between two given vertices.

bottleneck-t = infinity
best-path-to-t = ()
visited = ()

dfs-loop(n, current_max_bottleneck, current_path)
   visited(n) = true
   for each edge (n,w)
        if (edge-length < bottleneck(t)) 
            new_path = current_path + w
            new_max_bottleneck = max(current_max_bottleneck, edge-weight)
            if w == t then 
                bottleneck(t) = new_max_bottleneck
                best-path-to-t = new_path
                return
            else if !visited(w)
                dfs-loop(w, new_max_bottleneck, new_path)
            end
        end
    end
end

find-min-bottleneck(s,t) 
    dfs-loop(s, infinity, ())
    return best-path-to-t
end

# [Posted February 9th] What if the graph is directed? Can you compute a minimum-bottleneck path between two given vertices faster than O(mlogn)?




