
package tiralabra;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static logic.AI.minimax;
import static utils.Utils.*;
import static domain.Board.*;

public class App {
    static final int PLAYER = 0;
    static final int AI = 1;

    static final char PLAYER_PIECE = 'X';
    static final char AI_PIECE = 'O';

    public static void main(String[] args) {
        char[][] board = initBoard();
        Random rand = new Random();
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
                    if (isAWinningMove(row, inputCol, piece, board)) gameOver = true;
                }
                turn++;
                turn = turn % 2;
            } else {
                System.out.println("It's O's turn!");
                piece = AI_PIECE;
                int[] arr = minimax(15, piece, Integer.MIN_VALUE, Integer.MAX_VALUE,  board);
                int column = arr[0];
                int row = getNextOpenRow(column, board);
                dropPiece(row, column, piece, board);
                if (isAWinningMove(row, column, piece, board)) gameOver = true;
                turn++;
                turn = turn % 2;
            }
            print(board);
            if (gameOver) {
                System.out.println(piece + " wins!");
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }
}

