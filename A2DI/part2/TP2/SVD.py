import numpy as np 

def similarity(doc, req) :
    return np.dot(doc.T, req) / (np.linalg.norm(doc) * np.linalg.norm(req))

d1 = "Romeo and Juliet ."
d2 = "Juliet : O happy dagger ."
d3 = "Romeo die by dagger ."
d4 = "Live free or die , that's the New-Hampshire motto ."
d5 = "Did you know , New-Hampshire is in New England ."

q = "die dagger"

d1 = d1.split()
d2 = d2.split()
d3 = d3.split()
d4 = d4.split()
d5 = d5.split()

doc = [d1, d2, d3, d4, d5]
q = q.split()
terms = list(set(d1+d2+d3+d4+d5))
term_doc, term_q = [], []

for w in terms :
    l = []
    for d in doc :
        tmp = d.count(w)
        l.append(tmp)
    term_doc.append(l)
    term_q.append(q.count(w))

print term_doc[:][0]
simil = []
for i in range(5) :
    simil.append(similarity(term_doc[:,i], term_q))

print simil
