package soundwave.views;

import soundwave.repository.Migration;
import soundwave.util.ClearConsole;
import soundwave.util.Logger;
import soundwave.util.UserInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Playlist extends View {
    public Playlist() {}

    @Override
    public void console() {
        try {
            Scanner input = new Scanner(System.in);
            UserInterface ui = new UserInterface();

            do {
                ui.banner("MENU PLAYLIST");
                ui.playlist();
                System.out.print("Silahkan masukan pilihan Anda: [1 - 4]: ");
                String menu = input.nextLine();

                switch (menu) {
                    case "1":
                        read();
                        break;
                    case "2":
                        create();
                        break;
                    case "3":
                        update();
                        break;
                    case "4":
                        delete();
                        break;

                    default:
                        break;
                }

                if (menu.equalsIgnoreCase("0")) {
                    System.out.print("Apa anda yakin ingin kembali (yes/no) [default: yes]: ");
                    String out = input.nextLine();

                    if (!out.equalsIgnoreCase("no")) {
                        break;
                    }
                }
            } while (true);
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }

    @Override
    public void read() {
        try {
            UserInterface ui = new UserInterface();
            Scanner input = new Scanner(System.in);

            if (Migration.getPlaylists() == null || Migration.getPlaylists().get(Migration.getUserActive()) == null) {
                ClearConsole.clear();
                ui.banner("Data Playlist kosong");
            } else {
                if (Migration.getPlaylists().get(Migration.getUserActive()).size() < 1) {
                    ClearConsole.clear();
                    ui.banner("Data Playlist kosong");
                } else {
                    ui.readPlaylist(Migration.getPlaylists().get(Migration.getUserActive()));
                }
            }

            input.nextLine();
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }

    @Override
    public void create() {
        try {
            Scanner input = new Scanner(System.in);
            soundwave.model.Playlist playlist = new soundwave.model.Playlist();

            UserInterface ui = new UserInterface();
            ui.banner("TAMBAH PLAYLIST");

            // songs
            ArrayList<soundwave.model.Music> songs = new ArrayList<>();

            while(true) {
                Music music = new Music();
                music.read();
                System.out.print("ID Musik : ");
                songs.add(Migration.getSongs().get(input.nextLine()));

                System.out.print("Ingin menambahkan musik lagi? (yes/no) [default: no]: ");
                if (!input.nextLine().equalsIgnoreCase("yes")) {
                    break;
                }
            }
            playlist.setSongs(songs);

            ArrayList<soundwave.model.Playlist> playlists = Migration.getPlaylists() == null
                    ? new ArrayList<>()
                    : Migration.getPlaylists().get(Migration.getUserActive());
            playlists.add(playlist);

            boolean isClean = playlist.save(playlists);
            if (isClean) {
                ClearConsole.clear();
                ui.banner("Playlist berhasil disimpan!");
                input.nextLine();
            }
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }

    @Override
    public void update() {
        this.read();

        try {
            UserInterface ui = new UserInterface();
            if (Migration.getPlaylists() != null) {
                Scanner input = new Scanner(System.in);
                int index;
                soundwave.model.Playlist playlist;

                while(true) {
                    try {
                        System.out.print("Index Playlist : ");
                        index = Integer.parseInt(input.nextLine()) - 1;
                        playlist = Migration.getPlaylists().get(Migration.getUserActive()).get(index);
                        break;
                    } catch (Exception exception) {
                        ui.banner("Index tidak ada!");
                        Logger.log(exception);
                    }
                }
                ui.banner("EDIT PLAYLIST");

                // songs
                ArrayList<soundwave.model.Music> songs = new ArrayList<>();

                while(true) {
                    Music music = new Music();
                    music.read();
                    System.out.print("ID Musik : ");
                    songs.add(Migration.getSongs().get(input.nextLine()));

                    System.out.print("Ingin menambahkan musik lagi? (yes/no) [default: no]: ");
                    if (!input.nextLine().equalsIgnoreCase("yes")) {
                        break;
                    }
                }
                playlist.setSongs(songs);

                ArrayList<soundwave.model.Playlist> playlists = Migration.getPlaylists().get(Migration.getUserActive());
                playlists.set(index, playlist);

                boolean isClean = playlist.save(playlists);
                if (isClean) {
                    ClearConsole.clear();
                    ui.banner("Playlist berhasil disimpan!");
                    input.nextLine();
                }
            }

            if (Migration.getPlaylists() == null) {
                ClearConsole.clear();
                ui.banner("Data Playlist kosong!");
            }
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }

    @Override
    public void delete() {
        this.read();

        try {
            Scanner input = new Scanner(System.in);
            UserInterface ui = new UserInterface();
            ui.banner("HAPUS PLAYLIST");

            System.out.print("Index Playlist: ");
            Migration.getPlaylists().get(Migration.getUserActive()).remove(Integer.parseInt(input.nextLine()) - 1);

            ClearConsole.clear();
            ui.banner("Playlist berhasil dihapus!");
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }
}
