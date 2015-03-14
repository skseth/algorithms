#Divide-and-Conquer Paradigm

#Mergesort
Better than Insertion, Selection, and Bubble sorts.

Selection - find minimum, repeatedly
Insertion - maintain invariant that prefix is sorted
Bubble Sort - multiple pass - swap when adjacent elements are out of order

Worst-case performance of all the above - O(n^2)

##Description
Recursive Algorithm

    split into half
    sort the left, sort the right (2 recursive steps)
    merge the two halves

##Merge Step Complexity
    Merge Step :
    i = 1
    j = 1
    for k = 1 to n
        if A(i) < B(j)
            C(k) = A(i)
            i++
        else if B(j) < A(i)
            C(k) = B(j)
            j++
        end
    end

No of operations:
Initialization - 2
per iteration - 
    k-increment, j or i increment, assignment and comparison = 4 ops
4n + 2 lines of code (or arguably 5n + 2)

We will accept : 

    T(m) <= 6m (for merge)

##Recursion Tree

level 0, 1, 2, .. log_2 n
n_j = no of subproblems = 2^j
s_j = size of subproblem = n / 2^j

work done for s_j for merge = 6.s_j
Work done at each level = 2^j . work done for one subproblem 
                        <= 2^j . 6. n / 2^j
                        = 6.n (at each level)

##Total Work

Height of tree = log_2 n + 1 ... assuming n is a power of 2.

Work at each level = 6.n

T(n) <= 6.n.log_2 n + 6.n

#O-Notation
##Formal Definitions

    if there exists c, N > 0 s.t. 
        T(n) <= c.f(n) for all n > N
    then T(n) = O(f(n))

c, N are independent of n.

##Suppression of Constant Factors and Lower Order Terms

###Polynomial

    if T(n) = P(n,k), T(n) = O(n^k)
    where P(n,k) = a_k n^k + a_k-1 n^k-1 ... a_0 

choose n0 = 1, c = Sum(|a_k|)
For all values of n, n >= 1

    T(n) <= |a_k| n^k + |a_k-1| n^k .. a_0
         <= Sum(|a_k) n^k
         = c.n^k

Thus we can suppress constant factors and lower order terms. Lower order terms tend to be irrelevant for large inputs, and constant factors may be compiler dependent.

###MergeSort
For merge sort,
T(n) <= 6.n.log n + 6.n

But n < n log n for all n >= 2

Hence, T(n) <= 12.n.log n, for all n >= 2
Choosing c = 12, n0 = 2
T(n) <= c. n log n, for all n >= n0
Hence, T(n) = O(n log n)

##Big-Omega, Theta, Little-o

Big-O:if T(n) <= c.f(n), then T(n) = O(f(n))

Omega:if T(n) >= c.f(n), then T(n) = omega(f(n))

Theta:if T(n) = O(f(n)) and Omega(f(n)), then T(n) = theta(f(n))

Little-O : for every constant c > 0, there exists n >= n0, s.t. 
T(n) <= c.f(n) for all n > n0,
then T(n) = o(n)

e.g.  n^k-1 = o(n^k)

Take a arbitrary constant c > 0. Choose n0 > ceiling(1/c).

Then : c.n^k = (c.n)n^k-1
             >= n^k-1, since c.n >= 1 for all n >= n0

Hence, n^k-1 <= c.n^k, given any c > 0, and n > ceiling(1/c)
Hence, n^k-1 = o(n^k)

##Examples

* 2^(n+10) = O(2^n) 
* 2^10n <> O(2^n)
Assume it is O(2^n)
2^10n < c. 2^n for all n > n0

=> 2^9n < c for all n > n0

=> n < log c / 9. But this is false for all n > log c / 9.

* For every positive functions f(n) and g(n),
pointwise max(f,g) = theta(f(n) + g(n))

max(f(x),g(x)) <= f(x) + g(x)  ... because f, g are positive

2.max(f(x),g(x)) >=  f(x) + g(x) ... because f,g are positive

1/2(f + g) <= max(f,g) <= (f + g)

Here - n0 = 1, c1 = 1/2, c2 = 1
hence max(f,g) = theta(f+g)

