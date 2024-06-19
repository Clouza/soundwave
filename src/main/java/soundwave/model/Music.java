package soundwave.model;

import soundwave.repository.Migration;
import soundwave.util.Logger;

import java.util.HashMap;
import java.util.UUID;

public class Music {
    private final String id = UUID.randomUUID().toString();
    private String
        name,
        duration,
        releaseDate;

    private Artist artist;
    private Genre genre;

    public Music() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public Artist getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getDuration() {
        return duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getId() {
        return id;
    }

    public boolean save() {
        try {
            HashMap<String, Music> songs = Migration.getSongs() == null
                    ? new HashMap<String, Music>()
                    : Migration.getSongs();

            songs.put(this.id, this);
            Migration.setSongs(songs);
        } catch (Exception exception) {
            Logger.log(exception);
            return false;
        }

        return true;
    }

    public boolean delete(String id) {
        try {
            if (Migration.getSongs().get(id) == null) {
                return false;
            }
            
            Migration.getSongs().remove(id);
        } catch (Exception exception) {
            Logger.log(exception);
            return false;
        }
        
        return true;
    }

    public HashMap<String, Music> songs() {
        return Migration.getSongs();
    }
}
