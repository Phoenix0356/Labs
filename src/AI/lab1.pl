female("XHN").
female("HXF").
female("FJH").
female("CSH").
female("FDZ").
female("WTL").
female("WZY").
female("LWY").
female("PYR").
female("XBT").
male("XYZ").
male("XZF").
male("XZT").
male("XX").
male("CDR").
male("FYY").
male("CXS").
male("CXK").
male("CJQ").
male("FZB").
male("FWB").

marry("WZY","XYZ",1948).
marry("XHN","CDR",1970).
marry("HXF","FYY",1969).
marry("LWY","XX", 1975).
marry("PYR","XZF",2009).
marry("FJH","CXS",2000).
marry("WTL","FZB",2002).
marry("CSH","CXK",2004).

birth("","", "WZY",1916).
birth("", "", "XYZ",1920).
birth("WZY","XYZ","XHN",1949).
birth("WZY","XYZ","XX",1951).
birth("XHN","CDR","CXS",1974).
birth("XHN","CDR","CSH",1977).
birth("HXF","FYY","FJH",1973).
birth("HXF","FYY","FZB",1976).
birth("LWY","XX","XZF",1982).
birth("FJH","CXS","CJQ",2003).
birth("WTL","FZB","FWB",2005).
birth("WTL","FZB","FDZ",2006).
birth("CXK","CSH","CST",2006).
birth("PYR","XZF","XBT",2014).
birth("PYR","XZF","XZT",2020).

death("WZY",2001).
death("XYZ",1998).


wife(Husbend,Y):-marry(Y,Husbend,_).
husbend(Wife,Y):-marry(Wife,Y,_).
father(Child,Y):-birth(_,Y,Child,_).
mother(Child,Y):-birth(Y, _, Child, _).
parent(Child,M,F):-mother(Child,M),father(Child,F).
child(Parent,Y):-father(Y, Parent);mother(Y, Parent).
siblings(X,Y):-
    parent(X, M, F),
    parent(Y, M, F),
    X \= Y.
cousins(X, Y):-
    parent(X, Mx, Fx),
    parent(Y, My, Fy),
    (siblings(Mx, My); siblings(Mx, Fy); siblings(Fx, My); siblings(Fx, Fy)),
    X \= Y.
uncle(Child,Y):-
    male(Y),
    (father(Child, F), siblings(F, Y);
      mother(Child, M), siblings(M, Y)).
aunt(Child,Y):-
     female(Y),
     (father(Child, F), siblings(F, Y);
      mother(Child, M), siblings(M, Y)).
grandfather(Grandchild,Y):-
    father(Grandchild, A),father(A,Y);
    mother(Grandchild, A),father(A,Y).
grandmother(Grandchild,Y):-
    father(Grandchild, A),mother(A,Y);
    mother(Grandchild, A),mother(A,Y).
grandparent(Child,Gm,Gf):-grandmother(Child,Gm),grandfather(Child,Gf).
grandchild(Gparent,Y):-grandfather(Y, Gparent);grandmother(Y, Gparent).
married(X) :- marry(X, _, _); marry(_, X, _).
single(X) :- not(married(X)).
alive(X) :-
    birth(_,_,X,_),
    not(death(X,_)).
age(X,CurrentYear,Age):-
    birth(_, _, X, BirthYear),
    alive(X),
    CurrentYear>=BirthYear,
    Age is CurrentYear - BirthYear.
death_age(X,Age):-
    birth(_, _, X, BirthYear),
    death(X,DeathYear),
    not(alive(X)),
    Age is abs(DeathYear-BirthYear).
age_dif(X,Y,Diff):-
    birth(_, _, X, Xb),
    birth(_, _, Y, Yb),
    Diff is abs(Xb-Yb).










