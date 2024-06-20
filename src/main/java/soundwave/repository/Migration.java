package soundwave.repository;

import soundwave.model.*;
import soundwave.util.Dotenv;
import soundwave.util.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Migration {
    private static HashMap<String, Music> songs;
    private static HashMap<String, Artist> artists;
    private static HashMap<String, Genre> genres;
    private static HashMap<String, User> users;
    private static HashMap<String, ArrayList<Playlist>> playlists;
    private static String userActive;

    public Migration() {
        String database = new Dotenv().ENV("DATABASE_NAME");
        if(!Objects.equals(database, "")) {
            try {
                Connection conn = DriverManager.getConnection(database);
                Statement stmt = conn.createStatement();

                // drop
                String sql = "DROP TABLE IF EXISTS users";
                stmt.execute(sql);

                // create
                sql = "CREATE TABLE users (" +
                        "id VARCHAR(255) PRIMARY KEY," +
                        "username VARCHAR(255) NOT NULL UNIQUE," +
                        "password VARCHAR(255) NOT NULL," +
                        "email VARCHAR(255) NOT NULL UNIQUE," +
                        "name VARCHAR(255)" +
                        ");";
                stmt.execute(sql);

                // insert
                sql = "INSERT INTO users(id, username, password, email, name) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement prpstmt = conn.prepareStatement(sql);

                prpstmt.setString(1, UUID.randomUUID().toString()); // id
                prpstmt.setString(2, "admin"); // username
                prpstmt.setString(3, "admin"); // password
                prpstmt.setString(4, "admin@example.com"); // email
                prpstmt.setString(5, "Admin"); // name
                prpstmt.executeUpdate();

                // read
                sql = "SELECT id, username, password, email, name FROM users";
                prpstmt = conn.prepareStatement(sql);
                ResultSet rs = prpstmt.executeQuery();

                HashMap<String, User> userHashMap = Migration.getUsers();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String name = rs.getString("name");

                    User user = new User();
                    user.setName(name);
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setId(id);

                    userHashMap.put(username, user);
                }

                Migration.setUsers(userHashMap);
            } catch (SQLException exception) {
                Logger.log(exception);
                exception.printStackTrace();
            }
        }
    }

    // transaction

    public static void setSongs(HashMap<String, Music> songs) {
        Migration.songs = songs;
    }

    public static void setArtists(HashMap<String, Artist> artists) {
        Migration.artists = artists;
    }

    public static void setGenres(HashMap<String, Genre> genres) {
        Migration.genres = genres;
    }

    public static void setUsers(HashMap<String, User> users) {
        Migration.users = users;
    }

    public static void setPlaylists(HashMap<String, ArrayList<Playlist>> playlists) {
        Migration.playlists = playlists;
    }

    public static void setUserActive(String userActive) {
        Migration.userActive = userActive;
    }

    public static HashMap<String, Music> getSongs() {
        return songs;
    }

    public static HashMap<String, Artist> getArtists() {
        return artists;
    }

    public static HashMap<String, Genre> getGenres() {
        return genres;
    }

    public static HashMap<String, User> getUsers() {
        return users;
    }

    public static HashMap<String, ArrayList<Playlist>> getPlaylists() {
        return playlists;
    }

    public static String getUserActive() {
        return userActive;
    }
}
