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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Game started!");
        board.printBoard();

        while (true) {
            if (againstComputer && currentPlayer == player2) {
                System.out.println("Computer's turn (Symbol: " + currentPlayer.getSymbol() + ")");
                int column = getComputerMove();
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
                continue;
            }

            System.out.println(currentPlayer.getName() + "'s turn (Symbol: " + currentPlayer.getSymbol() + ")");
            System.out.println("Enter a column (1-7) to play, or type 'save' to save the game, or 'load' to load a game:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("save")) {
                System.out.print("Enter the filename to save the board: ");
                String filename = scanner.nextLine();
                board.saveToFile(filename);
                System.out.println("Game saved to " + filename);
                continue;
            }

            if (input.equalsIgnoreCase("load")) {
                System.out.print("Enter the filename to load the board: ");
                String filename = scanner.nextLine();
                board.loadFromFile(filename);
                board.printBoard();
                continue;
            }

            int column;
            try {
                column = Integer.parseInt(input) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7, or 'save'/'load'.");
                continue;
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


    public int getComputerMove() {
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
