package soundwave.views;

import soundwave.repository.Migration;
import soundwave.util.ClearConsole;
import soundwave.util.Logger;
import soundwave.util.UserInterface;

import java.util.Scanner;

public class Music extends View {
    public Music() {}

    @Override
    public void console() {
        try {
            Scanner input = new Scanner(System.in);
            UserInterface ui = new UserInterface();

            do {
                ClearConsole.clear();
                ui.banner("MENU MUSIK");
                ui.music();

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
            ClearConsole.clear();
            UserInterface ui = new UserInterface();
            Scanner input = new Scanner(System.in);

            if (Migration.getSongs().size() < 1) {
                ClearConsole.clear();
                ui.banner("Data Musik kosong");
            } else {
                for (soundwave.model.Music music: Migration.getSongs().values()) {
                    ui.readMusic(music);
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
            ClearConsole.clear();
            Scanner input = new Scanner(System.in);
            soundwave.model.Music music = new soundwave.model.Music();

            UserInterface ui = new UserInterface();
            ui.banner("TAMBAH MUSIK");

            System.out.print("Nama                  : ");
            music.setName(input.nextLine());

            System.out.print("Durasi (menit)        : ");
            music.setDuration(input.nextLine());

            System.out.print("Release Date [yyyy]   : ");
            music.setReleaseDate(input.nextLine());

            // artist
            Artist artist = new Artist();
            artist.read();
            System.out.print("Artis ID              : ");
            music.setArtist(Migration.getArtists().get(input.nextLine()));

            // genre
            Genre genre = new Genre();
            genre.read();
            System.out.print("Genre ID              : ");
            music.setGenre(Migration.getGenres().get(input.nextLine()));

            boolean isClean = music.save();
            if (isClean) {
                ClearConsole.clear();
                ui.banner("Musik berhasil disimpan!");
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
            Scanner input = new Scanner(System.in);
            System.out.print("Masukkan ID: ");
            soundwave.model.Music music = Migration.getSongs().get(input.nextLine());

            UserInterface ui = new UserInterface();
            ui.banner("EDIT MUSIK");

            System.out.print("Nama: ");
            music.setName(input.nextLine());

            System.out.print("Duration: ");
            music.setDuration(input.nextLine());

            System.out.print("Release Date [yyyy]: ");
            music.setReleaseDate(input.nextLine());

            // artist
            Artist artist = new Artist();
            artist.read();
            System.out.print("Artis ID              : ");
            music.setArtist(Migration.getArtists().get(input.nextLine()));

            // genre
            Genre genre = new Genre();
            genre.read();
            System.out.print("Genre ID              : ");
            music.setGenre(Migration.getGenres().get(input.nextLine()));

            boolean isClean = music.save();
            if (isClean) {
                ClearConsole.clear();
                ui.banner("Musik berhasil diperbarui!");
                input.nextLine();
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
            ui.banner("HAPUS MUSIK");

            System.out.print("Masukkan ID: ");
            Migration.getSongs().remove(input.nextLine());

            ClearConsole.clear();
            ui.banner("Musik berhasil dihapus!");
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }
}
