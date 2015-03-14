#Purpose 
Space efficient lookups. HashSet - type

#Pros-Cons
More space efficient

mostly not for deletions
no associated data
small false positive probability

#Operations
Insert
Lookup

#Applications
Early spellcheckers

#Implementation
Array of n bits

k hash functions h1, h2, .., hk

Insert(x) : for i = 1,2..k set A[h_i(x)] = 1

Lookup(x) : (for i = 1,2,..k if A[h_i(x)] == false then false) else true

#Heuristic Analysis (What is the Error Rate)
Assume : all hi(x) are random, and independent across i's and x's
For each bit of A

data set S inserted into A. What is the probability a certain bit, say 1, is set?

k|S| hits at n boxes. 

x balls, n slots - probability slot 1 is empty = no of ways i can fill x balls in n slots - no of ways x balls in n-1 slots

Pr of bit being one = n^x - (n-1)^x / n^x = 1 - (1 - 1/n)^k|S|

assume x = 1/n, and know e^x > 1 +x for all x. Assume x = -1/n
Pr ~= 1 - e^(-k|S|/n)   , since 1/n is close to zero
b = |S|/N = no of bits per object inserted

Pr of bit being one ~= 1 - e^(-k/b)

More the b's, lower Pr of one, hence lower chance of collision

False positive error rate = Pr(bit=1)^k = (1 - e^(-k/b))^k

Larger the b, larger the k, closer the error rate = 0

For fixed b, choose k that minimizes error rate
k ~= (ln 2).b = 0.693 b

e ~= (1/2)^(ln 2)b

or b ~= 1.44 log_2 1/e

e.g. b = 8, k = 5 or 6, e = 2%

#References

http://www.codecommit.com/blog/scala/bloom-filters-in-scala



