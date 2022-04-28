package domain;

import java.util.Random;
import java.util.Scanner;

import static domain.Board.*;
import static logic.AI.minimax;
import static utils.Utils.print;
import static utils.Utils.validateInput;

public class Game {

    static final int PLAYER = 0;
    static final int AI = 1;

    static final char PLAYER_PIECE = 'X';
    static final char AI_PIECE = 'O';

    public static void play() {
        char[][] board = initBoard();
        Random rand = new Random();
        int turn = rand.nextInt(AI + 1);
        Scanner scanner = new Scanner(System.in);
        print(board);
        char piece = '.';
        boolean gameOver = false;

        while (!gameOver) {
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
                    if (isAWinningMove(row, inputCol, piece, board)) gameOver = true;
                }
                turn++;
                turn = turn % 2;
            } else {
                System.out.println("It's O's turn!");
                piece = AI_PIECE;
                int[] arr = minimax(5, piece, Integer.MIN_VALUE, Integer.MAX_VALUE,  board);
                int column = arr[0];
                int row = getNextOpenRow(column, board);
                dropPiece(row, column, piece, board);
                if (isAWinningMove(row, column, piece, board)) gameOver = true;
                turn++;
                turn = turn % 2;
            }
            print(board);
        }
        if (getValidLocations(board).size() == 0) {
            System.out.println("It's a draw");
        } else {
            System.out.println(piece + " wins!");
        }
        System.out.println("Thanks for playing!");
    }
}
