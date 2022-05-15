package test_utils;

import static domain.Board.dropPiece;
import static domain.Board.getNextOpenRow;

public class UtilsForTests {
    public static void makeMoves(int[] moves, char[][] board) {
        int turn = 0;
        char piece;
        for (int move : moves) {
            piece = turn == 0 ? 'O' : 'X';
            int row = getNextOpenRow(move, board);
            dropPiece(row, move, piece, board);
            turn++;
            turn = turn % 2;
        }
    }
}
