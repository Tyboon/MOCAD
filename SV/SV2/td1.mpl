#Emilie Allart
#** TD1 **

with(MABSys):

R1 := NewReaction(E+C,C,MassActionLaw(k1));
R2 := NewReaction(C,E+C,MassActionLaw(k_1));
R3 := NewReaction(C,E+P,MassActionLaw(k2));

RS := [R1,R2,R3] :

RateVector(RS);

StoichiometricMatrix(RS, [E,S,C,P]);

ReactionSystem2ODEs(RS, [E,S,C,P]);

ConservationLaws(RS);

Equilibria(RS);

sol := NumericalSolution(RS,
    ics = { C(0)=0, P(0)=0, E(0)=10, S(0)=20},
    params = { k1=10, km1=5, k2=8} ); 

plotsetup(X11); # Pour tracer depuis le mode texte

PlotSolution(sol, t=0..1.5);
