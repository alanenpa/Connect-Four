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
        // horizontal coordinates for columns: 2, 6, 10, 14, 18, 22, 26
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
//            assertFalse(areFourConnected(piece, grid));
            assertFalse(isAWinningMove(row, moves[i], piece, board));
        }
        int row = getNextOpenRow(2, board);
        dropPiece(row, 2, 'O', board);
        print(board);
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
}
