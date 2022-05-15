package logic;

import org.junit.Before;
import org.junit.Test;

import static domain.Board.*;
import static test_utils.UtilsForTests.makeMoves;
import static utils.Utils.print;
import static logic.AI.*;

public class AITest {
    private char[][] board;

    @Before
    public void setUp() {
        board = initBoard();
    }

    @Test
    public void winAtDepthOne() {
        int[] moves = {3,3,6,0,5,0};
        makeMoves(moves, board);
        int[] arr = minimax(2, 'X', Integer.MIN_VALUE, Integer.MAX_VALUE, board);
        System.out.println(arr[0] + ", " + arr[1]);
        print(board);
    }

}
