
###Needleman-Wunch Score 

###Sub-problem

--- X ----
--- Y ----

for the last position, there are 3 possibilities :

P_ij = min( Pi-1,j-1 + alpha_xi.yj,Pi,j-1 + alpha(gap),Pi-1,j + alpha(gap))

P0,i and Pi,0 = i * penalty_gap

Fpr i = 1 to m
  for j = 1 to n
    A[i.j] = min(...)




