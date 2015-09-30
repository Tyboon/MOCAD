
from collections import OrderedDict

def createSA(gap) :
    '''
    Create the suffix array of gap    
    param gap : the chain to treat
    return d : the dictionnary suffix-index
    '''
    l = range(len(gap))
    l = sorted(l,key=lambda i : gap[i:])
    return l

def BWT(genome, SA) :
    l = []
    len_gen = len(genome)
    for i in range(len_gen) :
        letter = genome[(SA[i]-1+len_gen)%len_gen]
        l.append(letter)
    return l



if __name__ == "__main__" :
    
    genome = 'acatgcatgt$'
    SA = createSA(genome)
    L = BWT(genome, SA)
    print L

 
