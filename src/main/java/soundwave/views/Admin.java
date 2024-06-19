package soundwave.views;

import soundwave.util.Logger;
import soundwave.util.UserInterface;

import java.util.Scanner;

public class Admin {
    public Admin() {
        try {
            UserInterface ui = new UserInterface();
            Scanner input = new Scanner(System.in);

            do {
                ui.banner("Admin Dashboard");
                ui.admin();

                System.out.print("Pilih menu (0..5) [default: 0]: ");
                String userChoose = input.nextLine();

                switch (userChoose) {
                    case "1":
                        Music music = new Music();
                        music.console();
                        break;
                    case "2":
                        Artist artist = new Artist();
                        artist.console();
                        break;
                    case "3":
                        Genre genre = new Genre();
                        genre.console();
                        break;
                    case "4":
                        Playlist playlist = new Playlist();
                        playlist.console();
                        break;
                    case "5":
                        User user = new User();
                        user.console();
                        break;
                    default:
                        break;
                }

                if (userChoose.equalsIgnoreCase("0")) {
                    System.out.print("Apa anda yakin ingin keluar (yes/no) [default: yes]: ");
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
}
