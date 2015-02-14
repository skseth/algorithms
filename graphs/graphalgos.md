#Graph

Pairwise dependencis between objects.

Vertices / Nodes (V) - set of vertices.
Edges / Arcs - directed / undirected, head/tail

#Examples
Road Networks, the web, social networks (which are directed, undirected?), precedence constraints

#Minimum Cut of a Graph
MinCut
Allow parallel edges

#Applications
Network bottlenecks
community detection in social network
image segmentation - input graph of pixels.

Connected graphs : Max no of edges nC2. Min : n-1
n - # of vertices, m - no of edges

sparse - 

#Data Structure
Adjacency matrix - A_ij : 1 => edge exists
Needs n^2 space. Good for dense graph.

Adjacency list - edgx`

Array (or list of vertices)
Array (or list of edges)
Each edge points to its endpoints
Each vertex points to edges incident on it.

Space usage = Theta(m + n)

Web graph : 10^10 nodes, 10^11 edges

Sum (Degree v) = 2 m, degree(v) >= k (min cut), for any v in a graph.

Hence, Sum(degree v ) >= kn, hence m >= kn / 2

Pr[S1] = k/m <= 2 / n

Pr[S1 n S2] = Pr[S1 | S2] * Pr[S1]

Pr[S1 n S2 n ... Sn] = Pr(S1) Pr(S2 | S1) Pr(S3 | S1 n S2) ..
                    >= (1-2/n)(1-2/n-1)...
                    = 2/n(n-1) >= 2/n2






