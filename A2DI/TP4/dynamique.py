import numpy as np
from numpy.linalg import inv

pt_a = 1
pt_b = 3 
pt_a_p = 21
pt_b_p = 13

def init() : 
    pass

def get_v(alpha = 0.9) :
	r_pi = get_r_pi()
	p_pi = get_p_pi()
	ident = np.identity(len(p_pi))
	return np.dot(inv(ident - np.dot(alpha, p_pi)), r_pi)

def get_p_pi(pi = 0.25) :
	p = np.zeros((25,25))
	for s in range(25) :
		if s == pt_a :
			p[s, pt_a_p] = 1
		elif s == pt_b :
			p[s,pt_b_p] = 1
		else :
			for s_new in range(25) :
				tmp = 0
				for a in range(4) :
					m_x, m_y = move(s,a)
					if m_x < 0 :
					  m_x += 1
					elif m_x >= 5 :
					  m_x -= 1
					if m_y < 0 :
					  m_y += 1
					elif m_x >= 5 :
						m_y -= 1
					if (m_x * 5 + m_y) == s_new :
						tmp += pi * 1  # ici pi * (0.85 * (cost a) + 0.05 * ( sum autres cost)) 
				p[s,s_new] = tmp
	return p
			

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
	return r_pi


def move(s,a) :
    '''
    Renvoie les coordonnees d'arrivee d'apres un mouvement et un etat de depart
    '''
    if (a == 0) :
        x = (s/5) - 1
        y = (s % 5)
    elif (a == 1) :
        x = s/5 + 1
        y = (s % 5)
    elif (a == 2) :
        x = s/5 
        y = (s % 5) - 1
    else :
        x = s/5 
        y = (s % 5) + 1

    return x, y


def cost(s,a) :
	if (s == pt_a) :
		return 10
	if (s == pt_b) :
		return 5
	x, y = move(s,a)
	if (x < 0 or y <0 or x >= 5 or y >= 5) :
		return -1
	return 0


