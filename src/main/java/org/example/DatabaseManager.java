package org.example;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/connect4";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public DatabaseManager() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS players (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(50) UNIQUE NOT NULL,
                    wins INT DEFAULT 0
                )
                """;
            stmt.execute(createTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DatabaseManager(Connection mockConnection) {
    }

    public void recordWin(String playerName) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String updateSQL = """
                INSERT INTO players (name, wins) VALUES (?, 1)
                ON DUPLICATE KEY UPDATE wins = wins + 1
                """;
            try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                pstmt.setString(1, playerName);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayHighScores() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            String querySQL = "SELECT name, wins FROM players ORDER BY wins DESC";
            try (ResultSet rs = stmt.executeQuery(querySQL)) {
                System.out.println("High Scores:");
                while (rs.next()) {
                    System.out.printf("%s: %d wins%n", rs.getString("name"), rs.getInt("wins"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
