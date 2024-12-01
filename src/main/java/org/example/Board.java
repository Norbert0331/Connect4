package org.example;

public class Board {
    private final int rows = 6;
    private final int columns = 7;
    private final char[][] grid;

    public Board() {
        grid = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("|" + grid[i][j]);
            }
            System.out.println("|");
        }
        System.out.println(" 1 2 3 4 5 6 7 ");
    }

    public boolean isValidMove(int column) {
        if (column < 0 || column >= columns) {
            return false;
        }
        return grid[0][column] == ' ';
    }

    public void dropDisc(int column, char symbol) {
        for (int i = rows - 1; i >= 0; i--) {
            if (grid[i][column] == ' ') {
                grid[i][column] = symbol;
                break;
            }
        }
    }

    public boolean checkWin(char symbol) {
        return checkHorizontal(symbol) || checkVertical(symbol) || checkDiagonal(symbol);
    }

    private boolean checkHorizontal(char symbol) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= columns - 4; j++) {
                if (grid[i][j] == symbol && grid[i][j + 1] == symbol && grid[i][j + 2] == symbol && grid[i][j + 3] == symbol) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical(char symbol) {
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i <= rows - 4; i++) {
                if (grid[i][j] == symbol && grid[i + 1][j] == symbol && grid[i + 2][j] == symbol && grid[i + 3][j] == symbol) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(char symbol) {
        // Check \ diagonal
        for (int i = 0; i <= rows - 4; i++) {
            for (int j = 0; j <= columns - 4; j++) {
                if (grid[i][j] == symbol && grid[i + 1][j + 1] == symbol && grid[i + 2][j + 2] == symbol && grid[i + 3][j + 3] == symbol) {
                    return true;
                }
            }
        }
        // Check / diagonal
        for (int i = 3; i < rows; i++) {
            for (int j = 0; j <= columns - 4; j++) {
                if (grid[i][j] == symbol && grid[i - 1][j + 1] == symbol && grid[i - 2][j + 2] == symbol && grid[i - 3][j + 3] == symbol) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFull() {
        for (int j = 0; j < columns; j++) {
            if (grid[0][j] == ' ') {
                return false;
            }
        }
        return true;
    }

    public int getColumns() {
        return columns;
    }

    public char[][] getGrid() {
        return grid;
    }
}
