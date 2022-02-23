
package tiralabra;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static final int PLAYER = 0;
    public static final int AI = 1;

    public static void main(String[] args) {
        Board board = new Board();
        Random rand = new Random();
        AI ai = new AI();
        int turn = 0;
        Scanner scanner = new Scanner(System.in);
        board.print();

        while (true) {
            char player;
            if (turn == PLAYER) {
                System.out.println("It's X's turn!");
                player = 'X';
                System.out.println("Choose a column to play (1-7)");
                String input = scanner.nextLine();
                int inputInt = board.validate(input);
                if (inputInt == -1) break;
                board.makeAMove(inputInt, player);
            } else {
                System.out.println("It's O's turn!");
                player = 'O';
                int column = rand.nextInt(7)+1;
                board.makeAMove(column, player);
            }
            board.print();
            if (board.areFourConnected(player)) {
                System.out.println(player + " wins!");
                break;
            }
            turn++;
            turn = turn % 2;
        }
        System.out.println("Thanks for playing!");
    }
}
