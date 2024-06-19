package soundwave.repository;

import soundwave.model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Migration {
    private static HashMap<String, Music> songs;
    private static HashMap<String, Artist> artists;
    private static HashMap<String, Genre> genres;
    private static HashMap<String, User> users;
    private static HashMap<String, ArrayList<Playlist>> playlists;
    private static String userActive;

    public Migration() {}

    // temporary database

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
