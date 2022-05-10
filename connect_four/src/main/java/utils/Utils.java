package utils;

public class Utils {

    /**
     * Prints the gameboard with some extra space and divided columns.
     * @param board board which is used for the game
     */
    public static void print(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
    }

    /**
     * Checks if the user input for a column to be chosen is a number between 1 and 7.
     * If yes, the input is parsed to an integer and substracted by one, in order to match it to a corresponding index.
     * If not, the user is either quitting with input 'q' or the input is invalid
     * @param input user input
     * @return integer of the valid input. -1 if the input is invalid and -2 if the input is 'q' for quitting.
     */
    public static int validateInput(String input) {
        if (input.chars().allMatch(Character::isDigit) && !input.equals("")) {
            int inputInt = Integer.parseInt(input);
            if (inputInt >= 1 && inputInt <= 7) {
                return inputInt-1;
            }
        } else if (input.equals("q")) {
            return -2;
        }
        return -1;
    }

}
