// Hoare circa 1961
#Quicksort
##Complexity
Worst case (for sorted array and naive choice of pivot = O(n^2))

Best case = O(n log n) if we choose median every times

##Correctness
    P(n) : Quicksort correctly sorts arrays of length n
    Base case : P(1) - array already sorted. Quicksort returns array. Hence true
    Inductive Step : for all k < n, P(k) is true => P(n) is true
        Fix n >= 2
        Assume Array a of length n
            p - pivot. a = [k1,p,k2], where p is in correct position.
            length(k1,k2) < n. Since P(k1) and P(k2) are true, by inductive hypothesis
            => k1 is sorted and less than p, k2 is sorted and greater than p, and p is in correct place.
            => P(n) is true.

##Running Time: Big idea - random pivots
RT dominated by comparisons.
Can be proved that RT(sig) <= c.no_of_comparisons

    sig = all possible pivot sequences
    C(sig) = no of comparisons between 2 input numbers
    t.p.t E(C) = O(n log n)

###Decompose to Simpler Variables
Master method does not apply (as discussed). Subproblems of diff sizes.

    z_i = ith smallest element of A (element which should be in i)

e.g. (6,8,10,2), z_1 = 2, z_2 = 6, z_3 = 8, z_4 = 10

    For sig in S, i < j,let X_ij(sig) = # of times i and j are compared in QuickSort

times i and j are compared in QuickSort = 0 or 1, never more. X_ij is an indicator variable.

reason:pivot compared to all elements once. Once compared, never again. Whenever 2 elements are compared, one is a pivot.

    E(C(sig)) = Sum(X_ij(sig)) = Sum_i_1_to_n-1 Sum_j_i+1_to_n X_ij(sig)
    E(C(sig)) = Sum(X_ij(sig)) = Sum_i Sum_j Pr[X_ij(sig) = 1]

###Claim:Pr[X_ij]
Pr[X_ij] = 2/(j - i + 1)

fix z_i and z_j.

Consider Z = (z_i, z_i+1, z_i+2,...z_j) 

As long as pivot is outside, these elements are never compared.

    Consider when a pivot element is chosen :
    case a : z_i or z_j are picked. Then compared once.
    case b : any one in the middle is picked. Then z_i z_j are never compared.

Hence, probability Pr[X_ij] = 2 / (j - i + 1)

###E(C(sig))
E(C(sig)) = Sum_i Sum_j 2 / (j - i + 1)
          = 2*Sum_i Sum_j 2 / (j - i + 1)
          <= 2*Sum_i [1/2 + 1/3 + 1/4 + 1/k]
          <= 2*n*[1/2 + 1/3 ..]
          <= 2*n*log n

QED

#Lower Bound on Comparison Based Sorting

Theorem: Every deterministic comparison based sorting algorithm has worst-case running time omega(n log n). i.e. T(n) >= c.n log n

Comparison-based sorting : MergeSort, QuickSort, HeapSort.

BucketSort, RadixSort, CountingSort make strong assumptions about data. Can achieve O(n).

Proof: Take input of length n. Can be arranged in n! ways.

Assume no of comparisons <= k. Each comparison returns a 0 or 1, so can be represented in at most k bits. Hence no of possible executions <= 2^k for sorting an input of length n, with n! possible combinations.

By pigeonhole principle, 2^k >= n! 
                             >= (n/2)^(n/2) ... (at least half the n terms are more than n/2)
                        k   >= log (n/2)^(n/2) 
                            = n/2 log (n/2) 
                            = n/2 log n - n/2 
                        k   = omega(n log n) .. bounded below by

















