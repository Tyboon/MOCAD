import numpy as np

A = 1, B = 3, A_p = 21, B_p = 18

def init() : 
    pass


def get_r_pi(pi = 0.25) :
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
            cum += pi * cost(i, a)
        r_pi.append(cum)
    return r_pi


def cost(s,a) :
    if (x * y < 0 || x >= 5 || y >= 5) :
        return -1
    if ( x*5 + y == A ) :
        return 10
    if (x*5+y == B :
        return 5
    return 0

        

def move(s,a) :
    '''
    Renvoie les coordonnees d'arrivee d'apres un mouvement et un etat de depart
    '''
    if (a == 0) :
        x = (s/5)
        y = (s mod 5) - 1
    elif (a == 1) :
        x = s/5
        y = (s mod y) + 1
    elif (a == 2) :
        x = s/5 - 1
        y = (s mod 5) 
    else :
        x = s/5 + 1
        y = (s mod 5)

    return x, y
