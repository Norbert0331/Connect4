package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Connect4!");
        System.out.println("Choose game mode:");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs Computer");
        int choice;

        while (true) {
            System.out.print("Enter your choice (1 or 2): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1 || choice == 2) {
                    break;
                }
            }
            System.out.println("Invalid choice. Please enter 1 or 2.");
            scanner.nextLine();
        }

        Player player1 = null;
        Player player2 = null;

        if (choice == 1) {
            System.out.print("Enter player 1 name: ");
            player1 = new Player(scanner.nextLine(), 'X');

            System.out.print("Enter player 2 name: ");
            player2 = new Player(scanner.nextLine(), 'O');
        } if (choice == 2) {
            System.out.print("Enter your name: ");
            player1 = new Player(scanner.nextLine(), 'X');
            player2 = new Player("Computer", 'O');
        }

        Game game = new Game(player1, player2, choice == 2);
        game.playGame();
    }
}

