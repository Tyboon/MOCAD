# -*- coding: utf-8 -*-
import numpy as np
import matplotlib.pyplot as plt

# Les données

taille_h = np.loadtxt("taille_h.txt")
taille_f = np.loadtxt("taille_f.txt")

# Calcul des moyennes et variances

moy_h = np.mean(taille_h)
moy_f = np.mean(taille_f)

var_h = np.std(taille_h)
var_f = np.std(taille_f)

print "La moyenne des hommes est {0} et la l'écart type {1}.".format(moy_h,var_h)
print "La moyenne des femmes est {0} et la l'écart type {1}.".format(moy_f,var_f) 

#Calcul des probabilités a priori

nb_h = len(taille_h)
nb_f = len(taille_f)

nb = nb_h+nb_f

p_f = float(nb_f)/nb
p_h = float(nb_h)/nb


print "La probabilité d'être une femme est {0} et celle d'être un homme est {1}.".format(p_f,p_h) 
print "\n"

#Calcul des limites des bins

bins_h = [0]+range(160,220,5)+[300] #bins_h[0] nb d'hommes de taille < 160m (bins_h[13] idem > 220)
bins_f = [0]+range(160,220,5)+[300] #bins_f[0] nb de femmes de taille < 160m (bins_f[13] idem > 220)

#Calcul des histogrammes 

hist_h, edges_h = np.histogram(taille_h,bins=bins_h)
hist_f, edges_f = np.histogram(taille_f,bins=bins_f)


#Calcul des vraisemblances 

print "La probabilité de mesurer moins d'1,60 m pour un homme est de {0}.".format(float(hist_h[0])/nb_h)
print ""
print "La probabilité de mesurer moins d'1,60 m pour une femme est de {0}.".format(float(hist_f[0])/nb_f)
print "\n"


#Calcul des probabilités a posteriori

def proba_x_with(x,y) :
    if y == 'h' :
        nb = nb_h
    else : 
        nb = nb_f
    return float(hist_h[bins_h.index(int(x))])/nb

p_180_h = float(hist_h[bins_h.index(180)])/nb_h # proba de mesurer 180-185 sachant que c'est un homme
p_180_f = float(hist_f[bins_f.index(180)])/nb_f # idem sachant que c'est une femme

p_160_h = float(hist_h[bins_h.index(160)])/nb_h # _____ 160-165 sachant que c'est un homme
p_160_f = float(hist_f[bins_f.index(160)])/nb_f # idem sachant que c'est une femme



p_h_180 = p_180_h * p_h / (p_180_h * p_h + p_180_f * p_f) # proba d'être un homme si on mesure 180-185
p_f_180 = p_180_f * p_f / (p_180_h * p_h + p_180_f * p_f) # idem pour la femme

p_h_160 = p_160_h * p_h / (p_160_h * p_h + p_160_f * p_f) # proba d'être un homme si on mesure 160-165
p_f_160 = p_160_f * p_f / (p_160_h * p_h + p_160_f * p_f) # idem pour la femme

print "La probabilité d'être un homme quand on mesure entre 1,6 et 1,65 m est de {0}.".format(p_h_160)
print ""
print "La probabilité d'être une femme quand on mesure entre 1,6 et 1,65 m est de {0}.".format(p_f_160)
print ""
print "La probabilité d'être un homme quand on mesure entre 1,8 et 1,85 m est de {0}.".format(p_h_180)
print ""
print "La probabilité d'être une femme quand on mesure entre 1,8 et 1,85 m est de {0}.".format(p_f_180)
print "\n"


#Calcul du risque

R = 0

for h in taille_h :
    t = min([max([bins_h[1],int(h/5) * 5]),bins_h[len(bins_h)-2]]) 
    # tranche de taille à laquelle appartient h dans les limites des tailles existantes
    p_t_h = float(hist_h[bins_h.index(t)])/nb_h * p_h # proba d'avoir la taille t sachant qu'on est un homme
    p_t_f = float(hist_f[bins_f.index(t)])/nb_f * p_f # proba d'avoir la taille t sachant qu'on est une femme
    if p_t_h < p_t_f : # si mal classé
        R +=1

for f in taille_f:
    f = min([max([bins_f[1],int(f/5) * 5]),bins_h[len(bins_f)-2]])
    p_t_h = float(hist_h[bins_h.index(f)])/nb_h * p_h
    p_t_f = float(hist_f[bins_f.index(f)])/nb_f * p_f
    if p_t_f < p_t_h :
        R +=1

print "Risque : {0}".format(float(R)/(nb_f+nb_h)) # R/
'''
#Affichage de l'histogramme

n,bins,ignore = plt.hist(taille_h,bins=bins_h)

#plt.plot(bins, 1/(sigma_h * np.sqrt(2 * np.pi)) * np.exp( - (bins - mu_h)**2 / (2 * sigma_h**2) ),linewidth=2, color='r')

plt.show()

'''

def proba_taille(x) :
    #print (float(hist_h[bins_h.index(x)]) + float(hist_f[bins_f.index(x)]))/(nb_f+nb_h)
    return (proba_x_with(x,'h') * p_h + proba_x_with(x,'f') * p_f)

def classify_bayes(x) :
    p_h_pred = proba_x_with(x,'h') * p_h / proba_taille(x)
    p_f_pred = proba_x_with(x,'f') * p_f / proba_taille(x)
    if p_h_pred > p_f_pred :
        return 'h'
    return 'f'

print classify_bayes(180)

def classify_joint(x) :
    nb_x_h = float(hist_h[bins_h.index(x)])
    nb_x_f = float(hist_f[bins_f.index(x)])
    p_h_pred = (nb_x_h / nb) / proba_taille(x)
    p_f_pred = (nb_x_f / nb) / proba_taille(x)
    if p_h_pred > p_f_pred :
        return 'h'
    return 'f'

print classify_joint(180)

def error_h_bayes() :
    err = 0
    for h in taille_h :
        if classify_bayes(h) != 'h' :
            err += 1
    return err

def error_f_bayes() :
    err = 0
    for f in taille_f :
        if classify_bayes(f) != 'f' :
            err += 1
    return err

def MAP() :
    eh = error_h_bayes()
    ef = error_f_bayes()
    return (eh + ef)/nb

print "MAP {0}".format(MAP())

def ML(alpha = 0.1, beta = 0.5) :
    eh = error_h_bayes()
    ef = error_f_bayes()
    return alpha * (eh/nb_h) + beta * (ef/nb_f)

print "ML 50h-50f : {0}".format(ML())
print "ML 25h-75f : {0}".format(ML(75,25))


