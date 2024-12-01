package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerCreation() {
        Player player = new Player("Player1", 'X');
        assertEquals("Player1", player.getName());
        assertEquals('X', player.getSymbol());
    }

    @Test
    void testEqualsSameObject() {
        Player player = new Player("Player1", 'X');
        assertEquals(player, player);
    }

    @Test
    void testEqualsDifferentObjectsWithSameValues() {
        Player player1 = new Player("Player1", 'X');
        Player player2 = new Player("Player1", 'X');
        assertEquals(player1, player2);
    }

    @Test
    void testNotEqualsDifferentNames() {
        Player player1 = new Player("Player1", 'X');
        Player player2 = new Player("Player2", 'X');
        assertNotEquals(player1, player2);
    }

    @Test
    void testNotEqualsDifferentSymbols() {
        Player player1 = new Player("Player1", 'X');
        Player player2 = new Player("Player1", 'O');
        assertNotEquals(player1, player2);
    }

    @Test
    void testHashCodeSameValues() {
        Player player1 = new Player("Player1", 'X');
        Player player2 = new Player("Player1", 'X');
        assertEquals(player1.hashCode(), player2.hashCode());
    }

    @Test
    void testHashCodeDifferentNames() {
        Player player1 = new Player("Player1", 'X');
        Player player2 = new Player("Player2", 'X');
        assertNotEquals(player1.hashCode(), player2.hashCode());
    }

    @Test
    void testHashCodeDifferentSymbols() {
        Player player1 = new Player("Player1", 'X');
        Player player2 = new Player("Player2", 'O');
        assertNotEquals(player1.hashCode(), player2.hashCode());
    }

    @Test
    void testToString() {
        Player player = new Player("Player1", 'X');
        assertEquals("Player{name='Player1', symbol=X}", player.toString());
    }
}