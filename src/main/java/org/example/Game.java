package org.example;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public void playGame() {
        while (true) {
            board.printBoard();
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + "): ");
            int column = getPlayerMove();

            if (board.makeMove(column, currentPlayer.getSymbol())) {
                if (checkWin(currentPlayer.getSymbol())) {
                    board.printBoard();
                    System.out.println("Congratulations " + currentPlayer.getName() + ", you won!");
                    break;
                } else if (board.isFull()) {
                    board.printBoard();
                    System.out.println("It's a draw!");
                    break;
                }
                switchPlayer();
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }

    private int getPlayerMove() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        return scanner.nextInt();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private boolean checkWin(char symbol) {
        return checkHorizontal(symbol) || checkVertical(symbol) || checkDiagonal(symbol);
    }

    private boolean checkHorizontal(char symbol) {
        for (int row = 0; row < Board.ROWS; row++) {
            int count = 0;
            for (int col = 0; col < Board.COLUMNS; col++) {
                if (board.getGrid()[row][col] == symbol) {
                    count++;
                    if (count == 4) {
                        return true; // Négy azonos korong van vízszintesen
                    }
                } else {
                    count = 0; // Ha nem azonos a jel, a számlálót lenullázzuk
                }
            }
        }
        return false;
    }

    private boolean checkVertical(char symbol) {
        for (int col = 0; col < Board.COLUMNS; col++) {
            int count = 0;
            for (int row = 0; row < Board.ROWS; row++) {
                if (board.getGrid()[row][col] == symbol) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(char symbol) {
        for (int row = 0; row < Board.ROWS - 3; row++) {
            for (int col = 0; col < Board.COLUMNS - 3; col++) {
                if (board.getGrid()[row][col] == symbol &&
                        board.getGrid()[row + 1][col + 1] == symbol &&
                        board.getGrid()[row + 2][col + 2] == symbol &&
                        board.getGrid()[row + 3][col + 3] == symbol) {
                    return true;
                }
            }
        }

        for (int row = 0; row < Board.ROWS - 3; row++) {
            for (int col = 3; col < Board.COLUMNS; col++) {
                if (board.getGrid()[row][col] == symbol &&
                        board.getGrid()[row + 1][col - 1] == symbol &&
                        board.getGrid()[row + 2][col - 2] == symbol &&
                        board.getGrid()[row + 3][col - 3] == symbol) {
                    return true;
                }
            }
        }

        return false;
    }
}
