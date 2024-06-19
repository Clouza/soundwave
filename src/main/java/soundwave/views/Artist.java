package soundwave.views;

import soundwave.repository.Migration;
import soundwave.util.ClearConsole;
import soundwave.util.Logger;
import soundwave.util.UserInterface;

import java.util.Scanner;

public class Artist extends View {
    public Artist() {}

    @Override
    public void console() {
        try {
            Scanner input = new Scanner(System.in);
            UserInterface ui = new UserInterface();

            do {
                ClearConsole.clear();
                ui.banner("MENU ARTIS");
                ui.artist();

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

            if (Migration.getArtists() == null) {
                ClearConsole.clear();
                ui.banner("Data Artis kosong");
            } else {
                for (soundwave.model.Artist artist: Migration.getArtists().values()) {
                    ui.readArtis(artist);
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
            soundwave.model.Artist artist = new soundwave.model.Artist();

            UserInterface ui = new UserInterface();
            ui.banner("TAMBAH ARTIS");

            System.out.print("Nama   : ");
            artist.setName(input.nextLine());

            System.out.print("Bio    : ");
            artist.setBio(input.nextLine());

            System.out.print("Negara : ");
            artist.setCountry(input.nextLine());

            boolean isClean = artist.save();
            if (isClean) {
                ClearConsole.clear();
                ui.banner("Artis berhasil disimpan!");
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
            soundwave.model.Artist artist = Migration.getArtists().get(input.nextLine());

            UserInterface ui = new UserInterface();
            ui.banner("EDIT ARTIS");

            System.out.print("Nama   : ");
            artist.setName(input.nextLine());

            System.out.print("Bio    : ");
            artist.setBio(input.nextLine());

            System.out.print("Negara : ");
            artist.setCountry(input.nextLine());

            boolean isClean = artist.save();
            if (isClean) {
                ClearConsole.clear();
                ui.banner("Artis berhasil diperbarui!");
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
            ui.banner("HAPUS ARTIS");

            System.out.print("ID Artis: ");
            Migration.getArtists().remove(input.nextLine());

            ClearConsole.clear();
            ui.banner("Artis berhasil dihapus!");
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }
}
