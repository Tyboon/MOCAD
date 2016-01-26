import numpy as np
import scipy.linalg as ln
from numpy import linalg

A = np.array([[4,5],[6,5]])
B = np.array([[-4,10],[7,5]])
N,M = A.shape
Q = np.full(N,1./N)
def mat_diag(adj):
    return np.diag(np.sum(adj, axis=1))

def mat_transition (W, D) :
    return np.dot(np.linalg.inv(D), W)


#D = mat_diag(A)
#L = D - W

def power_iteration (Q, P) :
    Q_old = Q
    i = 0
    converged = False
    while not(converged) :
        Q_old = Q
        i += 1
        Q = np.dot(Q_old.T, P) / np.linalg.norm(np.dot(Q_old.T, P))
        converged = np.allclose(Q, Q_old)
    return Q, i

def main() :
    
    print power_iteration(Q, A)
    w, pi = ln.eig(A, left=True, right=False)
    print w
    print pi
    
    print power_iteration(Q, B)
    w, pi = ln.eig(B, left=True, right=False)
    print w
    print pi

if __name__ == '__main__' :
    main()

