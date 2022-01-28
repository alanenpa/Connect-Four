
package Tiralabra;

public class App {

    public static void printBoard(char[][] board) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 25; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[6][25]; // 7x6 gameboard with some space
        String gridRow = "|   |   |   |   |   |   |";
        for (int i = 0; i < 6; i++) {
            board[i] = gridRow.toCharArray();
        }
        printBoard(board);

    }
}
