#Problem
Input:Array with n distinct nos and a number i between 1 and n.

#Output
ith order statistic - ie ith smallest element

if i = n/2, then statistic is medians

#RSelect

##Runtime Estimation
Worst case : O(n^2)

Phase j:array size between (3/4)^(j+1) n and (3/4)^(j) n e.g. phase 0 we are using 0.75n - n size array.

Algo uses <= c.n ops outside of recursive call.

X_j = number of recursive calls during phase j

W_j = c.(3/4)^j.n is work done per call

Running time of RSelect <= E(Sum_j X_j . W_j)

###X_j - value of Coin Flipping

given a coin, how many times you have to flip, to get a head

N = no of coin flips - geometric random variable
E(N) = 1 + 0.5 E(N)
E(N) = 2
E(X_j) = 2

###Expected Run time
expected running time <= E[c_n Sum 3/4^j X_j]
                       = c.n Sum 3/4^j E(E_j)
                       = 2.c.n Sum 3/4^j
                       <= 2.c.n (1/1-3/4)
                       = 8.c.n
                       = O(n)

##Deterministic
#Blum-Floyd-Pratt-Rivest-Tarjan

DSelect(a,p,stat)

C = split_and_sort(a,5) // split a into n parts

T(n) <= c.n + T(n/5) + T(n.7/10)

i.e. pivot guarantees a reduction in array size of at least 30%.

By induction, we can prove.

Statement: P(n) => T(n) <= c.10.n for all n, c>= 1
P(1) = true, by default.
P(n): 
T(n) = c.n + T(n/5) + T(n.7/10) 
     <= c.n + 10.c.n/5 + 10.c.n.7/10
     = c.n(1 + 2 + 7) = 10.c.n
QED
      





