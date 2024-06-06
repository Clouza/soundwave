package soundwave.repository;

import soundwave.model.Artist;
import soundwave.model.Genre;
import soundwave.model.Music;
import soundwave.model.User;

import java.util.HashMap;

public class Migration {
    private static HashMap<String, Music> songs;
    private static HashMap<String, Artist> artists;
    private static HashMap<String, Genre> genres;
    private static HashMap<String, User> users;

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
}
