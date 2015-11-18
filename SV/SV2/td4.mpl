with(MABSys):
with (plots) :
R1 := NewReaction(E+S,C,MassActionLaw(k1),fast=true);
R2 := NewReaction(C,E+S,MassActionLaw(km1),fast=true);
R3 := NewReaction(C,E,MassActionLaw(k2));

RS := [R1,R2,R3]:

sol1 := NumericalSolution(RS, ics = { C(0)=0, E(0)=10, S(0)=20}, params = { k1=10, km1=5, k2=8} );
#sol2 := NumericalSolution(RS, ics = { C(0)=0, E(0)=10, S(0)=20}, params = { k1=20, km1=10, k2=8} );
plotsetup(X11);

#P1 := PlotSolution(sol1, t=0..1.5, odeplotOptions={numpoints=500});
#P2 := PlotSolution(sol2, t=0..1.5, odeplotOptions={numpoints=500});
#display([P1,P2]);


#PlotSolution(sol1, t=0..1.5, listPlots=[[t,k1*E*S-km1*C]]);
PlotSolution(sol1, t=0..1.5, listPlots=[[t,k1*E*S-km1*C]]);


