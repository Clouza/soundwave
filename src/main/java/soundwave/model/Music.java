package soundwave.model;

import java.util.UUID;

public class Music {
    private final String id = UUID.randomUUID().toString();
    private String
        artistId,
        genreId,
        duration,
        releaseDate;

    public Music() {}
}
