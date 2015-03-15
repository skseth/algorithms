#Multiplication of 2 nxn Matrices

##Naive algorithm
Multiply (x1,x2,..xn).(y1,y2,..yn) = n multiplications, n-1 additions = O(n)
n^2 multiplications in all 

T(n) = O(n^3)

#Simple Divide and Conquer
X = A B
    C D

Y = E F
    G H

X.Y = A.E+B.G  A.F+B.H
      C.E+D.G  C.F+D.H


T(n) = 8 T(n/2) + O(n^2)

As per master method :
a = 8, b = 2, d = 2

a > b^d

T(n) = O(n^log_b a) = O(n^log_2 8) = O(n^3)

#Strassens Algorithm

T(n) = 7 T(n/2) + O(n^2)

a = 7, b = 2, d = 2

a > b^d

T(n) = O(n^log_b a) = O(n^log 7) = O(n^2.807)


