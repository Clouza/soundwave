package soundwave.model;

import java.util.HashMap;
import java.util.UUID;

public class User {
    private final String id = UUID.randomUUID().toString();
    private String name, favoriteMusic, email;

    public User() {}
}
