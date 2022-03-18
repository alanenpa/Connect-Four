
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
            if (turn == PLAYER) {
                System.out.println("It's X's turn!");
                piece = PLAYER_PIECE;
                System.out.println("Choose a column to play (1-7)");
                String input = scanner.nextLine();
                int inputInt = validateInput(input);
                if (inputInt == -1) break;
                makeAMove(inputInt, piece, board);
                turn++;
                turn = turn % 2;
            } else {
                System.out.println("It's O's turn!");
                piece = AI_PIECE;
                int column = rand.nextInt(7) + 1;
                makeAMove(column, piece, board);
                turn++;
                turn = turn % 2;
                Thread.sleep(1500);
            }
            print(board);
            if (areFourConnected(piece, board)) {
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
                return inputInt;
            }
        }
        return -1;
    }

    /**
     * Marks the board with current players disc.
     *
     * @param col   column number
     * @param piece current players piece (or disc): 'X' or 'O'
     * @return true if a move was made
     */
    public static boolean makeAMove(int col, char piece, char[][] board) {
        for (int i = HEIGHT - 1; i >= 0; i--) {
            if (board[i][col - 1] == ' ') {
                board[i][col - 1] = piece;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks are there four discs connected on the gameboard.
     *
     * @param piece 'X' or 'O'
     * @return true if four discs are connected, false if not
     */
    public static boolean areFourConnected(int piece, char[][] board) {
        // check rows
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH - 3; j++) {
                if (board[i][j] == piece && board[i][j + 1] == piece && board[i][j + 2] == piece && board[i][j + 3] == piece) {
                    return true;
                }
            }
        }
        // check columns
        for (int i = 0; i < HEIGHT - 3; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j] == piece && board[i + 1][j] == piece && board[i + 2][j] == piece && board[i + 3][j] == piece) {
                    return true;
                }
            }
        }
        // check ascending diagonals
        for (int i = 3; i < HEIGHT; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == piece && board[i - 1][j + 1] == piece && board[i - 2][j + 2] == piece && board[i - 3][j + 3] == piece) {
                    return true;
                }
            }
        }
        // check descending diagonals
        for (int i = 0; i < HEIGHT - 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == piece && board[i + 1][j + 1] == piece && board[i + 2][j + 2] == piece && board[i + 3][j + 3] == piece) {
                    return true;
                }
            }
        }
        return false;
    }
}
