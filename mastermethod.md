#Master Method

##Statement
T(n) <= aT(n/b) + O(n^d)
constants - do not depend on n:
a = recursive calls at each step >= 1
b = input size shrinkage factor > 1
d = combine step exponent d = 0 => contant time

All subproblems have same size

T(n) = 
    O(n^d log n) ... if a = b^d ... same work at each level * no of levels
    O(n^d)       ... if a < b^d ... root dominates
    O(n^log_b a) ... if a > b^d ... leaves dominate

##Key points about Recursive Algorithms with fixed a,b and d

Height of tree = 1 + log_b n

No of leaf nodes = a^log_b n = n^log_b a

Root node time = O(n^d)

#Examples
MergeSort : 
a = 2, b = 2, d = 1. a = b^d => n log n

BinarySearch : 
a = 1, b = 2, d = 0 => a = b^d => (n^d log n) = log n

Integer Multiplication : 
a = 4, b = 2, d = 1 => b^d = 2 => (n^log_2 4) = n^2

Karatsuba Alogorithm : 
a = 3, b = 2, d = 1 => b^d = 2, a > b^d => (n^log_2 3) = (n^1.59)

Strassens Matrix Multiplication:
a = 7, b = 2, d = 1 => (n^log_2 7) = n^2.80

Closest Pair:


#Proof

Assume :
(i)     T(1) <= c
(ii)    T(n) <= aT(n/b) + c n^d 
.. where c is some constant
.. n is power of b

No of subproblems at jth level = a^j
Size of subproblem at jth level = n / b^j
Time for all subproblems at jth level <= a^j . T(n / b^j) 
                                = a^j . c.[n / b^j]^d = c.n^d [a / b ^ d]^j

    T(n) = c.n^d.Sum(j = 0 ... log_b n) [a / b ^ d]^j

Tug of war between 
a   ... rate of subproblem proliferation
b^d ... rate of work shrinkage

## Case A: a = b^d
T(n) = c.n^d . ((log_b n) + 1) = O(n^d log n)

##Cases B & C - Facts about Sum
S = 1 + r + r^2 + .. r^k = r^k+1 - 1/ r - 1

If r < 1, then 

    S = 1 - r^(k+1) / 1 - r <= 1/1-r

If r > 1, then

    S = r^k(1 + (1-1/r^k)/(r-1)) <= r^k ( 1 + 1/r-1) <= 2.r^k

## Case B: a < b^d

S <= 1 / (1 - r) = k (constant)

T(n) = c.n^d.k = O(n^d)

## Case C: a > b^d

S = <= r^k (1 + 1/r - 1) <= 2.r^k

r = a/b^d,k = log n => S <= c. (a/b^d)^log_b n

T(n) = c.n^d.c.(a/b^d)^log_b n 

(1/b^d)^(log_b n) = (b^-d)^(log_b n) = (b^(log_b n))^(-d) = n^(-d)

    T(n) = O(a^log_b n) = O(#leaves)

a^log_b n = n^log_b a ! take logs of both sides and compare

because:
a^log_b n = (2^(log a))^(log n / log b) = (2^log n)^(log a / log b)
          = n^log_b a

    T(n) = n^log_a b

