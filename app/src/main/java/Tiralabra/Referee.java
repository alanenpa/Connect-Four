package Tiralabra;

public class Referee {
    private int turn;

    public Referee() {
        this.turn = 0;
    }

    public char getDisc() {
        if (this.turn % 2 == 1) {
            System.out.println("It's X's turn!");
            this.turn++;
            return 'X';
        } else {
            System.out.println("It's O's turn!");
            this.turn++;
            return 'O';
        }
    }
}
