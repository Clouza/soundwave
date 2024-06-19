package soundwave.model;

import soundwave.repository.Migration;
import soundwave.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class Playlist {
    private ArrayList<Music> songs;

    public void setSongs(ArrayList<Music> songs) {
        this.songs = songs;
    }

    public ArrayList<Music> getSongs() {
        return songs;
    }

    public boolean save(ArrayList<Playlist> playlists) {
        try {
            HashMap<String, ArrayList<Playlist>> playlist = Migration.getPlaylists() == null
                    ? new HashMap<String, ArrayList<Playlist>>()
                    : Migration.getPlaylists();

            playlist.put(Migration.getUserActive(), playlists);
            Migration.setPlaylists(playlist);
        } catch (Exception exception) {
            Logger.log(exception);
            return false;
        }

        return true;
    }

    public boolean delete(Integer index) {
        try {
            if (Migration.getPlaylists().get(Migration.getUserActive()) == null) {
                return false;
            }

            Migration.getPlaylists().get(Migration.getUserActive()).remove(index);
        } catch (Exception exception) {
            Logger.log(exception);
            return false;
        }

        return true;
    }
}
