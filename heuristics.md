(avg) Sum pj / m <= (max) maxj pj

superopt = m . avg / 2

superopt <= opt <= greedy <= superopt + 0.5 max

greedy / opt <= superopt + max / opt <= superopt + max / superopt
              = 1 + max*.5 / superopt = 1 + max / m . avg / 2 
              = 1 + max/m.avg = 2

greedy - superopt <= (max) maxj pj

greedy <= max + opt 

opt  >= m. avg / 2 

superopt + 0.5 min
1 + 0.5 min / m. avg / 2 = 1 + min / m. avg <= 1 + 1/m

m >= 2

