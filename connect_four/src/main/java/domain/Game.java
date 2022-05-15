package domain;

import java.util.Scanner;

import static domain.Board.*;
import static logic.AI.minimax;
import static utils.Utils.print;
import static utils.Utils.validateInput;

public class Game {

    static final char PLAYER_PIECE = 'X';
    static final char AI_PIECE = 'O';

    /**
     * The method in which the game is played. Players take turns in a while-loop.
     * The game ends if the results in a win, draw or the player quits with the input 'q'.
     */
    public static void play() {
        char[][] board = initBoard();
        Scanner scanner = new Scanner(System.in);
        char piece = Math.random() < 0.5 ? 'X' : 'O';
        int row;
        int column;
        int totalMoves = 0;
        boolean gameOver = false;

        while (!gameOver) {
            print(board);
            announceTurn(piece);
            column = pickColumn(board, scanner, piece);
            if (column == -1) {
                System.out.println("Invalid input!");
            } else if (column == -2) {
                // quitting game with 'q'
                gameOver = true;
            } else {
                if (isValidLocation(column, board)) {
                    row = useTurn(board, piece, column);
                    totalMoves++;
                    if (isAWinningMove(row, column, piece, board)) {
                        System.out.println(piece + " wins!");
                        gameOver = true;
                    } else if (totalMoves == 42) {
                        System.out.println("It's a draw!");
                        gameOver = true;
                    } else {
                        piece = piece == PLAYER_PIECE ? AI_PIECE : PLAYER_PIECE;
                    }
                } else {
                    System.out.println("The column " + (column+1) + " is full!");
                }
            }
        }

        print(board);
        System.out.println("Thanks for playing!");
    }

    /**
     * Drops current player's piece in the next open row in the chosen column.
     * Row coordinate is returned for the win check method.
     * @param board board which is used for the game
     * @param piece 'X' or 'O'
     * @param column column coordinate
     * @return row coordinate
     */
    private static int useTurn(char[][] board, char piece, int column) {
        int row;
        row = getNextOpenRow(column, board);
        dropPiece(row, column, piece, board);
        return row;
    }

    /**
     * Determines the column in which the next move is played. If the current player is the user, the column is read from keyboard input.
     * If the current player is the AI, minimax algorithm is used to determine the best move available, given the depth.
     * @param board board which is used for the game
     * @param scanner used to read keyboard input
     * @param piece 'X' or 'O'
     * @return the chosen column for next move
     */
    private static int pickColumn(char[][] board, Scanner scanner, char piece) {
        int column;
        if (piece == PLAYER_PIECE) {
            String input = scanner.nextLine();
            column = validateInput(input);
        } else {
            int[] arr = minimax(10, piece, Integer.MIN_VALUE, Integer.MAX_VALUE, board);
            column = arr[0];
        }
        return column;
    }

    public static void announceTurn(char piece) {
        if (piece == PLAYER_PIECE) {
            System.out.println("It's X's turn!");
            System.out.println("Choose a column to play (1-7)");
            System.out.println("or quit the game with q");
        } else {
            System.out.println("It's O's turn!");
        }
    }
}
