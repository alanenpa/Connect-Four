
package Tiralabra;

import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Board board = new Board();
        Referee referee = new Referee();
        Scanner scanner = new Scanner(System.in);
        board.print();

        while (true) {
            char disc = referee.getDisc();

            System.out.println("Choose a column to play (1-7)");
            String input = scanner.nextLine();

            int inputInt = board.parseMove(input);
            if (inputInt == -1) break;
            board.makeAMove(inputInt, disc);
            board.print();
        }
        System.out.println("Thanks for playing!");
    }

}
