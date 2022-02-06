package Tiralabra;

public class Board {
    private final int HEIGHT = 6;
    private final int WIDTH = 29;
    private final String GRID_ROW = "|   |   |   |   |   |   |   |";
    private char[][] grid;

    public Board() {
        this.grid = new char[this.HEIGHT][this.WIDTH]; // 7x6 gameboard with some space
        for (int i = 0; i < 6; i++) {
            this.grid[i] = this.GRID_ROW.toCharArray();
        }
    }

    public void makeAMove(int input, char disc) {
        int column = convertMove(input);
        for (int i = 5; i >= 0; i--) {
            if (this.grid[i][column] == ' ') {
                this.grid[i][column] = disc;
                break;
            }
        }
    }

    public int parseMove(String input) {
        if (input.chars().allMatch(Character::isDigit)) {
            int inputInt = Integer.parseInt(input);
            if (inputInt >= 1 && inputInt <= 7) {
                return inputInt;
            }
        }
        return -1;
    }

    public int convertMove(int input) {
        return switch (input) {
            case 1 -> 2;
            case 2 -> 6;
            case 3 -> 10;
            case 4 -> 14;
            case 5 -> 18;
            case 6 -> 22;
            case 7 -> 26;
            default -> -1;
        };
    }

    public void print() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 29; j++) {
                System.out.print(this.grid[i][j]);
            }
            System.out.println();
        }
    }

}
