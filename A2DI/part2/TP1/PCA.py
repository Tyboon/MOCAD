from numpy import mean, std, cov
from scipy import linalg
import csv
import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

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
    tot = sum(val[:1]) / p
    print "Pourcentage de variance totale : %f" % tot
    Y_1 = np.dot(X_, U[:,:1])
    plt.plot(Y_1[:50,0], np.zeros(50), 'ro')
    plt.plot(Y_1[50:100,0], np.zeros(50), 'bo')
    plt.plot(Y_1[100:150,0], np.zeros(50), 'go')
    plt.title('1D')
    plt.show()
    plt.clf()

    err = np.mean((np.absolute(R - np.dot(Y_1, U[:,:1].T))))
    print " Erreur absolue moyenne : %f" % err  
	 
	 
	 # 2D
    tot = sum(val[:2]) / p
    print "Pourcentage de variance totale : %f" % tot
    Y_2 = np.dot(X_, U[:,:2])
    plt.plot(Y_2[:50,0],Y_2[:50,1], 'ro')
    plt.plot(Y_2[50:100,0],Y_2[50:100,1], 'bo')
    plt.plot(Y_2[100:150,0],Y_2[100:150,1], 'go')
    plt.title('2D')
    plt.show()
    plt.close()
    
    err = np.mean((np.absolute(R - np.dot(Y_2, U[:,:2].T))))
    print " Erreur absolue moyenne : %f"%err

    
	 # 3D
    tot = sum(val[:3]) / p
    print "Pourcentage de variance totale : %f" % tot
    Y_3 = np.dot(X_, U[:,:3])
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ax.scatter(np.asarray(Y_3[:50,0]),np.asarray(Y_3[:50,1]),np.asarray(Y_3[:50,2]),color='r',marker='o')
    ax.scatter(np.asarray(Y_3[50:100,0]),np.asarray(Y_3[50:100,1]),np.asarray(Y_3[50:100,2]),color='b',marker='o')
    ax.scatter(np.asarray(Y_3[100:150,0]),np.asarray(Y_3[100:150,1]),np.asarray(Y_3[100:150,2]),color='g',marker='o')
    plt.title('3D')
    plt.show()
    
    err = np.mean((np.absolute(R - np.dot(Y_3, U[:,:3].T))))
    print " Erreur absolue moyenne : %f"%err
    return 

if __name__ == '__main__' :
    main()
