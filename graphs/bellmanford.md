###Problem Definition

Shorted path, with negative edge lengths

Q. How do we handle negative cycles?
negative cycles can lead to infinite recursion.

Q. Compute shortest cycle-free path in presence of negative cycle
NP-hard. 

Bellman-Ford detects negative cycles naturally

###Optimal Substructure Lemma

Graphs are not sequential in general (unlike path graphs for example). The output of Bellman-Ford is a path graph, but the input is not.

Bellman-Ford approach : budget no of path lengths

For each budget : 

###Algorithm

Base case : A[0,s] = 0, A[0,v] = inf. for all v <> s

for i = 1 .. n-1
  for v = 1 ... n
    A[i,v] = min { A[i-1, v], min (A[i-1,w] + cwv) }
