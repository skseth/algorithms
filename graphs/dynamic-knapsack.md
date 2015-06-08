###Maximal-WIS of Path Graphs

A[i] = max[A[i-1], A[i-2] + w_i]

Basically relate problem to smaller subproblem. 

###Knapsack Problem

For i = 1 ... n
    for x = 0 ... W
       A[i,x] = max{A[i-1,x], A[i-1,x-wi] + vi}

if we switched :

for x = 0 ... W
    for i = 1 ... n
       A[i,x] = max{A[i-1,x], A[i-1,x-wi] + vi}
