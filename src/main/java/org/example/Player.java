package org.example;

public final class Player {
    private final String name;
    private final char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return symbol == player.symbol && name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return 31 * name.hashCode() + Character.hashCode(symbol);
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', symbol=" + symbol + '}';
    }
}
