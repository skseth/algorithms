#Integer Multiplication

n = no of digits in the input numbers

T(n) = k.n^2 = O(n^2)

Doubling results in increase in time by 4.

#Karatsuba Multiplication

Sum = 10^n a.c + 10^n/2 (ad + bc) + b.d

(a + b)(c + d) - ac - bd = ad + bc (Gauss' Trick used for complex multiplication)

#Complexity of Karatsuba Multiplication

By Master Method, 

T(n) = 3 T(n/2) + O(n)

a = 3, b = 2, d = 1 => a > b^d

Hence,
T(n) = O(n^log_b a) = O(nln3) ~= O(n^1.585)





