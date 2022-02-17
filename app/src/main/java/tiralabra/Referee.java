package tiralabra;

/**
 * Keeps track of who's turn is it
 */
public class Referee {
    private int turn;

    public Referee() {
        this.turn = 0;
    }

    /**
     * Counts a turn used
     * @return the current players disc
     */
    public char useTurn() {
        char player = whosTurn();
        this.turn++;
        return player;
    }

    /**
     * Checks use turn is it
     * @return the current players disc
     */
    public char whosTurn() {
        if (this.turn % 2 == 1) {
            System.out.println("It's X's turn!");
            return 'X';
        } else {
            System.out.println("It's O's turn!");
            return 'O';
        }
    }
}
