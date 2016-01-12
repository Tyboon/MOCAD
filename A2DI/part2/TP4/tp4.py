import numpy as np

def e_graph(data, len_data, sigma) :
    s = np.zeros((len_data, len_data))
    for i in range(len_data) :
        for j in range (len_data) :
            s[i,j] = exp(- norm(data[i]-data[j])/pow(sigma,2))
    return s
