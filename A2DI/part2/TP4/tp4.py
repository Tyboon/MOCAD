import numpy as np
from math import exp, pow, fabs
import csv

epsilon = 1.0
sigma = 1.0

'''
    Retourne X et Y
'''
def parse (filename) :
    matrix, c = [], []
    with open(filename,'r') as f :
        reader = csv.reader(f, delimiter=",")
        for row in reader :
            r = map(float, row[:-1])
            c.append(row[-1])
            matrix.append(r)
    return np.asmatrix(matrix), c 


def similarity (xi, xj) :
    exp(- np.linalg.norm((data[xi]-data[xj]), 2)/pow(sigma,2))


'''
    Matrice de similarite
'''
def e_graph(data, len_data) :
    s = np.zeros((len_data, len_data))
    for i in range(len_data) :
        for j in range (len_data) :
            if (similarity (i,j) < epsilon) :
                s[i,j] = 1
    return s


def main() :
    X,Y = parse('iris.data')
    n,p = X.shape
    graph = e_graph(X, n)
    D = []



if __name__ == '__main__' :
    main()
