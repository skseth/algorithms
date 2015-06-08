##Huffman Codes
###Codes

prefix-free codes of variable length

think of binary codes as trees. prefix-free codes have symbols at leaves.

    L(T) = Sum_i p(i).[depth of i in tree]

Goal - minimize L(T)

Huffman's optimal idea :

###Algorithm [Huffman 52]

Choose the least frequent 2 letters in the alphabet. Join them in a tree, and create new alphabet with the 2 letters joined, with frequency as sum of 2 letters. Iterate till you have one letter.

###Proof of Correctness

For base case, |alphabet| = 2, the algorithm returns one bit.

Consider 2 trees T and T', T' has node ab with P(a) + P(b), and T has nodes a and b instead of ab, with P(a) and P(b respectively).

We find that L(T) - L(T') = P(a) + P(b)

Of all solutions where a and b are siblings, then this algorithm gives the optimal solution, since it is the smallest increase in average encoding length from T', and we have assumed T' is optimal by inductive hypothesis.

Key Lemma : There is an optimal tree for an alphabet, containing a and b as siblings.

Let x, y as siblings in the deepest level. Net benefit is :

(P(x) - P(a))(orig_depth(x) - origdepth(a)) + (P(y) - P(b))(orig_depth(y) - origdepth(b)) >= 0.

Can be implemented by one sort, and 2 queues. Naive implementation takes O(n^2) time.




