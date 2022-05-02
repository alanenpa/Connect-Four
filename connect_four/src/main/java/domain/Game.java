package domain;

import java.util.Scanner;

import static domain.Board.*;
import static logic.AI.minimax;
import static utils.Utils.print;
import static utils.Utils.validateInput;

public class Game {

    static final char PLAYER_PIECE = 'X';
    static final char AI_PIECE = 'O';

    public static void play() {
        char[][] board = initBoard();
        Scanner scanner = new Scanner(System.in);
        char piece = Math.random() < 0.5 ? 'X' : 'O';
        int row;
        int column;
        boolean gameOver = false;
        System.out.println("quit the game with q");
        print(board);

        while (!gameOver) {
            announceTurn(piece);
            column = pickColumn(board, scanner, piece);
            if (column == -1) {
                System.out.println("Invalid input!");
                print(board);
                continue;
            } else if (column == -2) {
                // quitting game with 'q'
                gameOver = true;
                continue;
            }
            if (isValidLocation(column, board)) {
                row = useTurn(board, piece, column);
                if (isAWinningMove(row, column, piece, board)) {
                    System.out.println(piece + " wins!");
                    gameOver = true;
                }
            } else {
                continue;
            }
            piece = piece == PLAYER_PIECE ? AI_PIECE : PLAYER_PIECE;
            if (getValidLocations(board).size() == 0) {
                System.out.println("It's a draw");
            }
        }
        System.out.println("Thanks for playing!");
    }

    private static int useTurn(char[][] board, char piece, int column) {
        int row;
        row = getNextOpenRow(column, board);
        dropPiece(row, column, piece, board);
        print(board);
        return row;
    }

    private static int pickColumn(char[][] board, Scanner scanner, char piece) {
        int column;
        if (piece == PLAYER_PIECE) {
            String input = scanner.nextLine();
            column = validateInput(input);
        } else {
            int[] arr = minimax(8, piece, Integer.MIN_VALUE, Integer.MAX_VALUE, board);
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
