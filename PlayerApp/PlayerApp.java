package PlayerApp;

import java.sql.*;
import java.util.*;

interface PlayerOperations {
    void addPlayer(Player player) throws SQLException;

    Player getPlayer(int id) throws SQLException;

    List<Player> getAllPlayers() throws SQLException;

    List<Player> getPlayersByCountry(String country) throws SQLException;

    List<Player> getPlayersByExperience() throws SQLException;

    void updatePlayer(Player player) throws SQLException;

    void deletePlayer(int id) throws SQLException;
}

abstract class AbstractPlayer {
    protected int id;
    protected String name;
    protected String skill;
    protected int exp;
    protected String country;
    protected double overall_score;

    public AbstractPlayer(int id, String name, String skill, int exp, String country, double overall_score) {
        this.id = id;
        this.name = name;
        this.skill = skill;
        this.exp = exp;
        this.country = country;
        this.overall_score = overall_score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSkill() {
        return skill;
    }

    public int getExp() {
        return exp;
    }

    public String getCountry() {
        return country;
    }

    public double getOverallScore() {
        return overall_score;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + name + ", skill=" + skill + ", exp=" + exp + ", country=" + country
                + ", overall_score=" + overall_score + "]";
    }
}

class Player extends AbstractPlayer implements PlayerOperations {

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/capgemini";
        String username = "root";
        String password = "2003";
        return DriverManager.getConnection(url, username, password);
    }

    public Player(int id, String name, String skill, int exp, String country, double overall_score) {
        super(id, name, skill, exp, country, overall_score);
    }

    @Override
    public void addPlayer(Player player) throws SQLException {
        String sql = "INSERT INTO players (name, skill, exp, country, overall_score) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, player.getName());
            pstmt.setString(2, player.getSkill());
            pstmt.setInt(3, player.getExp());
            pstmt.setString(4, player.getCountry());
            pstmt.setDouble(5, player.getOverallScore());
            pstmt.executeUpdate();
            System.out.println("Player added successfully: " + player.getName());
        }
    }

    @Override
    public Player getPlayer(int id) throws SQLException {
        String sql = "SELECT * FROM players WHERE id = ?";
        try (Connection con = getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Player fetchedPlayer = new Player(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
                        rs.getInt("exp"),
                        rs.getString("country"), rs.getDouble("overall_score"));
                System.out.println("Fetched player: " + fetchedPlayer);
                return fetchedPlayer;
            }
        }
        System.out.println("No player found with ID: " + id);
        return null;
    }

    @Override
    public List<Player> getAllPlayers() throws SQLException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM players";
        try (Connection con = getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                players.add(new Player(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
                        rs.getInt("exp"), rs.getString("country"), rs.getDouble("overall_score")));
            }
        }
        System.out.println("All players fetched: " + players.size() + " players.");
        return players;
    }

    @Override
    public List<Player> getPlayersByCountry(String country) throws SQLException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM players WHERE country = ?";
        try (Connection con = getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, country);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                players.add(new Player(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
                        rs.getInt("exp"), rs.getString("country"), rs.getDouble("overall_score")));
            }
        }
        System.out.println("Players fetched from country: " + country + " - " + players.size() + " players.");
        return players;
    }

    @Override
    public List<Player> getPlayersByExperience() throws SQLException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM players ORDER BY exp DESC";
        try (Connection con = getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                players.add(new Player(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
                        rs.getInt("exp"), rs.getString("country"), rs.getDouble("overall_score")));
            }
        }
        System.out.println("Players fetched sorted by experience.");
        return players;
    }

    @Override
    public void updatePlayer(Player player) throws SQLException {
        String sql = "UPDATE players SET name = ?, skill = ?, exp = ?, country = ?, overall_score = ? WHERE id = ?";
        try (Connection con = getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, player.getName());
            pstmt.setString(2, player.getSkill());
            pstmt.setInt(3, player.getExp());
            pstmt.setString(4, player.getCountry());
            pstmt.setDouble(5, player.getOverallScore());
            pstmt.setInt(6, player.getId());
            pstmt.executeUpdate();
            System.out.println("Player updated: " + player.getName());
        }
    }

    @Override
    public void deletePlayer(int id) throws SQLException {
        String sql = "DELETE FROM players WHERE id = ?";
        try (Connection con = getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Player with ID " + id + " deleted.");
        }
    }
}

public class PlayerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player player = new Player(0, "Virat Kohli", "Batsman", 12, "India", 90.5);
        Player player1 = new Player(0, "Rohit Sharma", "Batsman", 15, "India", 85.7);
        Player player2 = new Player(0, "Jasprit Bumrah", "Bowler", 9, "India", 84.2);
        Player player3 = new Player(0, "Ben Stokes", "All-Rounder", 10, "England", 88.4);
        Player player4 = new Player(0, "David Warner", "Batsman", 13, "Australia", 86.1);
        Player player5 = new Player(0, "Kane Williamson", "Batsman", 12, "New Zealand", 89.0);
        Player player6 = new Player(0, "AB de Villiers", "Wicketkeeper", 14, "South Africa", 91.2);
        Player player7 = new Player(0, "Shakib Al Hasan", "All-Rounder", 17, "Bangladesh", 85.5);
        Player player8 = new Player(0, "Moeen Ali", "All-Rounder", 8, "England", 82.3);
        Player player9 = new Player(0, "Kagiso Rabada", "Bowler", 9, "South Africa", 87.1);
        Player player10 = new Player(0, "Chris Gayle", "Batsman", 20, "West Indies", 91.8);

        try {
            // Adding players to the database
            player.addPlayer(player);
            player1.addPlayer(player1);
            player2.addPlayer(player2);
            player3.addPlayer(player3);
            player4.addPlayer(player4);
            player5.addPlayer(player5);

            // Fetching player by ID
            Player fetchedPlayer = player.getPlayer(1);
            System.out.println(fetchedPlayer);

            // Listing all players
            List<Player> players = player.getAllPlayers();
            players.forEach(System.out::println);

            // Listing players by country
            List<Player> playersByCountry = player.getPlayersByCountry("India");
            playersByCountry.forEach(System.out::println);

            // Listing players by experience
            List<Player> playersByExperience = player.getPlayersByExperience();
            playersByExperience.forEach(System.out::println);

            // Updating player data
            player.updatePlayer(new Player(1, "Rohit Sharma", "Batsman", 16, "India", 86.0));

            // Deleting a player
            player.deletePlayer(2);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
