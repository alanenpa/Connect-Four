
package tiralabra;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static final int PLAYER = 0;
    public static final int AI = 1;

    public static final char PLAYER_PIECE = 'X';
    public static final char AI_PIECE = 'O';


    public static void main(String[] args) throws InterruptedException {
        Board board = new Board();
        Random rand = new Random();
        AI ai = new AI();
        int turn = rand.nextInt(AI+1);
        Scanner scanner = new Scanner(System.in);
        board.print();

        while (true) {
            char piece;
            if (turn == PLAYER) {
                System.out.println("It's X's turn!");
                piece = PLAYER_PIECE;
                System.out.println("Choose a column to play (1-7)");
                String input = scanner.nextLine();
                int inputInt = board.validate(input);
                if (inputInt == -1) break;
                board.makeAMove(inputInt, piece);
                turn++;
                turn = turn % 2;
            } else {
                System.out.println("It's O's turn!");
                piece = AI_PIECE;
                int column = rand.nextInt(7)+1;
                board.makeAMove(column, piece);
                turn++;
                turn = turn % 2;
                Thread.sleep(1500);
            }
            board.print();
            if (board.areFourConnected(piece)) {
                System.out.println(piece + " wins!");
                break;
            }

        }
        System.out.println("Thanks for playing!");
    }
}
