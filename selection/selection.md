#Problem
Input:Array with n distinct nos and a number i between 1 and n.

#Output
ith order statistic - ie ith smallest element

if i = n/2, then statistic is medians

#RSelect
##Pseudocode

RSelect(A,i,l,r)

    chooseRandomPivot(A,l,r)
    p = Partition(A,l,r)
    if p < i
        RSelect(A,i-p,p+1,r)
    else if p > i
        RSelect(A,i,l,p-1)
    else
        return A[i]

##Runtime Estimation
Runtime depends on quality of given pivots. The worst case is O(n^2).

The best case - we choose median every time. Then,

T(n) = T(n/2) + O(n) : a = 1, b = 2, d = 1, a < b^d.
Hence, T(n) = O(n) ... root dominates.

We have to find the expected running time, and hope it is close to best case.

Unlike quicksort, we recurse on only one side. Algo uses <= c.n ops outside of recursive call - the partition step.

Concept of Phase - array size between (3/4)^(j+1) n and (3/4)^(j) n e.g. phase 0 we are using 0.75n - n size array. 

X_j = number of recursive calls during phase j

W_j = c.(3/4)^j.n is work done per call

Running time of RSelect <= E(Sum_j X_j . W_j)

###X_j - value of Coin Flipping

Given a coin, how many times you have to flip, to get a head

N = no of coin flips - geometric random variable

E(N) = 1 + 0.5 E(N)
E(N) = 2

X_j is equivalent to a coin flip, because it is the chance of choosing elements between 25% - 75% of the elements.

Hence, E(X_j) = 2

###Expected Run time
expected running time <= E[c_n Sum 3/4^j X_j]
                       = c.n Sum 3/4^j E(E_j)
                       = 2.c.n Sum 3/4^j
                       <= 2.c.n (1/1-3/4)
                       = 8.c.n
                       = O(n)

##Deterministic Linear Time Selection
#Blum-Floyd-Pratt-Rivest-Tarjan

RSelect(A,i,l,r)

    chooseDeterministicPivot(A,l,r)
    p = Partition(A,l,r)
    if p < i
        RSelect(A,i-p,p+1,r)
    else if p > i
        RSelect(A,i,l,p-1)
    else
        return A[i]

ChooseDeterministicPivot

    break A into n/5 elements of length 5
    sort each group of 5 (O(n) - fixed cost*n/5)
    M = median_ofA
    RSelect(M, n/10, 0, n/5)

Pivot guarantees reduction is size of at least 30%

oooooooooooooooooooooooooooooooooooooo
oooooooooooooooooooooooooooooooooooooo
xxxxxxxxxxxxxxxxxxxMxxxxxxxxxxxxxxxxxx
oooooooooooooooooooooooooooooooooooooo
oooooooooooooooooooooooooooooooooooooo

All elements to top left of x are less, elements on bottom right are more.
So pivot results in reduction of array size by at least 30%.


C = split_and_sort(a,5) // split a into n parts

T(n) <= c.n + T(n/5) + T(n.7/10)

i.e. pivot guarantees a reduction in array size of at least 30%.

By induction, we can prove.

Statement: P(n) => T(n) <= c.10.n for all n, c>= 1
P(1) = true, by default.
P(n): 
T(n) <= c.n + T(n/5) + T(n.7/10) 
     <= c.n + 10.c.n/5 + 10.c.n.7/10
     = c.n(1 + 2 + 7) = 10.c.n

T(n) = O(n)
QED

##Note on Deterministic Select
If we choose 3 elements instead of 5, we find that the worst case time does not hold. See theory problems. On the other hand, it does work for 7. Intuitively work done on selecting median for 3 elements starts becoming big enough.




