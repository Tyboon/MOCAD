import numpy as np

pt_a = 1
pt_b = 3 
pt_a_p = 21
pt_b_p = 18

def init() : 
    pass


def get_r_pi(pi = 0.25) :
	'''
	Recompense moyenne immediate
	param : S tableau de state
	'''
	r_pi = []
	# pour chaque etat de depart x = i/5 y = i mod 5
	for i in range(25) :
		cum = 0
		# pour chaque action
		for a in range(4) :
			cum += pi * cost(i, a)
		r_pi.append(cum)
		print cum
	return r_pi


def move(s,a) :
    '''
    Renvoie les coordonnees d'arrivee d'apres un mouvement et un etat de depart
    '''
    if (a == 0) :
        x = (s/5)
        y = (s % 5) - 1
    elif (a == 1) :
        x = s/5
        y = (s % 5) + 1
    elif (a == 2) :
        x = s/5 - 1
        y = (s % 5) 
    else :
        x = s/5 + 1
        y = (s % 5)

    return x, y


def cost(s,a) :
	x, y = move(s,a)
	if (x * y < 0 or x >= 5 or y >= 5) :
		return -1
	if ( (x*5 + y) == pt_a ) :
		return 10
	if ( (x*5+y) == pt_b) :
		return 5
	return 0


