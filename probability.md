# Examples
dice:2 rolling dice
pivot: choosing a pivot 

#Sample Spaces
dice = {1,2,3,4,5,6} - 6 elements
2dice = dice x dice = {(1,1), (1,2) ... (6,6)} - 36 elements
pivot = {1,2,3 ..n} - n elements

#Events
event = subset of S
P(event) = Sum()

event(sumis7) = {(1,6),(2,5),(3,4).. reverse} 
P(sumis7) = 1/6

#Random Variables
X = real-valued function over a sample space.
X:S -> R
SumOfDice:S(2Dice) -> R
SizeOfSubarryPassedToFirstRecursiveCall():S(Pivot) -> k

#Expectations
Average, weighted by probability

SumOf2Dice:
Sum - probability - Contribution to average sum

#(2) * 1/36 = 2/36
#(3) * 2/36 = 6/36
#(4) * 3/36 = 12/36
#(5) * 4/36 = 20/36
#(6) * 5/36 = 30/36
#(7) * 6/36 = 42/36
#(8) * 5/36 = 40/36
#(9) * 4/36 = 36/36
#(10) * 3/36 = 30/36
#(11) * 2/36 = 22/36
#(12) * 1/36 = 12/36

E(SumOf2Dice) = Sum(Pi * SumOfDice(i)) 252 / 36 = 7

E(SumOf1Dice) = 1/6 + 2/6 + ..6/6 = 1/6(6.7/2) = 3.5

SizeOfSubArray: values - 0..n-1

E(SizeOfSubArray) = Sum(Pi * SizeOfSubArray(i))
                  = 1/n.0 + 1/n*1 + (1/n)(n-1)
                  = 1/n(1+2..n-1) = (1/n)n(n-1)/2
                  = (n-1)/2

#Indicator Variables

Random variables with 2 values 0, and 1.

I(i) = 0, if i even, 
     = 1, if i odd

E I = Sum p_i * I(i) = pr[I=1]*1 + pr[I=0]*0 = pr[I=1]

#Linearity of Expectation
For Xi on a sample space S:

E(Sum_i(Xi)) = Sum(E Xi)

even works when Xi are not independent

E(Sum(2dice)) = Sum_i(E Sum_i(dice)) = 3.5 + 3.5 = 7

#Process to Server Example

Assign n process to n servers.

Servers = {1,2,..n}
Processes = {1,2,..m}
Sample Space = Servers x Servers x Servers.. n times = m^n

Y = no of processes assigned to first server
Problem : What is E Y 

Consider :
X_j = 1 if jth process assigned to first server
    = 0 if the jth process not assigned

S = {0,1,2,..m}
=> Pr(X_j = 1) = 1/m
E(X_j) = Pr(X_j = 1)*1 + Pr(X_j = 0)*0 = 1/m

Y = Sum(j=1 to n) X_j
E Y = Sum(E X_j)

E Y = n*1/m  = n/m

#Conditional Probability
Pr[X|Y] = Probability of X given Y = Pr[X n Y] / Pr[Y]

Probability of one of the dice being 1, given sum of 2 dice is 7

Sample Space : {(1,1)..(6,6)} .. 36 elements

Y = {(1,6),(6,1),(4,3),(3,4),(5,2),(2,5)} .. 6 elements

X = {(1,1),(1,2),..., (2,1),(3,1),..(6,1)} ... 11 elements

X n Y = {(1,6), (6,1)}

P[X|Y] = Pr(Xny)/Pr[Y] = 2/36 / 6/36 = 1/3

#Independence of Events
Intuitively, X and Y are independent iff knowing that X has occured gived no information if Y has occured or vice versa.

Pr[ X | Y ] = Pr [X]
Pr[ Y | X ] = Pr [Y]

X and Y are independent iff :
Pr{X n Y} = Pr{X}.Pr{Y}

=> Pr(X | Y) = Pr(X), and vice versa

#Independence of Random Variables

Value taken on by one gives no information on value taken on by the other.

Pr[A=a] and Pr[B=b] = Pr[A=a].Pr[B=b]

E[A.B] = Sum(a,b) Pr(A=a and B=b).a.b
       = Sum(a,b) a.Pr(A=a).b.Pr(B=b) ... assuming independence of A and B
       = Sum_a a.Pr(A=a)(Sum_b Pr(B=b))
       = E[A].E[B]

#Example
Let X1, X2 in {0,1}, X3 = X1 xor X2

Sigma = { 000, 011, 101, 110}

X1, X3 : Independent

    X1 = {0,0,1,1}
    X3 = {0,1,1,0}
    X1.X3 = {0,0,1,0}
    E(X1) = 1/2
    E(X2) = 1/2
    E(X1.X3) = 1/4 = 1/2.1/2

X1X3 and X2 are not independent
    X1.X3 = {0,0,1,0}
    X2 = {0,1,0,1}
    X1.X3.X2 = {0,0,0,0}
    E(X1.X3.X2) = 0
    E(X1.X3) = 1/4
    E(X2) = 1/2
    E(X1.X3).E(X2) = 1/8













