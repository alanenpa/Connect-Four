# Implementation document

## Project structure

The game is played on a two-dimensional (7x6) char array. The array stores the current game with 
'X' (player) and 'O' (AI) pieces. The game runs in a while-loop where the player and AI takes turns.

### AI

AI calculates it's moves with minimax. This is done by looking a certain amount of moves ahead and finding out which moves will lead to the quickest win. The algorithm returns the value and column index of the best move
possible move at given depth. The main game loop has only use for the column index of the best move but the returned value is needed
in recursion in order to minimax compare moves and find the best one. Alpha-beta pruning improves the efficiency of the algorithm as it prevents calculating
positions (or "branches") which will not be played because a better option has already been found.

The calculation works as follows:
- Win checking is executed on every turn. That is, the method checks if a row of four could be achieved on the current turn. 
  If yes, it is the best move and thus it gets the biggest score. The quicker a winning game is found the bigger value it gets. Thus, the algorithm
  favors wins with fewer moves.
- If a winning move is not found the algorithm moves on to call itself on game board where a hypothetical piece is placed on a
  column. After returning from the call, the piece is removed. The call is made to every column. With every recursive call the depth parameter is subtracted by one.
- When zero depth is reached a value will be given to the game situation on the board. This is the base case for the recursion. First, win check is executed (like on every else level of depth). If no winning move isn't found,
  the board gets a value of 0. The board also gets valued zero if the game results in a draw. This happens when the board gets full and there are no more possible moves. The value of the board is then returned.

### UI

The game has a basic command-line interface for UI. The game outputs the board after every move, announces turns and wins.

### Possible flaws and improvements

The minimax algorithm could possibly be refactored to get rid of some duplicated code by using -1 and 1 instead of chars to identify the players.
Testing of the algorithm was not implemented.

### Sources
- [Tirakirja](https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/) by Antti Laaksonen
- [Minimax](https://en.wikipedia.org/wiki/Minimax)
- [Alpha-beta pruning](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning)
- [How does a Board Game AI Work?](https://www.youtube.com/watch?v=y7AKtWGOPAE), a video
- [How to Program a Connect 4 AI](https://www.youtube.com/watch?v=MMLtza3CZFM), some hints from here as well
- 