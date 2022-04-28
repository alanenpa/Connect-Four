package connect_four;

import domain.Game;

public class Main {
    static final int PLAYER = 0;
    static final int AI = 1;

    static final char PLAYER_PIECE = 'X';
    static final char AI_PIECE = 'O';

    public static void main(String[] args) {
        Game.play();
    }
}
