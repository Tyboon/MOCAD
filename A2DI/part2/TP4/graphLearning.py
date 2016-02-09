#-*- coding: utf-8 -*-

import numpy as np
import math
import random
import sys

from sklearn import datasets

global r

'''
    Chargement de iris
'''
def load_iris_data():
    

    iris = datasets.load_iris()
    X = iris.data[:, :4]
    C = iris.target

    return (X, C)

def similarity(xi, xj, sigma):
    return math.exp(-(np.linalg.norm(np.subtract(xi, xj))) / math.pow(sigma, 2))

def degree_matrix(eps):
    len_eps = len(eps)
    d = np.zeros((len_eps, len_eps))

    for i, line in enumerate(eps):
        count = 0
        for j in range(0, len(line)):
            count += 1
        d[i,i] = count

    return d


def epsilon_graph(X, eps, sigma):
    len_X = len(X)
    eps_graph = np.zeros((len_X, len_X))

    for i, xi in enumerate(X):
        for j, xj in enumerate(X):
            sim = similarity(xi, xj, sigma)
            if sim > eps:
                eps_graph[i,j] = sim
            else:
                eps_graph[i,j] = 0

    return eps_graph

def select_rdm_point(X, C, k):
    global r
    r = []
    # choix alea d'un point de chaque classe
    r.append(random.randrange(0, 50))
    r.append(random.randrange(50, 100))
    r.append(random.randrange(100, 150))

    # choix alea pour le reste
    for i in range(k-3):
        r.append(random.randrange(0, 150))

    nb_learned = [0] * 3

    for n in r:
        nb_learned[C[n]] += 1

    print "k random choose : class  number_learned"
    for i in range(len(nb_learned)) :
        print i, nb_learned[i]

def init_y0(C, k):

    global r
    y_0 = np.zeros((len(C), 3))

    for r_0 in r:
        if C[r_0] == 0:
            y_0[r_0] = [1,0,0]
        elif C[r_0] == 1:
            y_0[r_0] = [0,1,0]
        else:
            y_0[r_0] = [0,0,1]

    return y_0

def iterate(eps_graph, d, y_i):
    global r

    y_0 = np.dot(np.dot(np.linalg.inv(d), eps_graph), y_i)

    for r_0 in r:
        if C[r_0] == 0:
            y_0[r_0] = [1,0,0]
        elif C[r_0] == 1:
            y_0[r_0] = [0,1,0]
        else:
            y_0[r_0] = [0,0,1]

    return y_0

def evaluate(y_pred, C):

    f_nb = 0
    errors = [0] * 3
    for i, line in enumerate(y_pred):
        am = np.argmax(line)
        if am != C[i] :
            f_nb += 1
            errors[C[i]] += 1

    print("\n Result : class  number_errors")
    for i  in  range(len(errors)) :
        print i, errors[i]

def iterative_harmonic_algorithm(X, C, k, eps, sigma):
    global r
    eps_graph = epsilon_graph(X, eps, sigma)
    d = degree_matrix(eps_graph)
    select_rdm_point(X, C, k)
    y_0 = init_y0(C, k)
    y_i= np.zeros((len(C), 3))

    while not np.array_equal(y_0,y_i) :
        y_i = y_0
        y_0 = iterate(eps_graph, d, y_0)

    evaluate(y_0, C)

if __name__ == '__main__' :
    k = int(sys.argv[1])
    epsilon = float(sys.argv[2])
    sigma = float(sys.argv[3])
    print " Parameter"
    print " k : {}".format(k)
    print " epsilon: {}".format(epsilon)
    print " sigma: {}\n".format(sigma)

    X,C = load_iris_data()
    iterative_harmonic_algorithm(X,C,k,epsilon,sigma)