package soundwave.model;

import java.util.List;
import java.util.UUID;

public class Genre {
    private final String id = UUID.randomUUID().toString();
    private String name, description;
    private List<Music> songs;
}
