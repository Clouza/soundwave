package soundwave.model;

import soundwave.repository.Migration;
import soundwave.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class User {
    private final String id = UUID.randomUUID().toString();
    private String
        username,
        password,
        email,
        name;

    private ArrayList<Playlist> playlists = new ArrayList<>();

    public User() {}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean save() {
        try {
            boolean existingUsername =  Migration.getUsers() != null && Migration.getUsers().get(username) != null;

            if (existingUsername) {
                System.out.println("Username telah digunakan.");
                return false;
            }

            HashMap<String, User> users = Migration.getUsers() == null
                    ? new HashMap<String, User>()
                    : Migration.getUsers();

            users.put(this.username, this);
            Migration.setUsers(users);
        } catch (Exception exception) {
            Logger.log(exception);
            return false;
        }

        return true;
    }

    public boolean delete(String username) {
        try {
            if (Migration.getUsers().get(username) == null) {
                return false;
            }

            Migration.getUsers().remove(username);
        } catch (Exception exception) {
            Logger.log(exception);
            return false;
        }

        return true;
    }

    public HashMap<String, User> songs() {
        return Migration.getUsers();
    }
}
