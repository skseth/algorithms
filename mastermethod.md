#Statement
T(n) <= aT(n/b) + O(n^d)
constants - do not depend on n:
a = recursive calls at each step >= 1
b = input size shrinkage factor > 1
d = combine step exponent d = 0 => contant time

T(n) = 
    O(n^d log n) ... if a = b^d ... same work at each level * no of levels
    O(n^d)       ... if a < b^d ... root dominates
    O(n^log_b a) ... if a > b^d ... leaves dominate

#Examples
MergeSort : a = 2, b = 2, d = 1. a = b^d => n log n
BinarySearch : a = 1, b = 2, d = 0 => a = b^d => (n^d log n) = log n
Mul : a = 4, b = 2, d = 1 => b^d = 2 => (n^log_2 4) = n^2
(why addition is linear time)
Gauss' : a = 3, b = 2, d = 1 => b^d = 2, a > b^d => (n^log_2 3) = (n^1.59)
Strassens Matrix Multiplication = a = 7, b = 2, d = 1 => (n^log_2 7) = n^2.80
why b = 2?
Closest Pair

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

## Case B: a < b^d

S = 1 + r + r^2 + .. r^k = r^k+1 - 1/ r - 1

if a < b^d, S < 1 / (1 - r) = const.

T(n) = O(n^d)

## Case C: a > b^d

S = r^k (1 + (1 - 1/r^k)  / r - 1)) <= r^k (1 + 1/r - 1) <= 2.r^k

r = a/b^d,k = log n => S <= c. (a/b^d)^log_b n

T(n) = c.n^d.c.(a/b^d)^log_b n 

(1/b^d)^(log_b n) = n^(-d)

T(n) = O(a^log_b n) = O(#leaves)

a^log_b n = n^log_b a ! take logs of both sides and compare





