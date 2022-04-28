# Week 5

[Hours](https://github.com/alanenpa/Connect-Four/blob/main/Documentation/Reports/Hours.md)

## What I did
- Heavy refactoring, ditched the pickBestMove-method altogether
- Ditched also the scorePosition-method, as in heuristic scoring is not necessary with enough minimax calculating with enough depth
- Created Minimax-method, the method returns not both the value of the best move and the best move (column) itself
- Noticed and fixed a major flaw in isWinningMove-method. The method didn't check the leftmost column \
  in horizontal rows of four

## Problems
Nothing major, implementing minimax properly wasn't easy.

## Next week
- Implement minimax with alpha-beta pruning 
- Divide App class into packages and separate classes
- Write testing and implementation documents
