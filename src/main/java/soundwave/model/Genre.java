package soundwave.model;

import soundwave.repository.Migration;
import soundwave.util.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Genre {
    private final String id = UUID.randomUUID().toString();
    private String name, description;
    private HashMap<String, Music> songs;

    public Genre() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSongs(HashMap<String, Music> songs) {
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<String, Music> getSongs() {
        return songs;
    }

    public String getId() {
        return id;
    }

    public boolean save() {
        try {
            boolean existingGenre =  Migration.getGenres() != null && Migration.getGenres().get(this.name) != null;
            if (existingGenre) {
                System.out.println("Genre sudah ada.");
                return false;
            }

            HashMap<String, Genre> genres = Migration.getGenres() == null
                    ? new HashMap<String, Genre>()
                    : Migration.getGenres();

            genres.put(this.name, this);
            Migration.setGenres(genres);
        } catch (Exception exception) {
            Logger.log(exception);
            return false;
        }

        return true;
    }

    public boolean delete(String id) {
        try {
            if (Migration.getGenres().get(id) == null) {
                return false;
            }

            Migration.getGenres().remove(id);
        } catch (Exception exception) {
            Logger.log(exception);
            return false;
        }

        return true;
    }

    public HashMap<String, Genre> genres() {
        return Migration.getGenres();
    }
}
