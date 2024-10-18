package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Connect4!");

        System.out.print("Enter player 1 name: ");
        Player player1 = new Player(scanner.nextLine(), 'X');

        System.out.print("Enter player 2 name: ");
        Player player2 = new Player(scanner.nextLine(), 'O');

        Game game = new Game(player1, player2);
        game.playGame();
    }
}
