package tiralabra;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static tiralabra.App.*;

public class BoardTest {
    private char[][] board;

    @Before
    public void init() {
        board = initBoard();
    }

    @Test
    public void movesShouldGoToCorrectPositions() {
        int col = 2;
        int row = getNextOpenRow(col, board);
        dropPiece(row, col, 'O', board);
        assertEquals('O', board[5][2]);

        col = 6;
        row = getNextOpenRow(col, board);
        dropPiece(row, col, 'O', board);
        assertEquals('O', board[5][6]);
    }

    @Test
    public void discsShouldStackUpOnBoard() {
        int col = 4;
        int row = getNextOpenRow(4, board);
        dropPiece(row, col, 'X', board);
        row = getNextOpenRow(4, board);
        dropPiece(row, col, 'O', board);
        row = getNextOpenRow(4, board);
        dropPiece(row, col, 'X', board);
        row = getNextOpenRow(4, board);
        dropPiece(row, col, 'O', board);
        assertEquals('O', board[2][4]);
        assertEquals('X', board[3][4]);
    }

    @Test
    public void validationShouldAcceptOnlyColumnNumbers() {
        int result = validateInput("");
        assertEquals(result, -1);

        result = validateInput("x");
        assertEquals(result, -1);

        result = validateInput("17");
        assertEquals(result, -1);

        result = validateInput("0");
        assertEquals(result, -1);

        result = validateInput("8");
        assertEquals(result, -1);

        result = validateInput("3");
        assertEquals(result, 2);
    }

    @Test
    public void horizontallyConnectedFourwins() {
        int[] moves = {3,3,6,5,0,6,1,5,4};
        int turn = 0;
        char piece = 'O';
        for (int i = 0; i < moves.length; i++) {
            piece = turn == 0 ? 'O' : 'X';
            int row = getNextOpenRow(moves[i], board);
            dropPiece(row, moves[i], piece, board);
            turn++;
            turn = turn % 2;
            assertFalse(isAWinningMove(row, moves[i], piece, board));
        }
        int row = getNextOpenRow(4, board);
        dropPiece(row,4, 'X', board);
        assertTrue(isAWinningMove(row, 4, 'X', board));
        // Final situation
        // |   |   |   |   |   |   |   |
        // |   |   |   |   |   |   |   |
        // |   |   |   |   |   |   |   |
        // |   |   |   |   |   |   |   |
        // |   |   |   | X | X | X | X |
        // | O | O |   | O | O | X | O |
    }

    @Test
    public void verticallyConnectedFourwins() {
        int[] moves = {2,3,2,5,2,6};
        int turn = 0;
        char piece = 'O';
        for (int i = 0; i < moves.length; i++) {
            piece = turn == 0 ? 'O' : 'X';
            int row = getNextOpenRow(moves[i], board);
            dropPiece(row, moves[i], piece, board);
            turn++;
            turn = turn % 2;
            assertFalse(isAWinningMove(row, moves[i], piece, board));
        }
        int row = getNextOpenRow(2, board);
        dropPiece(row, 2, 'O', board);
        assertTrue(isAWinningMove(row, 2, 'O', board));
        // Final situation
        // |   |   |   |   |   |   |   |
        // |   |   |   |   |   |   |   |
        // |   |   | O |   |   |   |   |
        // |   |   | O |   |   |   |   |
        // |   |   | O |   |   |   |   |
        // |   |   | O | X |   | X | X |
    }

    @Test
    public void DiagonallyAscendingConnectedFourwins() {
        int[] moves = {2,2,3,0,3,3,4,4,4,4,6,5,5,5,0,5,6};
        int turn = 0;
        char piece = 'O';
        for (int i = 0; i < moves.length; i++) {
            piece = turn == 0 ? 'O' : 'X';
            int row = getNextOpenRow(moves[i], board);
            dropPiece(row, moves[i], piece, board);
            turn++;
            turn = turn % 2;
            assertFalse(isAWinningMove(row, moves[i], piece, board));
        }
        int row = getNextOpenRow(5, board);
        dropPiece(row, 5, 'X', board);
        assertTrue(isAWinningMove(row, 5, 'X', board));
        // Final situation
        // |   |   |   |   |   |   |   |
        // |   |   |   |   |   | X |   |
        // |   |   |   |   | X | X |   |
        // |   |   |   | X | O | X |   |
        // | O |   | X | O | X | O | O |
        // | X |   | O | O | O | X | O |
    }

    @Test
    public void DiagonallyDescendingConnectedFourwins() {
        int[] moves = {0,0,0,0,4,3,2,2,5,1,1};
        int turn = 0;
        char piece = 'O';
        for (int i = 0; i < moves.length; i++) {
            piece = turn == 0 ? 'O' : 'X';
            int row = getNextOpenRow(moves[i], board);
            dropPiece(row, moves[i], piece, board);
            turn++;
            turn = turn % 2;
            assertFalse(isAWinningMove(row, moves[i], piece, board));
        }
        int row = getNextOpenRow(1, board);
        dropPiece(row, 1, 'X', board);
        assertTrue(isAWinningMove(row, 1, 'X', board));
        // Final situation
        // |   |   |   |   |   |   |   |
        // |   |   |   |   |   |   |   |
        // | X |   |   |   |   |   |   |
        // | O | X |   |   |   |   |   |
        // | X | O | X |   |   |   |   |
        // | O | X | O | X | O | O |   |
    }

    @Test
    public void DiagonallyAscedingUpperCornerCase() {
        int[] moves = {0,0,0,1,1,1,1,2,2,2,2,3,2,3,3,3,3,4,3};
        play(moves);
        print(board);
        assertTrue(isAWinningMove(0, 3, 'O', board));
        // Final situation
        // |   |   |   | O |   |   |   |
        // |   |   | O | O |   |   |   |
        // |   | O | O | X |   |   |   |
        // | O | X | X | O |   |   |   |
        // | X | O | O | X |   |   |   |
        // | O | X | X | X | X |   |   |
    }

    @Test
    public void DiagonallyAscedingLowerCornerCase() {
        int[] moves = {3,4,4,6,5,5,5,2,6,6,6};
        play(moves);
        assertTrue(isAWinningMove(2, 6, 'O', board));
        // Final situation
        // |   |   |   |   |   |   |   |
        // |   |   |   |   |   |   |   |
        // |   |   |   |   |   |   | O |
        // |   |   |   |   |   | O | X |
        // |   |   |   |   | O | X | O |
        // |   |   | X | O | X | O | X |

    }

    @Test
    public void DiagonallyDescendingUpperCornerCase() {
        int[] moves = {3,4,3,3,3,3,3,4,4,4,4,5,5,5,5,0,6,6,6};
        play(moves);
        assertTrue(isAWinningMove(3, 6, 'O', board));
        // Final situation
        // |   |   |   | O |   |   |   |
        // |   |   |   | X | O |   |   |
        // |   |   |   | O | X | O |   |
        // |   |   |   | X | O | X | O |
        // |   |   |   | O | X | O | X |
        // | X |   |   | O | X | X | O |
    }



    private void play(int[] moves) {
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
