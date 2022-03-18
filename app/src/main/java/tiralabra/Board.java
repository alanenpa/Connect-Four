package tiralabra;

/**
 * Controls the gameboard.
 */
public class Board {
    private static final int HEIGHT = 6;
    private static final int WIDTH = 7;
    private static final String GRID_ROW = "       ";
    private char[][] grid;

    /**
     * Initializes the 7x6 gameboard.
     */
    public Board() {
        this.grid = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            this.grid[i] = GRID_ROW.toCharArray();
        }
    }

    /**
     * Marks the board with current players disc.
     * @param column column number
     * @param piece current players piece (or disc): 'X' or 'O'
     */
    public void makeAMove(int column, char piece) {
        for (int i = HEIGHT-1; i >= 0; i--) {
            if (this.grid[i][column-1] == ' ') {
                this.grid[i][column-1] = piece;
                break;
            }
        }
    }

    /**
     * Checks are there four discs connected on the gameboard.
     * @param piece 'X' or 'O'
     * @return true if four discs are connected, false if not
     */
    public boolean areFourConnected(int piece) {
        // check rows
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH-3; j++) {
                if (this.grid[i][j] == piece && this.grid[i][j+1] == piece && this.grid[i][j+2] == piece && this.grid[i][j+3] == piece) {
                    return true;
                }
            }
        }
        // check columns
        for (int i = 0; i < HEIGHT - 3; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (this.grid[i][j] == piece && this.grid[i+1][j] == piece && this.grid[i+2][j] == piece && this.grid[i+3][j] == piece) {
                    return true;
                }
            }
        }
        // check ascending diagonals
        for (int i = 3; i < HEIGHT; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.grid[i][j] == piece && this.grid[i-1][j+1] == piece && this.grid[i-2][j+2] == piece && this.grid[i-3][j+3] == piece) {
                    return true;
                }
            }
        }
        // check descending diagonals
        for (int i = 0; i < HEIGHT - 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.grid[i][j] == piece && this.grid[i+1][j+1] == piece && this.grid[i+2][j+2] == piece && this.grid[i+3][j+3] == piece) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if input is a valid move. Input must be between 1 and 7 as there are 7 columns.
     * @param input string input of user
     * @return input parsed as integer, if valid. Otherwise returns -1
     */
    public int validate(String input) {
        if (input.chars().allMatch(Character::isDigit) && !input.equals("")) {
            int inputInt = Integer.parseInt(input);
            if (inputInt >= 1 && inputInt <= 7 && this.grid[0][inputInt-1] == ' ') {
                return inputInt;
            }
        }
        return -1;
    }

    public void print() {
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print("| ");
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(this.grid[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public char[][] getGrid() {
        return grid;
    }
}
