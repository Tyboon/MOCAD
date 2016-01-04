import numpy as np
from scipy import linalg

def similarity(doc, req) :
    return np.dot(doc, req) / (np.linalg.norm(doc) * np.linalg.norm(req))

def reduce(u, e, v, k=2) :
	u = u[:,:k]
	e = e[:k,:k]
	v = v[:k,:]
	return u, e, v

def parse(d) :
	d = d.replace('.', '')
	d = d.replace(',', '')
	d = d.replace(':', '')
	d = d.replace('\'','')
	d = d.replace('\n','')
	return d

d1 = "Romeo and Juliet ."
d2 = "Juliet : O happy dagger ."
d3 = "Romeo die by dagger ."
d4 = "Live free or die , that's the New-Hampshire motto ."
d5 = "Did you know , New-Hampshire is in New England ."

q = "die dagger"

d1 = parse(d1).split()
d2 = parse(d2).split()
d3 = parse(d3).split()
d4 = parse(d4).split()
d5 = parse(d5).split()

doc = [d1, d2, d3, d4, d5]
q = q.split()
terms = list(set(d1+d2+d3+d4+d5))
term_doc, term_q = [], []

print terms

for w in terms :
    l = []
    for d in doc :
        tmp = d.count(w)
        l.append(tmp)
    term_doc.append(l)
    term_q.append(q.count(w))

term_doc = np.asarray(term_doc)

print term_q
print len(term_q)

simil = []
for i in range(5) :
    simil.append(similarity(term_doc[:,i], term_q))
print simil

u,e,v = linalg.svd(term_doc) 
e = np.diag(e)

u,e,v = reduce(u,e,v,2)

#print u, e, v 

d_k = np.dot(e, v)

q_k = np.dot(np.dot(np.linalg.inv(e), u.T), term_q) 

simil2 =[]
for i in range(5) :
	simil2.append(similarity(d_k[:,i], q_k))
print simil2



