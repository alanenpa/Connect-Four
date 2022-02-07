package Tiralabra;

/**
 * Controls the gameboard
 */
public class Board {
    private final int HEIGHT = 6;
    private final int WIDTH = 29;
    private final int COl_SPACE = 4; // space between the columns
    private final String GRID_ROW = "|   |   |   |   |   |   |   |";
    private char[][] grid;

    /**
     * Initializes the 7x6 gameboard with some extra space
     */
    public Board() {
        this.grid = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            this.grid[i] = GRID_ROW.toCharArray();
        }
    }

    /**
     * Marks the board with current players disc
     * @param columnNum column number, gets converted to a coordinate on the board
     * @param player current players disc: 'X' or 'O'
     */
    public void makeAMove(int columnNum, char player) {
        int columnCoordinate = convert(columnNum);
        for (int i = HEIGHT-1; i >= 0; i--) {
            if (this.grid[i][columnCoordinate] == ' ') {
                this.grid[i][columnCoordinate] = player;
                break;
            }
        }
    }

    /**
     * Checks are there four discs connected on the gameboard
     * @param player 'X' or 'O'
     * @return true if four discs are connected, false if not
     */
    public boolean areFourConnected(int player) {
        int lastColumn = WIDTH - 2;
        // check rows
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 2; j < lastColumn-12; j+=4) {
                if (this.grid[i][j] == player && this.grid[i][j+COl_SPACE] == player && this.grid[i][j+COl_SPACE*2] == player && this.grid[i][j+COl_SPACE*3] == player) {
                    return true;
                }
            }
        }
        // check columns
        for (int i = 0; i < HEIGHT - 3; i++) {
            for (int j = 2; j < WIDTH; j+=4) {
                if (this.grid[i][j] == player && this.grid[i+1][j] == player && this.grid[i+2][j] == player && this.grid[i+3][j] == player) {
                    return true;
                }
            }
        }
        // check ascending diagonals
        for (int i = 3; i < HEIGHT; i++) {
            for (int j = 2; j < 15; j+=4) {
                if (this.grid[i][j] == player && this.grid[i-1][j+COl_SPACE] == player && this.grid[i-2][j+COl_SPACE*2] == player && this.grid[i-3][j+COl_SPACE*3] == player) {
                    return true;
                }
            }
        }
        // check descending diagonals
        for (int i = 0; i < HEIGHT - 3; i++) {
            for (int j = 2; j < 15; j+=4) {
                if (this.grid[i][j] == player && this.grid[i+1][j+COl_SPACE] == player && this.grid[i+2][j+COl_SPACE*2] == player && this.grid[i+3][j+COl_SPACE*3] == player) {
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
            if (inputInt >= 1 && inputInt <= 7) {
                return inputInt;
            }
        }
        return -1;
    }

    /**
     * Converts a column number into a coordinate on the gameboard
     * @param columnNum column number
     * @return coordinate on the gameboard
     */
    public int convert(int columnNum) {
        return switch (columnNum) {
            case 1 -> 2;
            case 2 -> 6;
            case 3 -> 10;
            case 4 -> 14;
            case 5 -> 18;
            case 6 -> 22;
            case 7 -> 26;
            default -> -1;
        };
    }

    public void print() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(this.grid[i][j]);
            }
            System.out.println();
        }
    }

    public char[][] getGrid() {
        return grid;
    }
}
