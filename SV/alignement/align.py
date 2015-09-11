import numpy as np

match = 5
mis = -4
indel = -10

MATCH = 0
MIS = 1
INS = 2
DEL = 3


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
    matrix = np.zeros((len_read+1,len_gen+1))
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
    score_index = np.nonzero(matrix[len_read] == score)[0][0]
    op = []

    i = len_read
    j = score_index
    print "SCORE %d" % score
    print "BACKTRAP : "
    print("score_index : %d"%score_index)
    tmp = len_gen

    while (tmp > j) :
        op.append(INS)
        tmp -= 1

    while (i!=0 and j!=0) :
        if (matrix[i][j]-match == matrix[i-1][j-1]):
            i -= 1
            j-= 1
            op.append(MATCH)
            print("MATCH")
        elif (matrix[i][j]-mis == matrix[i-1][j-1]) :
            i -= 1
            j -= 1
            op.append(MIS)
            print("MIS")
        elif (matrix[i][j]-indel == matrix[i-1][j]) :
            i -= 1
            op.append(INS)
            print("INS")
        elif (matrix[i][j]-indel == matrix[i][j-1]) :
            j -= 1
            op.append(DEL)
            print("DEL")
        else : 
            print("OTHER %d "% i) 
            return
        print("%d - %d : %d" % (i, j, matrix[i][j]))
    if j > 0 :
        while j > 0 :
            op.append(INS)
            j -= 1
    return op

def print_result(op, genome, read) :
    seq_g, seq_r, seq_o ="", "", ""
    op.reverse()
    cpt_g= 0
    cpt_r = 0
    for i in range(len(op)) :
        if op[i] == MATCH :
            seq_g += genome[cpt_g]
            seq_r += read[cpt_r]
            cpt_g += 1
            cpt_r += 1
            seq_o += '|'
        elif op[i] == MIS :
            seq_g += genome[cpt_g]
            seq_r += read[cpt_r]
            cpt_g += 1
            cpt_r += 1
            seq_o += ' '
        elif op[i] == INS :
            seq_g += genome[cpt_g]
            seq_r += '-'
            cpt_g += 1
            seq_o += ' '
        else :
            seq_g += '-'
            seq_r += read[cpt_r]
            cpt_r += 1
            seq_o += ' '
    print seq_g+'\n', seq_o+'\n', seq_r+'\n'

if __name__ == "__main__" :
    #v = 'GCAATATCACGCAT'
    #u = 'TCCATG'

    v = 'tgggatggatcaaccctaacagtggtggcacaaactatgcacagaagtttcagggcagggtcaccatgaccagggacacgtccatcagcacagcctacatggagctgagcaggctgagatctgacgacacggccgtgtattactgtgcgagaga'
    u = 'ttgcacgcattgattgggatgatgataaatactacagcacatctctgaagaccaggctcaccatctccaaggacacctccaaaaaccaggtggtccttacaatgaccaacatggaccctgtggacacggccgtgtattactg'
    matrix, len_read, len_gen = init(u,v)
    print matrix
    op = backtrap(matrix, len_read, len_gen)
    print_result(op, v, u)

