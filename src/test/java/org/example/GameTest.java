package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Mock
    private DatabaseManager mockDbManager;

    @Mock
    private Scanner mockScanner;

    @Mock
    private Random mockRandom;

    private Game game;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        player1 = new Player("Player1", 'X');
        player2 = new Player("Player2", 'O');
        game = new Game(player1, player2, false);
    }

    @Test
    public void testSwitchPlayer() {
        assertEquals(player1, game.getCurrentPlayer());
        game.switchPlayer();
        assertEquals(player2, game.getCurrentPlayer());
        game.switchPlayer();
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    public void testIsValidMove() {
        Board board = game.getBoard();
        assertTrue(board.isValidMove(0));
        assertFalse(board.isValidMove(-1));
        assertFalse(board.isValidMove(7));
    }

    @Test
    public void testDropDisc() {
        Board board = game.getBoard();
        board.dropDisc(0, 'X');
        assertEquals('X', board.getGrid()[5][0]);
        board.dropDisc(0, 'O');
        assertEquals('O', board.getGrid()[4][0]);
    }

    @Test
    public void testCheckHorizontalWin() {
        Board board = game.getBoard();
        for (int i = 0; i < 4; i++) {
            board.dropDisc(i, 'X');
        }
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void testCheckVerticalWin() {
        Board board = game.getBoard();
        for (int i = 0; i < 4; i++) {
            board.dropDisc(0, 'X');
        }
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void testCheckDiagonalWin() {
        Board board = game.getBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                board.dropDisc(i, 'O');
            }
            board.dropDisc(i, 'X');
        }
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void testIsFull() {
        Board board = game.getBoard();
        for (int i = 0; i < board.getGrid().length; i++) {
            for (int j = 0; j < board.getGrid()[i].length; j++) {
                board.dropDisc(j, 'X');
            }
        }
        assertTrue(board.isFull());
    }
}
