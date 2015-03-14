#Mergesort
See ../complexity.md for details

##Inversions
Basically mergesort, but additionally during merge, whenever right side is copied, we increment by one.

##Motivation
Similarity measure between 2 ranked lists

// Hoare circa 1961
#Quicksort
##Complexity
Worst case (for sorted array and naive choice of pivot = O(n^2))

Best case = O(n log n) if we choose median every times

##Partition Algorithm
Invariant : elements < pivot come before elements after pivot

###Pseudocode

    Partition(A,l,r)
        pivot := A[l]
        i := l+1
        for j = l+1 to r
            if A[j] < pivot 
                swap(A,i,j)
                i = i + 1
        swap(A,l,i-1) // swap pivot to rightful place

Some extra unnecessary swaps may arise in the beginning. This can be optimized.

###Running Time

linear scan, with fixed cost per scan (one swap, one comparison)
in-place

T(n) = O(n)

###Correctness of pivot algorithm - TO BE DONE
Claim : for loop maintains invariant:

j = l+k
LESS(k) = A[l+1]..A[i-1] < pivot
MORE(k) = A[i]..A[j-1] > pivot

For k = 1, at beginning of loop
    i = l+1

    case 1 A(l+1) < pivot => 
        swap(A,i,l+1); i = l+2, j = l+1
        => LESS(1) = {A[l+1]..A[i-1]} =  {A[l+1]} < pivot
           MORE(1) = {A[l+2]..A[l]} = EMPTY
    case 2 A(l+1) > pivot =>
        i = l+1, j = l+1
        LESS(1) = EMPTY
        MORE(1) = {A[l+1]} > pivot
    So P(1) = true, at end of loop


Now for k, at beginning of loop, 
    LESS(k-1) = A[l+1]..A[i-1] < pivot, by hypothesis
    MORE(k-1) = A[i]..A[l+k-2] > pivot, by hypothesis

    j = l+k
    case 1 A(l+k) < pivot => 
        swap(A,i,l+k); i = i + 1
        => A[i-1] < pivot, A[l+1]..A[i-2] < pivot, by hypothesis
        => LESS(k) < pivot




    case 2 => j=l+1, LESS' = LESS, MORE'= MORE U {A[l+k]}


Now, for k = 


##Quicksort Pseudocode
QuickSort(array A, l,r)
  if r <= l return
  p = ChoosePivot(A,n)
  Partition(A,p,n)
  QuickSort(A,l,p)
  QuickSort(A,p+1,r)

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

##Choosing a good pivot

Worst-case on sorted array

    T(n) >= n + n-1 + n-2 ... 1 = n(n+1)/2 = theta(n^2)

Running time depends crucially on pivot choice

Best-case :

We always choose median, dividing array in half.

    T(n) = 2T(n/2) + O(n) => O(n log n)

Q. How do we choose a pivot which brings us close to best case, on average

The intuition is that even for a 25-75 split gets us to O(n log n). Recursion tree exercise.

##Running Time: Big idea - random pivots
RT dominated by comparisons.
Can be proved that RT(sig) <= c.no_of_comparisons

    sig = all possible pivot sequences
    C(sig) = no of comparisons between 2 input numbers
    t.p.t E(C) = O(n log n)

###Decompose to Simpler Random Variables
Master method does not apply (as discussed). Subproblems of diff sizes.

    z_i = ith smallest element of A (element which should be in i)

e.g. (6,8,10,2), z_1 = 2, z_2 = 6, z_3 = 8, z_4 = 10

How often may two elements be compared in QuickSort?
    reason:pivot compared to all elements once. Once compared, never again. Whenever 2 elements are compared, one is a pivot.

    Let X_ij = #of times z_i and z_j are compared. 
    X_ij(sig) = 0 or 1 = #comparisons between z_i and z_j

    C(sig) = Sum_i(Sum_j(X_ij(sig)))
    E(C(sig)) = Sum_i(Sum_j) E(X_ij(sig))
              = Sum_i(Sum_j) E(Pr[X_ij = 1])


###Claim:Pr[X_ij] = 2/(j - i + 1)

fix z_i and z_j.

Consider Z = (z_i, z_i+1, z_i+2,...z_j) 

Remember these will not be the order in the input array, in general. 

As long as pivot is outside, these elements are never compared.

    Consider when a pivot element is chosen :
    case a : z_i or z_j are picked. Then compared once.
    case b : any one in the middle is picked. Then z_i z_j are never compared.

Hence, probability Pr[X_ij] = 2 / (j - i + 1)

###E(C(sig))
E(C(sig)) = Sum_i Sum_j 2 / (j - i + 1)
                where i = 1..n-1, j = i+1..n

          = 2*Sum_i Sum_j 1 / (j - i + 1)
          <= 2 . n Sum 1/k
                    where k = 2 to n
          <= 2. n . log n

Sum 1/k <= log n, because

integral (1/n) = log n
    from 1 to n = log n - log 1 = log n

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

















