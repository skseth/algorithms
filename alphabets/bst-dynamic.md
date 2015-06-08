###Probabilistic BST

Non-uniform access can change shape of optimal tree.

Average search time :

C(T) = Sum_i p_i * searchtime_i

searchtime = depth + 1

Difference from huffmans : internal nodes can have symbols

###Optimal Search Tree Structure

We have root r. Also left subtree containes everything less than r. right subtree contains everything more than r.

Proof : Assume not true ex. T1 (left) not optimal. Replace with T* for T1.

C(T) = Sum pi.[depth of i] = C(T1) + C(T2) + Sum(pi in T1) + Sum(p2 in T2)

Implies C(new T) < C(T) contradicting optimailty assumption.

###Recurrence

for s = 0 to n-1
for i = 1 to n
  A[i,i+s] = min (r=i to i+s) (Sum_k Pk + A[i,r-1] + A[r+1,i+s])

return A(1,n)

fills in diagonals
running time - theta(n^3)

Can be reduced to theta(n^2) by selecting relevant r's (Knuth 71, Yao 80)