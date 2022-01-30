
package Tiralabra;

import java.util.Scanner;


public class App {

    public static void main(String[] args) {
        char[][] board = new char[6][29]; // 7x6 gameboard with some space
        String gridRow = "|   |   |   |   |   |   |   |";
        for (int i = 0; i < 6; i++) {
            board[i] = gridRow.toCharArray();
        }

        Scanner scanner = new Scanner(System.in);
        printBoard(board);

        int charIndex = -1;
        int turn = 1;
        char disc;

        while (true) {
            if (turn % 2 == 1) {
                System.out.println("It's X's turn!");
                disc = 'X';
            } else {
                System.out.println("It's O's turn!");
                disc = 'O';
            }

            System.out.println("Choose a column to play (1-7)");
            String input = scanner.nextLine();

            System.out.println(input);
            int inputInt = -1;
            if (input.chars().allMatch( Character::isDigit )) {
                inputInt = Integer.parseInt(input);
                if (inputInt < 1 || inputInt > 7) break;
            } else {
                break;
            }
            switch (inputInt) {
                case 1:
                    charIndex = 2;
                    break;
                case 2:
                    charIndex = 6;
                    break;
                case 3:
                    charIndex = 10;
                    break;
                case 4:
                    charIndex = 14;
                    break;
                case 5:
                    charIndex = 18;
                    break;
                case 6:
                    charIndex = 22;
                    break;
                case 7:
                    charIndex = 26;
                    break;
            }
            for (int i = 5; i >= 0; i--) {
                if (board[i][charIndex] == ' ') {
                    board[i][charIndex] = disc;
                    break;
                }
            }
            printBoard(board);
            turn++;
        }
        System.out.println(charIndex);
        printBoard(board);
        System.out.println("Thanks for playing!");
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 29; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
