package soundwave.model;

import java.util.List;
import java.util.UUID;

public class Artist {
    private final String id = UUID.randomUUID().toString();
    private String
        name,
        bio,
        country;
    private List<Music> songs;

    public Artist() {}
}
