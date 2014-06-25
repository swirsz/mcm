Second-Best Matrix Chain Multiplication Optimization Calculator
===

mcm.java is based on a simple optimum matrix chain multiplication program available on [Wikipedia](http://en.wikipedia.org/wiki/Matrix_chain_multiplication) extended to calculate worst-case, second-best, and second worst-case values and parenthesizations.

The program works by first using 3 nested for loops to calculate the optimal parenthesization.  To calculate second-best instead of best-case, the program requires 2 more nested for loops and calculates all the possible cases where a single suboptimal decision is made, the second best instead of the best option.

The number of calculations that the program needs to make are minimized by simulating what would be done on paper. For example with 8 matrix dimensions, you have a total of 7 matrices, and since the first row cannot ever possibly have a second best option, you need to only calculate rows 2-7:

8 input values/7 matrix table:
```
row-column
2 2,3,4,5,6
3 3,4,5,6
4 4,5,6
5 5,6
6 6
```

To simulate making the one sub-optimal decision, matrixChainOrder() is called with three parameters: the input array, the row, and the column where the second-best option is taken. All other cases will always use the optimal choice.

The variables secondbest/secondworst are only utilized if the row and column specified matchup with the row and column being analyzed by the program.

The program implements a hash table style duplicate checker to only show a couple of examples of unique values under the "Almost Best Parenthesizations" categories. If these lines are removed the program will show all possible "2nd best" parenthesizations including all the duplicate solutions.

It would be very easy to limit or sort the output of 2nd-best cases displayed by the program to actually show only the real "2nd best", but currently I'm displaying everything because it highlights very convincingly which suboptimal choices affect the total and which ones do not.

Examples
==

Problem 1 Matrix Chain Problem

Consider a matrix chain whose sequence of dimensions is(5, 10, 3, 12, 5, 50, 6, 4).  Determine a worstcase parenthesization for this product.  Also give the total number of scalar multiplications required for this parenthesization.


ANSWER ONE-parenthesization:_____ (1((23)(45)))(67)________

ANSWER TWO-number of multiplications: ______14060____________


Problem 2 Another Matrix Chain Problem

Consider a matrix chain whose sequence of dimensions is(5, 10, 3, 12, 5, 50, 6, 4). Determine a true second best parenthesization for this product.


ANSWER ONE-parenthesization: _____ (12)((34)((56)7) _______

ANSWER TWO-number of multiplications: _____2070___________

_________________________________________
Program output: (5, 10, 3, 12, 5, 50, 6, 4)
_________________________________________
```
Optimal Parenthesization

2052
((12)(((34)(56))7))

Almost Best Parenthesizations

2052
((12)(((34)(56))7))
2070
((12)((34)((56)7)))
2125
(((12)(34))((56)7))

Worst-case Parenthesization

14060
((1((23)(45)))(67))

Almost Worst Parenthesizations

14060
((1((23)(45)))(67))
11000
((1(2(3(45))))(67))
12800
(1((((23)(45))6)7))
13480
(((1((23)(45)))6)7)
```
_________________________________________


Problem 1  Matrix Chain Problem 

Consider  a  matrix  chain  whose  sequence  of  dimensions  is (4, 2, 6, 3, 7, 7, 6, 4, 1).    Determine  a  worst-case  parenthesization  for  this  product.  Also give the total number of scalar multiplications required for this  parenthesization. 
 
ANSWER ONE-parenthesization: ______ ((AB)((((CD)E)F)G))H__________.   
 
ANSWER TWO-number of multiplications: ___________976___________________. 
 
 
Problem 2  Another Matrix Chain Problem   
Consider  a  matrix  chain  whose  sequence  of  dimensions  is (4, 2, 6, 3, 7, 7, 6, 4, 1).  Determine  a  true  second-best  parenthesization  for  this 
product. 
 
ANSWER ONE-parenthesization: ______ A((BC)(D(E(F(GH)))))__________.   
 
ANSWER TWO-number of multiplications: _______186____________.   

_________________________________________
Program output: (4, 2, 6, 3, 7, 7, 6, 4, 1)
_________________________________________
```
Optimal Parenthesization

174
(1(2(3(4(5(6(78)))))))

Almost Best Parenthesizations

174
(1(2(3(4(5(6(78)))))))
304
(1(2(3(4(5((67)8))))))
264
(1((((23)4)5)(6(78))))
215
(1(((23)4)(5(6(78)))))
186
(1((23)(4(5(6(78))))))
208
((1(23))(4(5(6(78)))))

Worst-case Parenthesization

976
(((12)(((34)(56))7))8)

Almost Worst Parenthesizations

976
(((12)(((34)(56))7))8)
912
((12)((((34)(56))7)8))
```
_________________________________________

Same data with debugging information:

(demonstrates the program picking the optimum and then the second optimum solution out of all of the alternatives)
_________________________________________
```
Optimal Parenthesization

48 2nd: 48 row:1 col:1
36 2nd: 36 row:1 col:2
126 2nd: 126 row:1 col:3
147 2nd: 147 row:1 col:4
294 2nd: 294 row:1 col:5
168 2nd: 168 row:1 col:6
24 2nd: 24 row:1 col:7
 (alt:60 row:2 col:2 sep:1)
 (alt:120 row:2 col:2 sep:2)
60 2nd: 120 row:2 col:2
 (alt:210 row:2 col:3 sep:1)
 (alt:78 row:2 col:3 sep:2)
78 2nd: 210 row:2 col:3
 (alt:273 row:2 col:4 sep:1)
 (alt:420 row:2 col:4 sep:2)
273 2nd: 420 row:2 col:4
 (alt:420 row:2 col:5 sep:1)
 (alt:273 row:2 col:5 sep:2)
273 2nd: 420 row:2 col:5
 (alt:364 row:2 col:6 sep:1)
 (alt:462 row:2 col:6 sep:2)
364 2nd: 462 row:2 col:6
 (alt:66 row:2 col:7 sep:1)
 (alt:196 row:2 col:7 sep:2)
66 2nd: 196 row:2 col:7
 (alt:134 row:3 col:3 sep:1)
 (alt:342 row:3 col:3 sep:2)
 (alt:144 row:3 col:3 sep:3)
134 2nd: 144 row:3 col:3
 (alt:357 row:3 col:4 sep:1)
 (alt:225 row:3 col:4 sep:2)
 (alt:176 row:3 col:4 sep:3)
176 2nd: 225 row:3 col:4
 (alt:381 row:3 col:5 sep:1)
 (alt:672 row:3 col:5 sep:2)
 (alt:525 row:3 col:5 sep:3)
381 2nd: 525 row:3 col:5
 (alt:448 row:3 col:6 sep:1)
 (alt:399 row:3 col:6 sep:2)
 (alt:345 row:3 col:6 sep:3)
345 2nd: 399 row:3 col:6
 (alt:115 row:3 col:7 sep:1)
 (alt:360 row:3 col:7 sep:2)
 (alt:392 row:3 col:7 sep:3)
115 2nd: 360 row:3 col:7
 (alt:232 row:4 col:4 sep:1)
 (alt:489 row:4 col:4 sep:2)
 (alt:291 row:4 col:4 sep:3)
 (alt:330 row:4 col:4 sep:4)
232 2nd: 291 row:4 col:4
 (alt:453 row:4 col:5 sep:1)
 (alt:345 row:4 col:5 sep:2)
 (alt:456 row:4 col:5 sep:3)
 (alt:260 row:4 col:5 sep:4)
260 2nd: 345 row:4 col:5
 (alt:417 row:4 col:6 sep:1)
 (alt:658 row:4 col:6 sep:2)
 (alt:609 row:4 col:6 sep:3)
 (alt:525 row:4 col:6 sep:4)
417 2nd: 525 row:4 col:6
 (alt:136 row:4 col:7 sep:1)
 (alt:234 row:4 col:7 sep:2)
 (alt:315 row:4 col:7 sep:3)
 (alt:357 row:4 col:7 sep:4)
136 2nd: 234 row:4 col:7
 (alt:308 row:5 col:5 sep:1)
 (alt:573 row:5 col:5 sep:2)
 (alt:405 row:5 col:5 sep:3)
 (alt:596 row:5 col:5 sep:4)
 (alt:400 row:5 col:5 sep:5)
308 2nd: 400 row:5 col:5
 (alt:465 row:5 col:6 sep:1)
 (alt:405 row:5 col:6 sep:2)
 (alt:498 row:5 col:6 sep:3)
 (alt:400 row:5 col:6 sep:4)
 (alt:308 row:5 col:6 sep:5)
308 2nd: 400 row:5 col:6
 (alt:154 row:5 col:7 sep:1)
 (alt:283 row:5 col:7 sep:2)
 (alt:381 row:5 col:7 sep:3)
 (alt:441 row:5 col:7 sep:4)
 (alt:441 row:5 col:7 sep:5)
154 2nd: 283 row:5 col:7
 (alt:340 row:6 col:6 sep:1)
 (alt:561 row:6 col:6 sep:2)
 (alt:453 row:6 col:6 sep:3)
 (alt:610 row:6 col:6 sep:4)
 (alt:512 row:6 col:6 sep:5)
 (alt:404 row:6 col:6 sep:6)
340 2nd: 404 row:6 col:6
 (alt:166 row:6 col:7 sep:1)
 (alt:178 row:6 col:7 sep:2)
 (alt:207 row:6 col:7 sep:3)
 (alt:256 row:6 col:7 sep:4)
 (alt:296 row:6 col:7 sep:5)
 (alt:316 row:6 col:7 sep:6)
166 2nd: 178 row:6 col:7
 (alt:174 row:7 col:7 sep:1)
 (alt:226 row:7 col:7 sep:2)
 (alt:208 row:7 col:7 sep:3)
 (alt:277 row:7 col:7 sep:4)
 (alt:326 row:7 col:7 sep:5)
 (alt:356 row:7 col:7 sep:6)
 (alt:356 row:7 col:7 sep:7)
174 2nd: 208 row:7 col:7
174
(1(2(3(4(5(6(78)))))))

Almost Best Parenthesizations

174
(1(2(3(4(5(6(78)))))))
304
(1(2(3(4(5((67)8))))))
264
(1((((23)4)5)(6(78))))
215
(1(((23)4)(5(6(78)))))
186
(1((23)(4(5(6(78))))))
208
((1(23))(4(5(6(78)))))

Worst-case Parenthesization

976
(((12)(((34)(56))7))8)

Almost Worst Parenthesizations

976
(((12)(((34)(56))7))8)
912
((12)((((34)(56))7)8))
```
