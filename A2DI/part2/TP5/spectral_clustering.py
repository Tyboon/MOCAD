#-*- coding: utf-8 -*-

import random
import numpy as np
from numpy import arange
import math
from matplotlib import pyplot as plt
from sklearn import datasets
from scipy.cluster.vq import kmeans, whiten, vq


def load_iris_data():
    iris = datasets.load_iris()
    X = iris.data[:, :4]
    C = iris.target

    return (X, C)

def degree_matrix(e_graph):
    d = np.zeros((len(e_graph), len(e_graph)))
    i = 0
    while i < len(e_graph):
        line = e_graph[i]
        count = 0
        for j in range(0, len(line)):
            if line[j] != 0:
                count+=1
        d[i,i] = count
        i += 1
    return d

def similarity(xi, xj, sigma = 1):
       return math.exp(-(np.linalg.norm(np.subtract(xi, xj))) / math.pow(sigma, 2))

def eps_graph(X, eps = 0.5, sigma = 1) :
    res = np.zeros((len(X), len(X)))
    for i, xi in enumerate(X) :
        for j, xj in enumerate(X) :
            sim = similarity(xi, xj, sigma)
            if sim > eps :
                res[i, j] = sim
            else:
                res[i, j] = 0
    return res


def spectral_vector_matrix(L, k):
    U,s,_ = np.linalg.svd(L, full_matrices=True)
    return U[:,:k]


def kmeans_clustering(data, k):
    centroids,_ = kmeans(data, k)
    clusters,_ = vq(data, centroids)
    return clusters


def spectral_clustering(data, k, sigma = 1, epsilon = 0.5):
    e_graph = eps_graph(data, epsilon, sigma)
    D = degree_matrix(e_graph)
    W = eps_graph(data, eps=-1)
    L = np.subtract(D, W)
    U_k = spectral_vector_matrix(L, k)
    clusters = kmeans_clustering(U_k, k)
    return clusters


def count_error(clusters, classes):
    count = 0
    for i in range(len(clusters)):
        if not clusters[i] == classes[i]:
            count += 1
    return count
    

def mean_kmeans_error(data, classes, k, n):
    errors = 0
    for i in range(n):
        clusters = kmeans_clustering(data, k)
        errors += count_error(clusters, classes)
    return errors / n


def mean_spectral_error(data, classes, k, n, sigma=0.5, epsilon=0.5):
    errors = 0
    for i in range(n):
        clusters = spectral_clustering(data, k, sigma, epsilon)
        errors += count_error(clusters, classes)
    return errors / n



def print_errors(X, C, k, n):
    
    print "Kmeans error : "
    print mean_kmeans_error(X, C, k, n)
   
    print "\nSpectral : "
    print mean_spectral_error(X, C, k, n)

    print "\nTest paramètre sigma epsilon sur spectral "
    for sigma in arange(0.1, 1., 0.2):
        for epsilon in arange(0.1, 1., 0.2):
            error = mean_spectral_error(X, C, k, n, sigma, epsilon)
            print("sigma: {}, epsilon: {}, error: {}".format(sigma, epsilon, error))
    
    
def main():
    X, C = load_iris_data()
    k = 3

    print_errors(X, C, k, 5)

if __name__ == '__main__':
    main()

