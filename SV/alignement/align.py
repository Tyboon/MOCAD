import numpy as np

match = 1
mis = 0
indel = -1

def init(u,v) :
    len_u = len(u)
    len_v = len(v)
    if len_u < len_v :
        read = u
        len_read = len_u
        genome = v
        len_gen = len_v
    else :
        read = v
        len_read = len_v
        genome = u
        len_gen = len_u
    matrix = np.full((len_read+1,len_gen+1), float("-inf"))
    matrix[0][0] = 0

    for i in range(1,len_gen+1) :
        if (i < len_read+1) :
            matrix[i][0] = matrix[i-1][0] + indel
        matrix[0][i] = matrix[0][i-1] + indel

    for i in range(1,len_read+1) :
        for j in range(1,len_gen+1) :
            if read[i-1] == genome[j-1] :
                matrix[i][j] = max((matrix[i-1][j-1] + match), (matrix[i][j-1] + indel), (matrix[i-1][j] + indel))
            else :
                 matrix[i][j] = max((matrix[i-1][j-1] + mis), (matrix[i][j-1] + indel), (matrix[i-1]    [j] + indel))
    return matrix, len_read, len_gen

def backtrap(matrix, len_read, len_gen) :
    score = max(matrix[len_read])
    score_index = np.where(matrix[len_read] , score)
    start = score_index[0]
    i = read_len-1
    j = start
    print "SCORE %d" % score
    print "BACKTRAP : "
    back = ""
    while (i!=0 and j!=0) :
        if (matrix[i][j] == matrix[i-1][j-1] - match) or (matrix[i][j] == matrix[i-1][j-1] - mismatch) :
            i -= 1
            j -= 1
        elif (matrix[i][j] == matrix[i-1][j] - indel) :
            i -= 1
        elif (matrix[i][j] == matrix[i][j-1] - indel) :
            j -= 1
        else : 
            return
        print("%d - %d : %d" % i, j, matrix[i][j]) 

if __name__ == "__main__" :
    v = 'GCAATATCACGCAT'
    u = 'TCCATG'

    matrix, len_read, len_gen = init(u,v)
    print matrix
    backtrap(matrix, len_read, len_gen)

