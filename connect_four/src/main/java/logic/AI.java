package logic;

import static domain.Board.*;

public class AI {

    static final char PLAYER_PIECE = 'X';
    static final char AI_PIECE = 'O';

    /**
     * The order where the board columns are checked. It is best to start from the middle, because there are the most possibilities for a row of four.
     */
    public static int[] COL_ORDER = {3,4,2,5,1,6,0};

    // col, score
    public static int[] minimax(int depth, char piece, int alpha, int beta, char[][] board) {
        for (int i : COL_ORDER) {
            int nextRow = getNextOpenRow(i, board);
            if (nextRow == -1) continue;
            dropPiece(nextRow, i, piece, board);
            if (isAWinningMove(nextRow, i, PLAYER_PIECE, board) || isAWinningMove(nextRow, i, AI_PIECE, board)) {
                removePiece(nextRow, i, board);
                if (piece == PLAYER_PIECE) {
                    return new int[] {i, 10000000-depth};
                } else {
                    return new int[] {i, -10000000+depth};
                }
            }
            removePiece(nextRow, i, board);
        }
        if (depth == 0 || (getValidLocations(board).size() == 0)) {
            return new int[] {-1, 0};
        }
        int score;
        int bestMove = -1;
        if (piece == PLAYER_PIECE) { // maximizing player
            score = Integer.MIN_VALUE;
            for (int i : COL_ORDER) {
                int nextRow = getNextOpenRow(i, board);
                if (nextRow == -1) continue;
                dropPiece(nextRow, i, PLAYER_PIECE, board);
                int newScore = minimax(depth-1, AI_PIECE, alpha, beta, board)[1];
                removePiece(nextRow, i, board);
                if (newScore > score) {
                    score = newScore;
                    bestMove = i;
                }
                alpha = Math.max(alpha, score);
                if (score >= beta) {
                    break;
                }
            }
        } else { // AI_PIECE, minimizing player
            score = Integer.MAX_VALUE;
            for (int i : COL_ORDER) {
                int nextRow = getNextOpenRow(i, board);
                if (nextRow == -1) continue;
                dropPiece(nextRow, i, AI_PIECE, board);
                int newScore = minimax(depth-1, PLAYER_PIECE, alpha, beta, board)[1];
                removePiece(nextRow, i, board);
                if (newScore < score) {
                    score = newScore;
                    bestMove = i;
                }
                beta = Math.min(alpha, score);
                if (score <= alpha) {
                    break;
                }
            }
        }
        return new int[] {bestMove, score};
    }

}
