package domain;

import java.util.ArrayList;

public class Board {

    static final int HEIGHT = 6;
    static final int WIDTH = 7;
    static final String GRID_ROW = "       ";

    /**
     * Initializes the 7x6 gameboard.
     */
    public static char[][] initBoard() {
        char[][] board = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            board[i] = GRID_ROW.toCharArray();
        }
        return board;
    }

    public static int getNextOpenRow(int col, char[][] board) {
        for (int i = HEIGHT - 1; i >= 0; i--) {
            if (board[i][col] == ' ') return i;
        }
        return -1;
    }

    /**
     * Marks the board with current players disc.
     *
     * @param col column number
     * @param piece current players piece (or disc): 'X' or 'O'
     */
    public static void dropPiece(int row, int col, char piece, char[][] board) {
        board[row][col] = piece;
    }

    public static void removePiece(int row, int col, char[][] board) {
        board[row][col] = ' ';
    }

    public static boolean isValidLocation(int col, char[][] board) {
        return board[0][col] == ' ';
    }

    public static ArrayList<Integer> getValidLocations(char[][] board) {
        ArrayList<Integer> validLocations = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            if (isValidLocation(i, board)) {
                validLocations.add(i);
            }
        }
        return validLocations;
    }

    public static boolean isAWinningMove(int row, int col, char piece, char[][] board) {
        // check rows
        int counter = 0;
        for (int i = -3; i < 4; i++) {
            if ((col+i) >= 0 && (col+i) < 7) {
                if (board[row][col+i] == piece) {
                    counter++;
                } else {
                    counter = 0;
                }
                if (counter == 4) return true;
            }
        }
        // check columns
        counter = 0;
        for (int i = -3; i < 4; i++) {
            if ((row+i) >= 0 && (row+i) < 6) {
                if (board[row+i][col] == piece) {
                    counter++;
                } else {
                    counter = 0;
                }
                if (counter == 4) return true;
            }
        }
        if (notInCornersAscending(row, col)) {
            // check ascending diagonals
            counter = 0;
            for (int i = -3; i < 4; i++) {
                if (indexNotWithinBoundsAsceding(row, col, i)) {
                    continue;
                } else {
                    if (board[row-i][col+i] == piece) {
                        counter++;
                    } else {
                        counter = 0;
                    }
                    if (counter == 4) return true;
                }
            }
        }
        if (notInCornersDescending(row, col)) {
            // check descending diagonals
            counter = 0;
            for (int i = -3; i < 4; i++) {
                if (indexNotWithinBoundsDesceding(row, col, i)) {
                    continue;
                } else {
                    if (board[row+i][col+i] == piece) {
                        counter++;
                    } else {
                        counter = 0;
                    }
                    if (counter == 4) return true;
                }
            }
        }
        return false;
    }

    public static boolean indexNotWithinBoundsAsceding(int row, int col, int i) {
        return (row-i) > 5 || (row-i) < 0 || (col+i) > 6 || (col+i) < 0;
    }

    public static boolean indexNotWithinBoundsDesceding(int row, int col, int i) {
        return (row+i) > 5 || (row+i) < 0 || (col+i) > 6 || (col+i) < 0;
    }


    public static boolean notInCornersAscending(int row, int col) {
        return notInUpperLeftCorner(row, col) && notInLowerRightCorner(row, col);
    }

    public static boolean notInUpperLeftCorner(int row, int col) {
        return (col != 0 || row >= 3) && (col != 1 || row >= 2) && (col != 2 || row >= 1);
    }

    public static boolean notInLowerRightCorner(int row, int col) {
        return (col != 4 || row <= 4) && (col != 5 || row <= 3) && (col != 6 || row <= 2);
    }

    public static boolean notInCornersDescending(int row, int col) {
        return notInLowerLeftCorner(row, col) && notInUpperRightCorner(row, col);
    }

    public static boolean notInLowerLeftCorner(int row, int col) {
        return (col != 0 || row <= 2) && (col != 1 || row <= 3) && (col != 2 || row <= 4);
    }

    public static boolean notInUpperRightCorner(int row, int col) {
        return (col != 4 || row >= 1) && (col != 5 || row >= 2) && (col != 6 || row >= 3);
    }


}
