package soundwave.model;

import soundwave.repository.Migration;
import soundwave.util.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Artist {
    private final String id = UUID.randomUUID().toString();
    private String
        name,
        bio,
        country;
    private HashMap<String, Music> songs;

    public Artist() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSongs(HashMap<String, Music> songs) {
        this.songs = songs;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getCountry() {
        return country;
    }

    public HashMap<String, Music> getSongs() {
        return songs;
    }

    public boolean save() {
        try {
            boolean existingArtist =  Migration.getArtists() != null && Migration.getArtists().get(this.name) != null;
            if (existingArtist) {
                System.out.println("Genre sudah ada.");
                return false;
            }

            HashMap<String, Artist> artists = Migration.getArtists() == null
                    ? new HashMap<String, Artist>()
                    : Migration.getArtists();

            artists.put(this.name, this);
            Migration.setArtists(artists);
        } catch (Exception exception) {
            Logger.log(exception);
            return false;
        }

        return true;
    }

    public boolean delete(String artist) {
        try {
            if (Migration.getArtists().get(artist) == null) {
                return false;
            }

            Migration.getArtists().remove(artist);
        } catch (Exception exception) {
            Logger.log(exception);
            return false;
        }

        return true;
    }

    public HashMap<String, Artist> artist() {
        return Migration.getArtists();
    }
}
