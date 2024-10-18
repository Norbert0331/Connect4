package org.example;

public class Board {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    private final char[][] grid;

    public Board() {
        grid = new char[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                grid[row][col] = '.';
            }
        }
    }

    public boolean makeMove(int column, char symbol) {
        if (column < 0 || column >= COLUMNS) {
            return false;
        }

        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][column] == '.') {
                grid[row][column] = symbol;
                return true;
            }
        }

        return false;
    }

    public boolean isFull() {
        for (int col = 0; col < COLUMNS; col++) {
            if (grid[0][col] == '.') {
                return false;
            }
        }
        return true;
    }

    public char[][] getGrid() {
        return grid;
    }

    public void printBoard() {
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
