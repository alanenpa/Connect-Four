# User manual

The program can be started by running it from an IDE, from a command line with the command `gradle run` 
or by running the [jar file](https://github.com/alanenpa/Connect-Four/releases/tag/v1.0.0).

## Input

The game asks the user to choose a column to drop a piece in on every turn. Invalid input (anything other than numbers between 1-7) causes
the game to ask for a column number again. The user can quit the game by entering 'q'.

Every played game ends up either in one of the players winning or in a draw. The game is a draw when there are no more space on the gameboard.

## Commands

The game can be started from the command line after cloning the repository with the command `gradle run`. Tests can be run with the command
`gradle test`.