
package tiralabra;

import java.util.Random;
import java.util.Scanner;

public class App {
    static final int PLAYER = 0;
    static final int AI = 1;

    static final char PLAYER_PIECE = 'X';
    static final char AI_PIECE = 'O';

    static final int HEIGHT = 6;
    static final int WIDTH = 7;
    static final String GRID_ROW = "       ";


    public static void main(String[] args) throws InterruptedException {
        char[][] board = initBoard();
        Random rand = new Random();
        AI ai = new AI();
        int turn = rand.nextInt(AI + 1);
        Scanner scanner = new Scanner(System.in);
        print(board);

        while (true) {
            char piece;
            boolean gameOver = false;
            if (turn == PLAYER) {
                System.out.println("It's X's turn!");
                piece = PLAYER_PIECE;
                System.out.println("Choose a column to play (1-7)");
                String input = scanner.nextLine();
                int inputCol = validateInput(input);
                if (inputCol == -1) break;
                if (isValidLocation(inputCol, board)) {
                    int row = getNextOpenRow(inputCol, board);
                    dropPiece(row, inputCol, piece, board);
                    if (isAWinningMove(row, inputCol, board)) gameOver = true;
                }
                turn++;
                turn = turn % 2;
            } else {
                System.out.println("It's O's turn!");
                piece = AI_PIECE;
                int column = rand.nextInt(7);
                if (isValidLocation(column, board)) {
                    int row = getNextOpenRow(column, board);
                    dropPiece(row, column, piece, board);
                    if (isAWinningMove(row, column, board)) gameOver = true;
                }
                turn++;
                turn = turn % 2;
                Thread.sleep(1500);
            }
            print(board);
            if (gameOver) {
                System.out.println(piece + " wins!");
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }

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

    public static void print(char[][] board) {
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print("| ");
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static int validateInput(String input) {
        if (input.chars().allMatch(Character::isDigit) && !input.equals("")) {
            int inputInt = Integer.parseInt(input);
            if (inputInt >= 1 && inputInt <= 7) {
                return inputInt-1;
            }
        }
        return -1;
    }

    public static boolean isValidLocation(int col, char[][] board) {
        return board[0][col] == ' ';
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

//    /**
//     * Checks are there four discs connected on the gameboard.
//     *
//     * @param piece 'X' or 'O'
//     * @return true if four discs are connected, false if not
//     */
//    public static boolean areFourConnected(int piece, char[][] board) {
//        // check rows
//        for (int i = 0; i < HEIGHT; i++) {
//            for (int j = 0; j < WIDTH - 3; j++) {
//                if (board[i][j] == piece && board[i][j+1] == piece && board[i][j+2] == piece && board[i][j+3] == piece) {
//                    return true;
//                }
//            }
//        }
//        // check columns
//        for (int i = 0; i < HEIGHT - 3; i++) {
//            for (int j = 0; j < WIDTH; j++) {
//                if (board[i][j] == piece && board[i+1][j] == piece && board[i+2][j] == piece && board[i+3][j] == piece) {
//                    return true;
//                }
//            }
//        }
//        // check ascending diagonals
//        for (int i = 3; i < HEIGHT; i++) {
//            for (int j = 0; j < 4; j++) {
//                if (board[i][j] == piece && board[i-1][j+1] == piece && board[i-2][j+2] == piece && board[i-3][j+3] == piece) {
//                    return true;
//                }
//            }
//        }
//        // check descending diagonals
//        for (int i = 0; i < HEIGHT - 3; i++) {
//            for (int j = 0; j < 4; j++) {
//                if (board[i][j] == piece && board[i+1][j+1] == piece && board[i+2][j+2] == piece && board[i+3][j+3] == piece) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    public static boolean isAWinningMove(int row, int col, char[][] board) {
        int counter = 0;
        char piece = board[row][col];

        // check rows
        for (int i = -4; i < 4; i++) {
//            System.out.println((row) + ", " + (col+i));
            if ((col+i) > 0 && (col+i) < 7 ) {
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
        // refactor this
        for (int i = 0; i < HEIGHT; i++) {
            if (board[i][col] == piece) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == 4) return true;
        }

        if (!(row < 3 && col < 3) || !(row > 2 && col > 3)) {
            counter = 0;
            // check ascending diagonals
            for (int i = -4; i < 4; i++) {
                if ((row-i) > 5 || (row-i) < 0 || (col+i) > 6 || (col+i) < 0) {
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


        if (!(row > 2 && col < 3) || !(row < 3 && col > 3)) {
            // check descending diagonals
            counter = 0;
            for (int i = -4; i < 4; i++) {
                if ((row+i) > 5 || (row+i) < 0 || (col+i) > 6 || (col+i) < 0) {
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

    public int scorePosition(int row, int col, char piece, char[][] board) {
        if (board[row][col] == piece && board[row][col+1] == piece && board[row][col+2] == piece) {
            return 100;
        }
        return 0;
    }

}
