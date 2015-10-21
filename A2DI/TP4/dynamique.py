import numpy as np

#A, A_p, B, B_p

def init() : 
    pass


def get_r_pi() :
    '''
    Récompense moyenne immédiate
    param : S tableau de state
    '''
    
    r_pi = []
    # pour chaque etat de depart x = i/5 y = i mod 5
    for (i in np.range(25)) :
        cum = 0
        # pour chaque action
        for (a in np.range(4)) :
            s = 0
            # pour chaque etat d'arrivee s_p
            for (j in np.range(25)) :
                t = 0
                if (move(i, a) == j)
                    t = 1
                r = rec(i,j,a)
                s += t * r
            cum += 0.25 * s
        r_pi.append(cum)
    return r_pi


def move(s,a) :
    

