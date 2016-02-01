import numpy as np
from math import exp, pow, fabs
import csv
import random

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
    exp(- np.linalg.norm(xi-xj)/pow(sigma,2))


'''
    Matrice de similarite
'''
def e_graph(X, len_data) :
    s = np.zeros((len_data, len_data))
    for i in range(len_data) :
        for j in range (len_data) :
            if (similarity (X[i],X[j]) < epsilon) :
                s[i,j] = 1
    return s

def main() :
    X,Y = parse('iris.data')
    n,p = X.shape
    graph = e_graph(X, n)

	 // 3 points random
	 rdm = []
	 rdm.append(random.randrange(0,50))
	 rdm.append(random.randarange(50, 100))
	 rdm.append(random.randarange(100,150))
	 
	 // Init y_0
	 y_0 = np.zeros((len(Y),3)) // 3 classes
	 for r in rdm :
		 if Y[r] == 'Iris-setosa' : 
			 y_0[r] = [0,0,1]
		 if Y[r] == 'Iris-versicolor' :
			 y_0[r] = [0,1,0]
		 else :
			 y_0[r] = [0,0,1]
	 y_i = np.zeros((len(Y),3))

    D = np.diag(np.sum(graph, axis=1))
	 
	 while not np.array_equal(y_0,y_i) :
		 y_0 = y_i
		 y_i = np.dot(np.linalg.inv(D), np.dot(e_graph, y_0)
	 
if __name__ == '__main__' :
    main()
