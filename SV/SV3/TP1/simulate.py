import numpy as np
import random as rdm
import math
import matplotlib.pyplot as plt

# reaction matrix 
mat_stoechio = np.matrix('-1 -1 1; -2 0 1; -2 -2 2')
#mat_stoechio = np.matrix('-1')

# initial quantity
quantity = [3,2,0]
#quantity = [1000]

# number of composites
nb_compo = len(quantity)


# parameter a : proba of reaction
a = [0.1,0.1,0.1]
#a = [0.1]

# nb reaction
nb_reaction = len(a)

# parameter h
h = np.zeros(nb_reaction)

# maximal reaction time
t_max = 100

def get_h(i) :
    tmp = 1
    for c in range(nb_compo):
        n = quantity[c]
        k = mat_stoechio[i,c]
        if k<0 : 
            if  n < -k :
                return 0
            cpt1 = 1
            cpt2 = 1
            for i in range(-k) :
                cpt1 *= (n-i)
                cpt2 *= (i+1)
            tmp *= cpt1/cpt2
    return tmp

def get_tho() :
    cum = 0.
    for i in range(nb_reaction) :
        h[i] = get_h(i)
        cum += h[i]*a[i]
    if cum == 0 :
        return -1
    cum = 1.0/cum
    return cum * math.log(1.0/(1.0-rdm.random()))

def get_reaction() :
    cumul = []
    tot = 0
    for r in range(nb_reaction) :
        tot += a[r] * h[r]
        cumul.append(tot)
    rand = rdm.random() * tot
    for r in range(nb_reaction) :
        if rand <= cumul[r] :
            return r
            


if __name__ == "__main__" :
    t = 0
    X = []
    while t < t_max :
        out = [] # form t q1 q2 q3
        tho = get_tho()
        if tho == -1 :
            break
        r = get_reaction()
        t = t + tho
        out.append(t)
        for i in range(nb_compo):
            quantity[i] += mat_stoechio[r,i]
            out.append(quantity[i])
        X.append(out)
        print out
    X = np.array(X)
    plt.plot(X[:,0],X[:,1])
    plt.title('A(0) = 10')
    plt.ylabel('[A]')
    plt.xlabel('temps')
    plt.show()
