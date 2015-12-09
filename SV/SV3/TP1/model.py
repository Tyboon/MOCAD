import numpy as np
import random as rdm
import math
import matplotlib.pyplot as plt

# reaction matrix 
#mat_stoechio = np.matrix('-1 -1 1; -2 0 1; -2 -2 2')
#mat_stoechio = np.matrix('-1')
#react = np.matrix('-1 -1 0; -2 0 0; -2 -2 0')
#product = np.matrix('0 0 1; 0 0 1; 0 0 2')
react = np.matrix('-1')
product = np.matrix('0')


# initial quantity
#quantity = [3,2,0]
quantity = [10000000]

# number of composites
nb_compo = len(quantity)

# parameter a : proba of reaction
#a = [0.1,0.1,0.1]
a = [0.1]

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
        k = react[i,c]
        if k<0 : ################# TOSEE utile ?? 
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
        print t, quantity
        if tho == -1 :
            break
        r = get_reaction()
        t = t + tho
        out.append(t)
        for i in range(nb_compo):
            quantity[i] += react[r,i] + product[r,i]
            out.append(quantity[i])
            if quantity[i] == 5000000 :
                    t_ = t
        X.append(out)
    print t_
    X = np.array(X)
    p1 = plt.plot(X[:,0],X[:,1])
    p2 = plt.plot([t_,t_], [0,10000000])
    plt.legend([p1,p2],["A(t)","1/2A(0)"])
    plt.title('A(0) = 10000000')
    plt.ylabel('[A]')
    plt.xlabel('temps')
    plt.show()
