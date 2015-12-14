from numpy import mean, std, cov
from scipy import linalg
import csv
import numpy as np
import matplotlib.pyplot as plt

def parse (filename) :
    matrix, c = [], []
    with open(filename,'r') as f :
        reader = csv.reader(f, delimiter=",")
        for row in reader :
            r = map(float, row[:-1])
            c.append(row[-1])
            matrix.append(r)
    return np.asmatrix(matrix), c 

def main() :
    X, c = parse('iris.data')
    n,p = X.shape
    # Reduction de X
    X_ = (X - np.mean(X,axis=0)) / np.std(X,axis=0)
    
    R = 1./n * np.dot(X_.T,X_) 
    
    val, U = linalg.eig(R)
    # 1D 
    Y_1 = np.dot(X_, U[:,0])
    plt.plot(Y_1[0], np.zeros(n))
    # 2D
    Y_2 = np.dot(X_, U[:,:2])
    # 3D
    Y_3 = np.dot(X_, U[:,:3])
    return Y_
