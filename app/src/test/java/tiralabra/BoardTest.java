package tiralabra;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    private Board board;
    private Referee referee;
    private char[][] grid;

    @Before
    public void init() {
        board = new Board();
        referee = new Referee();
        grid = board.getGrid();
        // horizontal coordinates for columns: 2, 6, 10, 14, 18, 22, 26
    }

    @Test
    public void movesShouldGoToCorrectPositions() {
        board.makeAMove(3, 'O');
        assertEquals('O', grid[5][10]);

        board.makeAMove(7, 'O');
        assertEquals('O', grid[5][26]);
    }

    @Test
    public void discsShouldStackUpOnBoard() {
        char player = referee.useTurn();
        board.makeAMove(4, player);

        player = referee.useTurn();
        board.makeAMove(4, player);

        player = referee.useTurn();
        board.makeAMove(4, player);

        player = referee.useTurn();
        board.makeAMove(4, player);
        assertEquals(player, grid[2][14]);

        player = referee.useTurn();
        assertEquals(player, grid[3][14]);
    }

    @Test
    public void validationShouldAcceptOnlyColumnNumbers() {
        int result = board.validate("");
        assertEquals(result, -1);

        result = board.validate("x");
        assertEquals(result, -1);

        result = board.validate("17");
        assertEquals(result, -1);

        result = board.validate("0");
        assertEquals(result, -1);

        result = board.validate("8");
        assertEquals(result, -1);

        result = board.validate("3");
        assertEquals(result, 3);
    }

    @Test
    public void convertWorks() {
        // horizontal coordinates for columns: 2, 6, 10, 14, 18, 22, 26
        int colCoordinate = board.convert(5);
        assertEquals(colCoordinate, 18);

        colCoordinate = board.convert(7);
        assertEquals(colCoordinate, 26);

        colCoordinate = board.convert(1);
        assertEquals(colCoordinate, 2);
    }

    @Test
    public void horizontallyConnectedFourwins() {
        int[] moves = {4,4,7,6,1,7,2,6,5};
        char player;
        for (int i = 0; i < moves.length; i++) {
            player = referee.useTurn();
            board.makeAMove(moves[i], player);
            assertFalse(board.areFourConnected(player));
        }
        player = referee.useTurn();
        board.makeAMove(5, player);
        assertTrue(board.areFourConnected(player));
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
        int[] moves = {3,4,3,6,3,7};
        char player;
        for (int i = 0; i < moves.length; i++) {
            player = referee.useTurn();
            board.makeAMove(moves[i], player);
            assertFalse(board.areFourConnected(player));
        }
        player = referee.useTurn();
        board.makeAMove(3, player);
        assertTrue(board.areFourConnected(player));
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
        int[] moves = {3,3,4,1,4,4,5,5,5,5,7,6,6,6,1,6,7};
        char player;
        for (int i = 0; i < moves.length; i++) {
            player = referee.useTurn();
            board.makeAMove(moves[i], player);
            assertFalse(board.areFourConnected(player));
        }
        player = referee.useTurn();
        board.makeAMove(6, player);
        assertTrue(board.areFourConnected(player));
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
        int[] moves = {1,1,1,1,5,4,3,3,6,2,2};
        char player;
        for (int i = 0; i < moves.length; i++) {
            player = referee.useTurn();
            board.makeAMove(moves[i], player);
            assertFalse(board.areFourConnected(player));
        }
        player = referee.useTurn();
        board.makeAMove(2, player);
        board.print();
        assertTrue(board.areFourConnected(player));
        // Final situation
        // |   |   |   |   |   |   |   |
        // |   |   |   |   |   |   |   |
        // | X |   |   |   |   |   |   |
        // | O | X |   |   |   |   |   |
        // | X | O | X |   |   |   |   |
        // | O | X | O | X | O | O |   |
    }
}
