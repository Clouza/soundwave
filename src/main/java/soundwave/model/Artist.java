package soundwave.model;

import soundwave.repository.Migration;
import soundwave.util.Logger;

import java.util.HashMap;
import java.util.UUID;

public class Artist {
    private final String id = UUID.randomUUID().toString();
    private String
        name,
        bio,
        country;

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

    public boolean delete(String id) {
        try {
            if (Migration.getArtists().get(id) == null) {
                return false;
            }

            Migration.getArtists().remove(id);
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
