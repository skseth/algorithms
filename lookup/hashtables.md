#Purpose
Fast lookup, like arrays, but for arbitrary objects

#Guarantees
Insert
Delete
Lookup

All O(1)

#Setup
Universe U - very large usually
Goal - want to maintain evolving set S incl. in U.

#Options
Array-based - rarely possible for large U - theta(|U|) size. Space-inefficient. O(1) ops.
List-based - Space efficient - O(|S|) size. But O(|S|) lookup.

#Implementation
* Pick n = # of buckets, where n ~= |S|. Assume - size is fairly stable. Can be handled with some variations.
* A HashFunction h:U -> {0,..,n-1}. Use array of length n, store x in A[h(x)]
* Collision : Linked-List, Open Addressing - linear probing, double hashing
* open addressing - deletion is tricker. Space - open addressing is more efficient.

#Hash Function
* should spread data uniformly - e.g random function
* easy to store / fast to evaluate
* Bad Hash Functions - poor hashtable performance
* Quick and Dirty Hash Function
    - objects u -> hash --> integers -> compr. function --> buckets
    - compression functions
        + modulus of |buckets| - may be bad in some cases
        + choose n to be prime, within a small constant factor of |buckets|
        + not too close to power of 10
        + not too close to power of 2

#Performance
Load of a Hash Table
alpha - |objects| / |buckets|
alpha > 1 => linked list, not open addressing

But, for performance alpha << 1. 
Problem : No ideal hash function exists

Pathological data in real world
[Crosby and Wallach, USENIX 2003](https://www.usenix.org/legacy/event/sec03/tech/full_papers/crosby/crosby.pdf)

Solutions : 
- cryptographic hash functions - infeasible to construct pathological data set for eg for SHA-2
- use randomization - have a family of hash functions

#Universal Hash Functions
H: set of functions from U -> {0..n-1}
H is universal only if and only if : for all x,y in U
    Pr_h_in_H[x,y collide h(x)=h(y)] <= 1/n

e.g H = set of ALL functions - is universal
    H = set of n constant functions - not universal.

#Example:Hashing IP Addresses
IPAddress = {x1,x2,x3,x4}, 0 <= xi <= 255
n = prime, larger than a_i
a = (a1,a2,a3,a4)
h_a = a (.) x mod n, where (.) = dot product
Question : Collision probability
Fix x,y. assume x and y differ at last byte.
Fix a1,a2,a3
We have to prove, we have to prove that Pr[x,y collide] <= 1/n

#Performance: Analysis of Hashing with Chaining
[Carter-Wegman 1979]
O(1) guarantee in expectation

Pre-conditions: 
|S| = O(n) i.e. alpha = |S| / n = O(1)
Hash Function takes O(1) time

Worst-Case : Unsuccessful lookups

Let S = dataset, |S| = O(n)
Let x in U, x not in S.

find bucket - O(1)
look at linked list - O(length of linked list)

#Sub-Problem : Expected Length of Linked List (L) on Average
L:Random Variable
h in H - a universal family of hash functions
For y in S, define Z_y = 1 if h(y) = h(x), 0 otherwise

L = Sum_y_in_S Z_y

E(L) = Sum_y_in_S E(Z_y)    // linearity of expectations
     = Sum_y_in_S Pr[h(y) == h(x)] // Indicator Variables
     <= Sum_y_in_S 1/n // Universal hash function
     = |S|/n // assumption - load is quite constant
     = c

Hence, constant time performance.

#Performance: Open Addressing with Double Hashing
Assumption: one object in each slot, hash function produces a probe sequence for each possible key x, all n! 
Heuristic Assumption : probe sequences equally likely (very unlikely)

Insertion time = 1 / 1 - alpha

E(N) = 1 + alpha*E(N)
E(N) = 1 / 1 - alpha

- Double Hashing : 1 / 1 - alpha can happen

#Performance: Open Addressing - Linear Probing
Linear Probing : Should not expect this. So keep low alpha. Heuristic assumption false.
Assumption : initial probe uniformly random, independent for different keys
Theorem : [Knuth 1962]
Expected Insertion Time >= 1 / (1 - alpha)^2, but is function of alpha.


Question 1
Download the text file here. (Right click and save link as).

The goal of this problem is to implement a variant of the 2-SUM algorithm (covered in the Week 6 lecture on hash table applications).
The file contains 1 million integers, both positive and negative (there might be some repetitions!).This is your array of integers, with the ith row of the file specifying the ith entry of the array.

Your task is to compute the number of target values t in the interval [-10000,10000] (inclusive) such that there are distinct numbers x,y in the input file that satisfy x+y=t. (NOTE: ensuring distinctness requires a one-line addition to the algorithm from lecture.)

Write your numeric answer (an integer between 0 and 20001) in the space provided.


OPTIONAL CHALLENGE: If this problem is too easy for you, try implementing your own hash table for it. For example, you could compare performance under the chaining and open addressing approaches to resolving collisions.

Question 2
Download the text file here.

The goal of this problem is to implement the "Median Maintenance" algorithm (covered in the Week 5 lecture on heap applications). The text file contains a list of the integers from 1 to 10000 in unsorted order; you should treat this as a stream of numbers, arriving one by one. Letting xi denote the ith number of the file, the kth median mk is defined as the median of the numbers x1,…,xk. (So, if k is odd, then mk is ((k+1)/2)th smallest number among x1,…,xk; if k is even, then mk is the (k/2)th smallest number among x1,…,xk.)

In the box below you should type the sum of these 10000 medians, modulo 10000 (i.e., only the last 4 digits). That is, you should compute (m1+m2+m3+⋯+m10000)mod10000.

OPTIONAL EXERCISE: Compare the performance achieved by heap-based and search-tree-based implementations of the algorithm.











