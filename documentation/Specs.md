# Project specification

The aim of this project is to implement minimax algorithm using Java.
The user plays against an AI who calculates it's next move with the algorithm.

### Algorithm time and space complexity

The time complexity of minimax is O(b^m) where b is the branching factor of the tree and m is the maximum depth of the tree.
The space complexity of minimax is O(bm). The algorithm can be made faster at the cost of accuracy with an arbitrarily chosen tree depth
in which the algorithm operates. This is called N-move look ahead.

With alpha-beta pruning the algorithm can be made significantly faster. A-B pruning leaves some tree branches out of the search because
the best move decision cannot be improved by searching through those branches. At best, each node will examine 2b-1 grandchildren. At worst,
each node will examine b^2 grandchildren.



### Input and output

The project uses a command line interface. On his turn, the player chooses the column where his next disc "drops".

### Study programme
Computer science (BSc)

### Sources
- https://en.wikipedia.org/wiki/Minimax
- https://www.youtube.com/watch?v=l-hh51ncgDI
