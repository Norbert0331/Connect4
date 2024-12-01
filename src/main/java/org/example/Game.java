package org.example;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final boolean againstComputer;
    private final DatabaseManager dbManager;

    public Game(Player player1, Player player2, boolean againstComputer) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.againstComputer = againstComputer;
        this.dbManager = new DatabaseManager();
    }

    public void playGame() {
        System.out.println("Game started!");
        board.printBoard();

        while (true) {
            System.out.println(currentPlayer.getName() + "'s turn (Symbol: " + currentPlayer.getSymbol() + ")");
            int column;

            if (againstComputer && currentPlayer == player2) {
                column = getComputerMove();
                System.out.println("Computer chooses column: " + (column + 1));
            } else {
                column = getPlayerMove();
            }

            if (board.isValidMove(column)) {
                board.dropDisc(column, currentPlayer.getSymbol());
                board.printBoard();

                if (board.checkWin(currentPlayer.getSymbol())) {
                    System.out.println(currentPlayer.getName() + " wins!");
                    dbManager.recordWin(currentPlayer.getName());
                    break;
                }

                if (board.isFull()) {
                    System.out.println("The game is a draw!");
                    break;
                }

                switchPlayer();
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        dbManager.displayHighScores();
        System.out.println("Game over. Thanks for playing!");
    }

    int getPlayerMove() {
        System.out.print("Enter a column (1-7): ");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number between 1 and 7.");
            scanner.next();
        }
        int column = scanner.nextInt() - 1;
        if (column < 0 || column >= board.getColumns()) {
            System.out.println("Column out of range. Please enter a valid column.");
            return getPlayerMove();
        }
        return column;
    }

    private int getComputerMove() {
        Random random = new Random();
        int column;
        do {
            column = random.nextInt(board.getColumns());
        } while (!board.isValidMove(column));
        return column;
    }

    void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
