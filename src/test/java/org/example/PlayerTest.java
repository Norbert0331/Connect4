package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testConstructor() {
        Player player = new Player("Player1", 'X');
        assertEquals("Player1", player.getName());
        assertEquals('X', player.getSymbol());
    }

    @Test
    public void testGetName() {
        Player player = new Player("Player2", 'O');
        assertEquals("Player2", player.getName());
    }

    @Test
    public void testGetSymbol() {
        Player player = new Player("Player3", 'X');
        assertEquals('X', player.getSymbol());
    }
}
