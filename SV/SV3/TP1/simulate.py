import numpy as np
import random as rdm
import math

# reaction matrix 
mat_stoechio = np.matrix('-1 -1 1; -2 0 1; -2 -2 2')

# number of composites
nb_compo = len(mat_stoechio[0])

# initial quantity
quantity = [3,2,0]

# parameter a : proba of reaction
a = [0.4,0.3,0.3]

# parameter h
h = np.zeros(nb_compo)

# maximal reaction time
t_max = 10

def get_h(i) :
    tmp = 1
    for c in range(nb_compo):
        n = quantity[c]
        k = mat_stoechio[i,c]
        tmp *= n! / ((n-k)! * k!)
    return tmp

def get_tho() :
    cum = 0
    for i in range(nb_compo) :
        h[i] = get_h(i)
        cum += h[i]*a[i]
    cum = 1/cum
    return cum * math.log(1/(1-rdm.random()))

def get_reaction() :


def main() :
    t = 0
    while t < t_max :
        tho = get_tho()
        r = get_reaction()
        t = t + tho
        
