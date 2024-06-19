package soundwave.repository;

import com.github.javafaker.Faker;
import soundwave.model.Artist;
import soundwave.model.Genre;
import soundwave.model.Music;
import soundwave.model.User;

import java.util.HashMap;

public class Seeder {
    private Faker faker = new Faker();

    public Seeder(){}

    public void genreSeed() {
        HashMap<String, Genre> genres = new HashMap<>();

        Genre genre = new Genre();
        genre.setName(this.faker.music().genre());
        genre.setDescription(this.faker.music().genre() + " Description");
        genres.put(genre.getId(), genre);

        genre = new Genre();
        genre.setName(this.faker.music().genre());
        genre.setDescription(this.faker.music().genre() + " Description");
        genres.put(genre.getId(), genre);

        genre = new Genre();
        genre.setName(this.faker.music().genre());
        genre.setDescription(this.faker.music().genre() + " Description");
        genres.put(genre.getId(), genre);

        genre = new Genre();
        genre.setName(this.faker.music().genre());
        genre.setDescription(this.faker.music().genre() + " Description");
        genres.put(genre.getId(), genre);

        genre = new Genre();
        genre.setName(this.faker.music().genre());
        genre.setDescription(this.faker.music().genre() + " Description");
        genres.put(genre.getId(), genre);

        Migration.setGenres(genres);
    }

    public void artistSeed() {
        HashMap<String, Artist> artists = new HashMap<>();

        Artist artist = new Artist();
        artist.setName(this.faker.artist().name());
        artist.setBio(this.faker.app().name());
        artist.setCountry(this.faker.country().name());
        artists.put(artist.getId(), artist);

        artist = new Artist();
        artist.setName(this.faker.artist().name());
        artist.setBio(this.faker.app().name());
        artist.setCountry(this.faker.country().name());
        artists.put(artist.getId(), artist);

        artist = new Artist();
        artist.setName(this.faker.artist().name());
        artist.setBio(this.faker.app().name());
        artist.setCountry(this.faker.country().name());
        artists.put(artist.getId(), artist);

        artist = new Artist();
        artist.setName(this.faker.artist().name());
        artist.setBio(this.faker.app().name());
        artist.setCountry(this.faker.country().name());
        artists.put(artist.getId(), artist);

        artist = new Artist();
        artist.setName(this.faker.artist().name());
        artist.setBio(this.faker.app().name());
        artist.setCountry(this.faker.country().name());
        artists.put(artist.getId(), artist);

        Migration.setArtists(artists);
    }

    public void musicSeed() {
        HashMap<String, Music> songs = new HashMap<>();

        for(Artist artist: Migration.getArtists().values()) {
            for (Genre genre: Migration.getGenres().values()) {
                Music music = new Music();
                music.setName(this.faker.music().instrument());
                music.setDuration(this.faker.number().digit());
                music.setReleaseDate(String.valueOf(this.faker.number().numberBetween(1950, 2024)));
                music.setArtist(artist);
                music.setGenre(genre);
                songs.put(music.getId(), music);
            }
        }

        Migration.setSongs(songs);
    }

    public void userSeed() {
        HashMap<String, User> users = new HashMap<>();

        User admin = new User();
        admin.setName("Admin");
        admin.setEmail("admin@example.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        users.put("admin", admin);

        User user = new User();
        String username = this.faker.name().username();
        user.setName(this.faker.name().fullName());
        user.setEmail(this.faker.name().firstName() + "@gmail.com");
        user.setUsername(username);
        user.setPassword(this.faker.name().username() + "123");
        users.put(username, user);

        user = new User();
        username = this.faker.name().username();
        user.setName(this.faker.name().fullName());
        user.setEmail(this.faker.name().firstName() + "@gmail.com");
        user.setUsername(username);
        user.setPassword(this.faker.name().username() + "123");
        users.put(username, user);

        user = new User();
        username = this.faker.name().username();
        user.setName(this.faker.name().fullName());
        user.setEmail(this.faker.name().firstName() + "@gmail.com");
        user.setUsername(username);
        user.setPassword(this.faker.name().username() + "123");
        users.put(username, user);

        user = new User();
        username = this.faker.name().username();
        user.setName(this.faker.name().fullName());
        user.setEmail(this.faker.name().firstName() + "@gmail.com");
        user.setUsername(username);
        user.setPassword(this.faker.name().username() + "123");
        users.put(username, user);

        user = new User();
        username = this.faker.name().username();
        user.setName(this.faker.name().fullName());
        user.setEmail(this.faker.name().firstName() + "@gmail.com");
        user.setUsername(username);
        user.setPassword(this.faker.name().username() + "123");
        users.put(username, user);

        Migration.setUsers(users);
    }
}
