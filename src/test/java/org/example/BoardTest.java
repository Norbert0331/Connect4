package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testInitialBoardIsEmpty() {
        char[][] grid = board.getGrid();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                assertEquals(' ', grid[i][j], "Grid should be empty initially");
            }
        }
    }

    @Test
    void testDropDiscValidMove() {
        assertTrue(board.isValidMove(3), "Column 3 should be a valid move");
        board.dropDisc(3, 'X');
        assertEquals('X', board.getGrid()[5][3], "Disc should be dropped in the lowest empty spot");
    }

    @Test
    void testDropDiscInvalidMove() {
        for (int i = 0; i < 6; i++) {
            board.dropDisc(3, 'X');
        }
        assertFalse(board.isValidMove(3), "Column 3 should not be a valid move after being filled");
    }

    @Test
    void testCheckWinHorizontal() {
        board.dropDisc(0, 'X');
        board.dropDisc(1, 'X');
        board.dropDisc(2, 'X');
        board.dropDisc(3, 'X');
        assertTrue(board.checkWin('X'), "X should win horizontally");
    }

    @Test
    void testCheckWinVertical() {
        board.dropDisc(0, 'X');
        board.dropDisc(0, 'X');
        board.dropDisc(0, 'X');
        board.dropDisc(0, 'X');
        assertTrue(board.checkWin('X'), "X should win vertically");
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
    void testIsFull() {
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 6; i++) {
                board.dropDisc(j, 'X');
            }
        }
        assertTrue(board.isFull(), "Board should be full");
    }

    @Test
    void testSaveAndLoadFile() {
        String filename = "test_board.txt";
        board.dropDisc(0, 'X');
        board.saveToFile(filename);

        Board newBoard = new Board();
        newBoard.loadFromFile(filename);
        assertEquals(board.getGrid()[5][0], newBoard.getGrid()[5][0], "Loaded board should match saved board");

        new File(filename).delete();
    }

}