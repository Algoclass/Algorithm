Plagiarism Detection- A comparative analasys of different Algorithms
=========
Types of Algorithms used for plagiarism detection:
Substring Matching
Subsequence Matching

Algorithms used:
LCSS
Rabin Karp
KMP
Boyer Moore
Naive

=========
LCSS 

Running Time O(mn)
Subsequence matching
Data Structures used:
2D Array
HashMap

=========
Naive

Running Time O(n^2)
Substring matching
Data Structures used:
Array
ArrayList

=========
KMP

Running Time O(n)
Substring matching
Prefix Function: O(m), m-pattern length
Data Structures used:
ArrayList

=========
Rabin Karp

Running Time: Average and best case running time is O(n+m), worst-case time is O(nm)
Substring matching
Data Structures used:
Array
ArrayList

=========
Boyer moore

Worst-case running time of O(n+m) only if the pattern does not appear in the text.  
When the pattern does occur in the text, running time of the original algorithm is O(nm) in the worst case.
Substring matching
Data Structures used:
Array
ArrayList


