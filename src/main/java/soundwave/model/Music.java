package soundwave.model;

import soundwave.repository.Migration;
import soundwave.util.Logger;

import java.util.HashMap;
import java.util.UUID;

public class Music {
    private final String id = UUID.randomUUID().toString();
    private String
        artistId,
        genreId,
        duration,
        releaseDate;

    public Music() {}

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getGenreId() {
        return genreId;
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
            boolean existingMusic =  Migration.getSongs() != null && Migration.getSongs().get(id) != null;
            if (!existingMusic) {
                System.out.println("Musik tidak ditemukan.");
                return false;
            }

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
