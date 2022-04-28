package ui;

public class UI {

    static final int HEIGHT = 6;
    static final int WIDTH = 7;

    static final char PLAYER_PIECE = 'X';
    static final char AI_PIECE = 'O';

    public static void announceTurn(char piece) {
        if (piece == PLAYER_PIECE) {
            System.out.println("It's X's turn!");
        } else {
            System.out.println("It's O's turn!");
        }
        System.out.println("Choose a column to play (1-7)");
    }
}
