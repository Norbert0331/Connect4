package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testConstructor() {
        Board board = new Board();
        assertNotNull(board.getGrid());
        for (int i = 0; i < board.getGrid().length; i++) {
            for (int j = 0; j < board.getGrid()[i].length; j++) {
                assertEquals(' ', board.getGrid()[i][j]);
            }
        }
    }

    @Test
    public void testIsValidMove() {
        Board board = new Board();
        assertTrue(board.isValidMove(0));
        assertTrue(board.isValidMove(6));
        assertFalse(board.isValidMove(-1));
        assertFalse(board.isValidMove(7));
    }

    @Test
    public void testDropDisc() {
        Board board = new Board();
        board.dropDisc(0, 'X');
        assertEquals('X', board.getGrid()[5][0]);
        board.dropDisc(0, 'O');
        assertEquals('O', board.getGrid()[4][0]);
    }

    @Test
    public void testCheckHorizontalWin() {
        Board board = new Board();
        for (int i = 0; i < 4; i++) {
            board.dropDisc(i, 'X');
        }
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void testCheckVerticalWin() {
        Board board = new Board();
        for (int i = 0; i < 4; i++) {
            board.dropDisc(0, 'X');
        }
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void testCheckDiagonalWin() {
        Board board = new Board();
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
        Board board = new Board();
        for (int i = 0; i < board.getGrid().length; i++) {
            for (int j = 0; j < board.getGrid()[i].length; j++) {
                board.dropDisc(j, 'X');
            }
        }
        assertTrue(board.isFull());
    }
}


