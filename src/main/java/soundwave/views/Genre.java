package soundwave.views;

import soundwave.repository.Migration;
import soundwave.util.ClearConsole;
import soundwave.util.Logger;
import soundwave.util.UserInterface;

import java.util.Scanner;

public class Genre extends View {
    public Genre() {}

    @Override
    public void console() {
        try {
            Scanner input = new Scanner(System.in);
            UserInterface ui = new UserInterface();

            do {
                ui.banner("MENU GENRE");
                ui.genre();
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

            if (Migration.getGenres() == null) {
                ClearConsole.clear();
                ui.banner("Data Musik kosong");
            } else {
                for (soundwave.model.Genre genre: Migration.getGenres().values()) {
                    ui.readGenres(genre);
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
            soundwave.model.Genre genre = new soundwave.model.Genre();

            UserInterface ui = new UserInterface();
            ui.banner("TAMBAH GENRE");

            System.out.print("Nama      : ");
            genre.setName(input.nextLine());

            System.out.print("Deskripsi : ");
            genre.setDescription(input.nextLine());

            boolean isClean = genre.save();
            if (isClean) {
                ClearConsole.clear();
                ui.banner("Genre berhasil disimpan!");
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

            System.out.print("ID Genre: ");
            soundwave.model.Genre genre = Migration.getGenres().get(input.nextLine());

            UserInterface ui = new UserInterface();
            ui.banner("EDIT GENRE");

            System.out.print("Nama   : ");
            genre.setName(input.nextLine());

            System.out.print("Deskripsi : ");
            genre.setDescription(input.nextLine());

            boolean isClean = genre.save();
            if (isClean) {
                ClearConsole.clear();
                ui.banner("Genre berhasil diperbarui!");
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
            ui.banner("HAPUS GENRE");

            System.out.print("ID Genre: ");
            Migration.getGenres().remove(input.nextLine());

            ClearConsole.clear();
            ui.banner("Genre berhasil dihapus!");
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }
}
