#Emilie Allart
# td2

with(MABSys):

R1 := NewReaction(2*AA+3*BB,CC+2*DD,MassActionLaw(k1));
R2 := NewReaction(CC+2*DD,2*AA+3*BB,MassActionLaw(k2));
R3 := NewReaction(AA+DD,EE+BB,MassActionLaw(k3));
R4 := NewReaction(EE+BB,AA+DD,MassActionLaw(k4));

RS := [R1,R2,R3,R4];

eq := Equilibria(RS);

cl := ConservationLaws(RS);

sys := [ op(eq), op(cl) ];

with(RegularChains);

R := PolynomialRing([AA,BB,CC,DD,EE,AA_0,BB_0,CC_0,DD_0,EE_0,k1,k2,k3,k4] );

out := Triangularize(sys, R);

map(Equations, out, R);

Equi := Equations(out[1],R);

sys0 := subs( {AA_0=5, BB_0=10, CC_0=1, DD_0=3, EE_0=18, k1=5, k2=3, k3=5, k4=8}, sys);

L := [solve(sys0)];

allvalues(L[2]);

evalf(%);
