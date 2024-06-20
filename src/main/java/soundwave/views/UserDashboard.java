package soundwave.views;

import soundwave.util.Logger;
import soundwave.util.UserInterface;

import java.util.Scanner;

public class UserDashboard {
    public UserDashboard() {
        try {
            UserInterface ui = new UserInterface();
            Scanner input = new Scanner(System.in);

            do {
                ui.banner("User Dashboard");
                ui.userDashboard();

                System.out.print("Pilih menu (0..1) [default: 0]: ");
                String userChoose = input.nextLine();

                switch (userChoose) {
                    case "1":
                        Playlist playlist = new Playlist();
                        playlist.console();
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
