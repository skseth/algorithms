###Polynomial Time Solvable Problem

O(n^k), k = constant, time for input of length n.

Class P

    All problems that admit polynomial time solution.



###P != NP

TODO: Paths, flowers, trees : No Polynomial time solution for Travelling Salesman Problem

###NP-Completeness

i) An algorithm is a member of NP-complete, if solutions have length polynomial.

ii) solution correctness can be verified in polynomial time

Cook-Levin
Richard Karp Reducibility Among Combinatorial Problems ... 21 NP-Complete Problems

###NP-Complete Problems

cycle-free shortest paths in graphs with negative cycles
knapsack
Travelling Salesman Problem

###Reductions and Completeness

Problem A reduces to problem B, if we prove that : given a polynomial time alogorithm for problem B, A can also be solved in polynomial time.

Contrapositive : If A is not in P, then neither is B i.e. B is at least as hard as A.

Completeness : given a set of problems C. Problem B is said to be C-complete if all problems in C reduce to B i.e. B is the hardest problem in C.

###NP-Completeness

A problem is NP (non-deterministic polynomial) if :

i) solution is polynomial
ii) verifying a solution is polynomial

Examples : 
Is there a TSP with length <= 1000
constraint-satisfaction problem e.g. 3-SAT

model-checking - harder than NP, but decidable.

NP-Complete problems : If one NP-Complete problem has a polynomial solution, then there is a polynomial solution for all NP problems => P = NP!

Cook, Levin, Karp

###Recipe for Proving a problem is NP-Complete

Find a suitable NP-Complete problem Pi. Prove that Pi reduces to your problem.

Garey and Johnson

###Attacking NP-Complete Problems
i) Computationally tractable special cases
ex. WIS in path graphs, trees, and bounded tree-width graphs
knapsack with polynomial size capacity
2-sat (P) instead of 3-sat (NP-C)
vertex cover when OPT is small

ii) heuristics - fast algorithms that are not always correct

examples : dynamic and greedy problems for knapsack

iii) Exponential but smarter than brute-force search

- knapsack O(nW)
- TSP (2^n instead of n!)
- vertex cover problem 2^OPT n instead of n^OPT

###Vertex Cover Problem - Smarter Search

polynomial time for path graphs, trees, and bounded tree-width graphs, bipartite graphs - no odd cycle, or partitionable into a cut which slices every edges

Special case : Optimal case is small.

Find min. cardinality vertex cover. Given k we want to find a solution with just k nodes.

naive brute force : O(n^k)

Substructure : Given a edge (u,v), then G(u) or G(v) or both contains a vertex cover of size k-1

Algo : pick edge (u,v). Find if G-{u} has vertex cover k-1, or G-{v}. else fail.

Runtime : O(2^k m)

###TSP - Dynamic Programming

Visit every vertex once, lowest cost

Algo :

A[S,1] = 0 if S = {1}, inf otherwise

for m = 2,3,..n
    for each set S in {1,2,..n} of size m that contains 1
        for each j in S, j <> 1
            A[S,j] = min {A[S-{j} , k] + C(k,j)}, k in S, k <> j

Solution : min A[S,j] + C(j,1), j = 2 to n

O(n2^n) subproblems, O(n) work.

O(n^2 2^n)

###Greedy Knapsack Heuristic

Order by bang for buck

Start packing

Return either this, or max valuable item

Guarantee : Value of solution is at least 50% of optimal solution: one-half approximation solution.

Proof : Greedy + fractional >= optimal

3-step greedy >= first k items
3-step greedy >= k+1 items
2 x 3-step greep >= first k+1 items >= OPT

Assumption : wi <= 10% of knapsack capacity

2-step greedy value >= 90% of greedy fractional

###Dynamic Heuristic for Knapsack
Arbitrarily Good Approximation

Round item values. 

Algo : 
    Round vi to nearest multiple of m.
    Smaller e => smaller m

    v'i = floor(vi/ m)

Dynamic Problem by value

Define A(i,x) = min total size needed to achieve value >= x while using only first i items

A[0,x] = 0 if x = 0, else inf 

for i = 0,..n
    x = 0,1,..2..n.vmax
        A(i,x) = min { A(i-1, x), wi + A(i-1,x-vi)}

Return largest x, such that A[n,x] <= W

O(n^2 vmax) for loop. O(nvmax) for scan.

vi - m <= m. vi' <= vi

m = e. vmax / n

Running time : O(n^2 v'max) = O(n^3 / e)

##Local Search
###Maximum Cut Problem
NP-Complete
Local search approach :
Take a cut
Move a vertex over if it improves the cut (no of incident edges not crossing cut > no of edges crossing cut)

Polynomial time.

Guarantee - at least 50% of best : one half approximation or better
Works with weighted

Running time analysis - may break down for weighted

###Principles
Neighbors
TSP - neighborhood - min differing edges (min = 2)

i) Let x = some initial solution
ii) while there is a superior neighbor y, set x = y

how to pick initial solution x
    - use random initial, run many independent trials
    - use best heuristics   
which neighbor to choose
    - random
    - biggest improvement
    - more complex heuristics
whats a neighborhood
    - find sweet spot between time vs accuracy
is local search guaranteed to terminate
    - usually yes
is local search guaranteed to converge quickly
    - not in general
are locally optimal solutions generally good approx. of globally optimal sol.
    - usually not

###2-SAT problem - Papadimitriou - local search 

3-SAT - 2^n brute force, Best is now (4/3)^n

repeat log n time :
    repeat 2 n^2 time :
        if not satisfied, flip a variable to make some clause true

Random Walk on a line : E(Tn) = n^2

p >= 1 - 1/n














