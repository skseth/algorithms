#Problem: Closest pair of points in 2-D space

* Make copies of pairs, one sorted by x-coordinate (Px, Py), one sorted by y-coordinate (O(nlogn))

* ClosestPair(Px,Py)
    - centreidx = Px.length / 2
    - centre = Px(centreidx)
    - (Qx,Rx) = Px.partition(x <= centre.x)
    - (Qy,Ry) = Py.partition(x < centre.x)
    - (p1,q1) = ClosestPair(Qx,Qy)
    - (p2,q2) = ClosestPair(Rx,Ry)
    - d1 = d(p1,q1), d2 = d(p2,q2)
    - d = min(d1,d2)
    - P3(p3,q3) = ClosestSplitPair(Px,Py,centre.x,d)
    - d3 = d(P3)
    - bestof(d1,d2,d3)

* ClosestSplitPair (O(n log n))
    - Sy = Py filter (x >= (centre.x - d) && x <= (centre.y - d))
    - best = d
    - bestpair = ()
    - For i = 0 to len(Sy) - 7
        + p = Sy(i)
        + for j = 1 to 7
            * q = Sy(i+j)
            * if d(p,q) < best, bestpair = (p,q), best = d(p,q)
    


