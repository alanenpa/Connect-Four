
package tiralabra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {
    static final int PLAYER = 0;
    static final int AI = 1;

    static final char PLAYER_PIECE = 'X';
    static final char AI_PIECE = 'O';

    static final int HEIGHT = 6;
    static final int WIDTH = 7;
    static final String GRID_ROW = "       ";


    public static void main(String[] args) {
        char[][] board = initBoard();
        Random rand = new Random();
        int turn = rand.nextInt(AI + 1);
        Scanner scanner = new Scanner(System.in);
        print(board);

        while (true) {
            char piece;
            boolean gameOver = false;
            if (turn == PLAYER) {
                System.out.println("It's X's turn!");
                piece = PLAYER_PIECE;
                System.out.println("Choose a column to play (1-7)");
                String input = scanner.nextLine();
                int inputCol = validateInput(input);
                if (inputCol == -1) break;
                if (isValidLocation(inputCol, board)) {
                    int row = getNextOpenRow(inputCol, board);
                    dropPiece(row, inputCol, piece, board);
                    if (isAWinningMove(row, inputCol, piece, board)) gameOver = true;
                }
                turn++;
                turn = turn % 2;
            } else {
                System.out.println("It's O's turn!");
                piece = AI_PIECE;
                int[] arr = minimax(5, piece, board);
                int column = arr[0];
                int row = getNextOpenRow(column, board);
                dropPiece(row, column, piece, board);
                if (isAWinningMove(row, column, piece, board)) gameOver = true;
                turn++;
                turn = turn % 2;
            }
            print(board);
            if (gameOver) {
                System.out.println(piece + " wins!");
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }


//    public static int pickBestMove(char[][] board, char piece, int column) {
//        int max = 0;
//        char[][] boardCopy;
//        ArrayList<Integer> validLocations = getValidLocations(board);
//        System.out.println("Scores:");
//        for (int col : validLocations) {
//            boardCopy = deepCopy(board);
//            int score = dropAndGetScore(boardCopy, piece, col);
//            System.out.println(score + ", " + col);
//            if (score > max) {
//                max = score;
//                System.out.println("improved!");
//                column = col;
//            }
//        }
//        System.out.println("BEST MOVE: " + column);
//        return column;
//    }

//    private static int dropAndGetScore(char[][] boardCopy, char piece, int col) {
//        int row = getNextOpenRow(col, boardCopy);
//        dropPiece(row, col, piece, boardCopy);
////        int score = scorePosition(row, col, piece, boardCopy);
//        int score = minimax(row, col, 2, piece, boardCopy);
//        return score;
//    }

    /**
     * Initializes the 7x6 gameboard.
     */
    public static char[][] initBoard() {
        char[][] board = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            board[i] = GRID_ROW.toCharArray();
        }
        return board;
    }

    public static void print(char[][] board) {
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print("| ");
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static int validateInput(String input) {
        if (input.chars().allMatch(Character::isDigit) && !input.equals("")) {
            int inputInt = Integer.parseInt(input);
            if (inputInt >= 1 && inputInt <= 7) {
                return inputInt-1;
            }
        }
        return -1;
    }

    public static boolean isValidLocation(int col, char[][] board) {
        return board[0][col] == ' ';
    }

    public static ArrayList<Integer> getValidLocations(char[][] board) {
        ArrayList<Integer> validLocations = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            if (isValidLocation(i, board)) {
                validLocations.add(i);
            }
        }
        return validLocations;
    }

    public static int getNextOpenRow(int col, char[][] board) {
        for (int i = HEIGHT - 1; i >= 0; i--) {
            if (board[i][col] == ' ') return i;
        }
        return -1;
    }

    /**
     * Marks the board with current players disc.
     *
     * @param col column number
     * @param piece current players piece (or disc): 'X' or 'O'
     */
    public static void dropPiece(int row, int col, char piece, char[][] board) {
        board[row][col] = piece;
    }

    public static void removePiece(int row, int col, char[][] board) {
        board[row][col] = ' ';
    }

    public static boolean isAWinningMove(int row, int col, char piece, char[][] board) {
        // check rows
        int counter = 0;
        for (int i = -3; i < 4; i++) {
            if ((col+i) >= 0 && (col+i) < 7) {
                if (board[row][col+i] == piece) {
                    counter++;
                } else {
                    counter = 0;
                }
                if (counter == 4) return true;
            }
        }
        // check columns
        counter = 0;
        for (int i = -3; i < 4; i++) {
            if ((row+i) >= 0 && (row+i) < 6) {
                if (board[row+i][col] == piece) {
                    counter++;
                } else {
                    counter = 0;
                }
                if (counter == 4) return true;
            }
        }
        if (notInCornersAscending(row, col)) {
            // check ascending diagonals
            counter = 0;
            for (int i = -3; i < 4; i++) {
                if (indexNotWithinBoundsAsceding(row, col, i)) {
                    continue;
                } else {
                    if (board[row-i][col+i] == piece) {
                        counter++;
                    } else {
                        counter = 0;
                    }
                    if (counter == 4) return true;
                }
            }
        }
        if (notInCornersDescending(row, col)) {
            // check descending diagonals
            counter = 0;
            for (int i = -3; i < 4; i++) {
                if (indexNotWithinBoundsDesceding(row, col, i)) {
                    continue;
                } else {
                    if (board[row+i][col+i] == piece) {
                        counter++;
                    } else {
                        counter = 0;
                    }
                    if (counter == 4) return true;
                }
            }
        }
        return false;
    }

    public static boolean indexNotWithinBoundsAsceding(int row, int col, int i) {
        return (row-i) > 5 || (row-i) < 0 || (col+i) > 6 || (col+i) < 0;
    }

    public static boolean indexNotWithinBoundsDesceding(int row, int col, int i) {
        return (row+i) > 5 || (row+i) < 0 || (col+i) > 6 || (col+i) < 0;
    }


    public static boolean notInCornersAscending(int row, int col) {
        return notInUpperLeftCorner(row, col) && notInLowerRightCorner(row, col);
    }

    public static boolean notInUpperLeftCorner(int row, int col) {
        return (col != 0 || row >= 3) && (col != 1 || row >= 2) && (col != 2 || row >= 1);
    }

    public static boolean notInLowerRightCorner(int row, int col) {
        return (col != 4 || row <= 4) && (col != 5 || row <= 3) && (col != 6 || row <= 2);
    }

    public static boolean notInCornersDescending(int row, int col) {
        return notInLowerLeftCorner(row, col) && notInUpperRightCorner(row, col);
    }

    public static boolean notInLowerLeftCorner(int row, int col) {
        return (col != 0 || row <= 2) && (col != 1 || row <= 3) && (col != 2 || row <= 4);
    }

    public static boolean notInUpperRightCorner(int row, int col) {
        return (col != 4 || row >= 1) && (col != 5 || row >= 2) && (col != 6 || row >= 3);
    }

    // col, score
    public static int[] minimax(int depth, char piece, char[][] board) {
        int[] ord = {3,4,2,5,1,6,0};
        for (int i : ord) {
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
        if (piece == PLAYER_PIECE) { // maximizing player
            int score = -999999999;
            int bestMove = -1;
            for (int i : ord) {
                int nextRow = getNextOpenRow(i, board);
                if (nextRow == -1) continue;
                dropPiece(nextRow, i, PLAYER_PIECE, board);
                int newScore = minimax(depth-1, AI_PIECE, board)[1];
                if (newScore > score) {
                    score = newScore;
                    bestMove = i;
                }
                removePiece(nextRow, i, board);
            }
            return new int[] {bestMove, score};
        } else { // AI_PIECE, minimizing player
            int score = 999999999;
            int bestMove = -1;
            for (int i : ord) {
                int nextRow = getNextOpenRow(i, board);
                if (nextRow == -1) continue;
                dropPiece(nextRow, i, AI_PIECE, board);
                int newScore = minimax(depth-1, PLAYER_PIECE, board)[1];
                if (newScore < score) {
                    score = newScore;
                    bestMove = i;
                }
                removePiece(nextRow, i, board);
            }
            return new int[] {bestMove, score};
        }
    }
}

