
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

def search(genome, SA, seed) :
    l = []
    gap = len(seed)
    len_gen = len(genome)

    for i in SA :
        print i
        if i+gap < len_gen :
            print genome[i:i+gap]
            if 'ca' > genome[i:i+gap] :
                return l
            elif 'ca' == genome[i:i+gap] :
                print 'yes'
                l.add(i)
    return l

if __name__ == "__main__" :
    
    genome = 'acatgcatgt'
    SA = createSA(genome)
    print SA
    #L = BWT(genome, SA)
    #print L
    print search(genome, SA, 'ca')  
