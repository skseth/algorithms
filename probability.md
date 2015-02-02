# Examples
dice:2 rolling dice
pivot: choosing a pivot 

#Sample Spaces

S(dice) = {(1,1), (1,2) ... (6,6)} - 36 elements
S(pivot) = {1,2,3 ..n} - n elements

#Events
event = subset of S
P(event) = Sum()

event(sumis7) = {(1,6),(2,5),(3,4).. reverse} 
P(sumis7) = 1/6

#Random Variables
X = real-valued function over a sample space.
X:S -> R
SumOfDice:S -> R
SizeOfSubarryPassedToFirstRecursiveCall():S(Pivot) -> k

#Expectations
Average, weighted by probability

SumOfDice:
#(2) = 1 = 2
#(3) = 2 = 6
#(4) = 3 = 12
#(5) = 4 = 20
#(6) = 5 = 30
#(7) = 6 = 42
#(8) = 5 = 40
#(9) = 4 = 36
#(10) = 3 = 30
#(11) = 2 = 22
#(12) = 1 = 12

E(SumOfDice) = 252 / 36 = 7

SizeOfSubArray:
0..n-1
equally likely
E(SizeOfSubArray) = (n-1)/2

#Linearity of Expectation
For Xi on a sample space S:

E(Sum_i(Xi)) = Sum(E Xi)

even works when Xi are not independent

E(Sum(1 dice)) = 3.5 (1+2+..6/6)
E(Sum(2 dice)) = Sum(E(Sum 1 Dice)) = 2*E(Sum(1 dice))

#Example

Assign n process to n servers.

S = n^n outcomes, equally likely
P(i) = 1/n^n 
Y = total no of processes assigned to first server
For process j, define Xj = 1 if j assigned, 0 if not to first server.
E(Y)    = Sum_j X(j) 
        = Sum_j 1/n = 1








